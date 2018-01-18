package com.xth.irdb.db;

import android.content.Context;

import com.xth.irdb.bean.AirControlData;
import com.xth.irdb.util.ConstantUtil;
import com.xth.irdb.util.Constants;
import com.xth.irdb.util.LogUtil;

/**
 * Created by XTH on 2018/1/2.
 */

public class BackIrData {
    private Context mContext;
    private DbManage dbManage;

    public DbManage getDbManage() {
        return dbManage;
    }

    public BackIrData(Context context) {
        mContext = context;
        dbManage = new DbManage(context);
    }

/* 7B0: 其中第0个字节：数据为对应空调的温度：19－30度(0x13-0x1e),默认：25度;十六进制,与显示对应,通过温度加减键调节 */
/* 7B1:其中第1个字节：风量数据：自动：01,低：02,中：03,高：04,与显示对应：默认：01,相关显示符号参考样机） */
/* 7B2:其中第2个字节：手动风向：向下：03,中：02,向上：01,默认02,与显示对应; */
/* 7B3:其中第3个字节：自动风向：01,打开,00,关,默认开:01,与显示对应 */
/* 7B4:其中第4个字节：开关数据：开机时：0x01,关机时：0x00,通过按开关机(电源）键实现,开机后,其它键才有效,相关符号才显示) */
/* 7B5:其中第5个字节：键名对应数据,电源：0x01,模式：0x02,风量：0x03,手动风向：0x04, */
                        /*       自动风向：0x05,温度加：0x06,  温度减：0x07, /* 表示当前按下的是哪个键 */
/* 7B6:其中第6个字节：模式对应数据和显示：自动(默认）：0x01,制冷：0X02,抽湿：0X03,送风：0x04;制热：0x05,这些值按模式键实现 */
    private byte[] airContolData(int key, int index) {
        byte[] result = new byte[7];
        AirControlData airControlData = dbManage.getAirControlData(index);//初始化红外控制数据
        switch (key) {
            case Constants.IR_AIR_CLASS.AIR_POWER://电源
                if (airControlData.getPower() == 0) {
                    airControlData.setPower(1);
                } else if (airControlData.getPower() == 1) {
                    airControlData.setPower(0);
                }
                break;
            case Constants.IR_AIR_CLASS.AIR_MODE://模式
                int mode = airControlData.getMode();
                if (mode < 5) {
                    mode++;
                    airControlData.setMode(mode);
                } else {
                    airControlData.setMode(1);
                }
                break;
            case Constants.IR_AIR_CLASS.AIR_VOL://风量
                int vol = airControlData.getVol();
                if (vol < 4) {
                    vol++;
                    airControlData.setVol(vol);
                } else {
                    airControlData.setVol(1);
                }
                break;
            case Constants.IR_AIR_CLASS.AIR_M://手动风向
                int m = airControlData.getManualWind();
                if (m > 1) {
                    m--;
                    airControlData.setManualWind(m);
                } else {
                    airControlData.setManualWind(3);
                }
                break;
            case Constants.IR_AIR_CLASS.AIR_A://自动风向
                if (airControlData.getAutoWind() == 0) {
                    airControlData.setAutoWind(1);

                } else if (airControlData.getAutoWind() == 1) {
                    airControlData.setAutoWind(0);
                }
                break;
            case Constants.IR_AIR_CLASS.AIR_TMP_ADD://温度＋
                int tempAdd = airControlData.getTemp();
                if (tempAdd < 30) {
                    tempAdd++;
                    airControlData.setTemp(tempAdd);
                }
                break;
            case Constants.IR_AIR_CLASS.AIR_TMP_RED://温度－
                int tempRed = airControlData.getTemp();
                if (tempRed > 16) {
                    tempRed--;
                    airControlData.setTemp(tempRed);
                }
                break;
            default:
                break;

        }
        airControlData.setIndex(index);
        dbManage.insertAirControlData(airControlData);//保存红外控制数据
        result[6] = (byte) airControlData.getMode();
        result[5] = (byte) (key + 1);
        result[4] = (byte) airControlData.getPower();
        result[3] = (byte) airControlData.getAutoWind();
        result[2] = (byte) airControlData.getManualWind();
        result[1] = (byte) airControlData.getVol();
        result[0] = (byte) airControlData.getTemp();
        return result;
    }

    public void deleteAirControlData(int index) {
        dbManage.deleteAirControlData(index);//删除红外控制数据
    }

    public byte[] analyzeData(int electricType, byte[] irData, int key, int index) {
        byte checkSum = 0;
        byte[] tempArray = null;
        switch (electricType) {
            case Constants.IR_AIR:
                dbManage.createAirControlData();
                tempArray = new byte[13 + irData.length];
                byte[] airContolDataArray = airContolData(key, index);
                tempArray[0] = 0x30;
                tempArray[1] = 0x01;
//                tempArray[2] = 0x00;
//                tempArray[3] = 0x00;
                tempArray[2] = (byte) (dbManage.getAirOneKeySerial() >> 8);
                tempArray[3] = (byte) dbManage.getAirOneKeySerial();
                for (int i = 0; i < 7; i++) {
                    tempArray[4 + i] = airContolDataArray[i];
                }
                for (int i = 0; i < irData.length; i++) {
                    if (i == 0) {
                        tempArray[11] = (byte) (irData[0] + 1);
                    } else {
                        tempArray[11 + i] = irData[i];
                    }
                }
                tempArray[11 + irData.length] = (byte) 0xff;
                for (int i = 0; i < 12 + irData.length; i++) {
                    checkSum += tempArray[i];
                }
                tempArray[12 + irData.length] = checkSum;
                break;
            default:
                tempArray = new byte[10];
                tempArray[0] = 0x30;
                tempArray[1] = 0x00;
                tempArray[2] = irData[0];
                for (int j = 0; j < 2; j++) {
                    tempArray[j + 3] = irData[j + key * 2 + 1];
                }
                for (int j = 0; j < 4; j++) {
                    tempArray[j + 5] = irData[irData.length - 4 + j];
                }
                for (int j = 0; j < tempArray.length - 1; j++) {
                    checkSum += tempArray[j];
                }
                tempArray[9] = checkSum;
                LogUtil.v("analyzeData" + ConstantUtil.bytes2HexString(tempArray));
                break;
        }
        return tempArray;
    }

    public byte[] modelMatch(int electricType, int serial) {
        return dbManage.matchDb(electricType, serial);
    }

    public byte[] intelligentMatch(int electricType, int lengthIndex) {
        return dbManage.intelligentDb(electricType, lengthIndex);
    }

    public byte[] oneKeyMatch(int electricType, byte[] learnData, int brandIndex) {
        return dbManage.getOneKeyMatchSerial(electricType, learnData, brandIndex);
    }

    public int getIntelligentIndexLength(int electricType, int serial) {
        return dbManage.getIntelligentIndexLength(electricType, serial);
    }

}

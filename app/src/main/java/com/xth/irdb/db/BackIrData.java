package com.xth.irdb.db;

import android.content.Context;
import android.content.SharedPreferences;

import com.xth.irdb.util.ConstantUtil;
import com.xth.irdb.util.Constants;
import com.xth.irdb.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by XTH on 2018/1/2.
 */

public class BackIrData {
    private Context mContext;
    private DbManage dbManage;
    public int temperature;
    public int blowVol;
    public int manualWind;
    public int autoWind;
    public int power;
    public int mode;

    public BackIrData(Context context) {
        mContext = context;
        dbManage = new DbManage(context);
        temperature = 25;
        blowVol = 1;
        manualWind = 2;
        autoWind = 1;
        power = 0;
        mode = 1;
    }

//    private final class AirData {
//        /* 7B0: 其中第0个字节：数据为对应空调的温度：19－30度(0x13-0x1e),默认：25度;十六进制,与显示对应,通过温度加减键调节 */
//     /* 7B1:其中第1个字节：风量数据：自动：01,低：02,中：03,高：04,与显示对应：默认：01,相关显示符号参考样机） */
//     /* 7B2:其中第2个字节：手动风向：向下：03,中：02,向上：01,默认02,与显示对应; */
//     /* 7B3:其中第3个字节：自动风向：01,打开,00,关,默认开:01,与显示对应 */
//     /* 7B4:其中第4个字节：开关数据：开机时：0x01,关机时：0x00,通过按开关机(电源）键实现,开机后,其它键才有效,相关符号才显示) */
//     /* 7B5:其中第5个字节：键名对应数据,电源：0x01,模式：0x02,风量：0x03,手动风向：0x04, */
//                    /*       自动风向：0x05,温度加：0x06,  温度减：0x07, /* 表示当前按下的是哪个键 */
//     /* 7B6:其中第6个字节：模式对应数据和显示：自动(默认）：0x01,制冷：0X02,抽湿：0X03,送风：0x04;制热：0x05,这些值按模式键实现 */
//        public int temperature;
//        public int blowVol;
//        public int manualWind;
//        public int autoWind;
//        public int power;
//        public int mode;
//
//        public AirData(int id) {
////            temperature = 25;
////            blowVol = 1;
////            manualWind = 2;
////            autoWind = 1;
//            power = 0;
////            mode = 1;
////            setAirContolData(id);
//        }
//
////        public byte[] airContolData(int key, int id) {
////            byte[] result = new byte[7];
////            result[5] = (byte) (key + 1);
////            switch (key) {
////                case Constants.AIR.AIR_Power://电源
////                    if (power == 0) {
////                        power = 1;
////                    } else if (power == 1) {
////                        power = 0;
////                    }
////                    result[4] = (byte) power;
////                    break;
////                case Constants.AIR.AIR_Mode://模式
////                    if (mode < 5) {
////                        mode++;
////                    } else {
////                        mode = 1;
////                    }
////                    result[6] = (byte) mode;
////                    break;
////                case Constants.AIR.AIR_Vol://风量
////                    if (blowVol < 4) {
////                        blowVol++;
////                    } else {
////                        blowVol = 1;
////                    }
////                    result[1] = (byte) blowVol;
////                    break;
////                case Constants.AIR.AIR_M://手动风向
////                    if (manualWind > 1) {
////                        manualWind--;
////                    } else {
////                        manualWind = 3;
////                    }
////                    result[2] = (byte) manualWind;
////                    break;
////                case Constants.AIR.AIR_A://自动风向
////                    if (autoWind == 0) {
////                        autoWind = 1;
////                    } else if (autoWind == 1) {
////                        autoWind = 0;
////                    }
////                    result[3] = (byte) autoWind;
////                    break;
////                case Constants.AIR.AIR_tmpAdd://温度＋
////                    if (temperature < 30) {
////                        temperature++;
////                    }
////                    result[0] = (byte) temperature;
////                    break;
////                case Constants.AIR.AIR_tmpRed://温度－
////                    if (temperature > 16) {
////                        temperature--;
////                    }
////                    result[0] = (byte) temperature;
////                    break;
////                default:
////                    break;
////
////            }
////            return result;
////        }
//    }

    private byte[] airContolData(int key) {
        byte[] result = new byte[7];
        result[5] = (byte) (key + 1);
        switch (key) {
            case Constants.IR_AIR_CLASS.AIR_POWER://电源
                if (power == 0) {
                    power = 1;
                } else if (power == 1) {
                    power = 0;
                }
                result[4] = (byte) power;
                break;
            case Constants.IR_AIR_CLASS.AIR_MODE://模式
                if (mode < 5) {
                    mode++;
                } else {
                    mode = 1;
                }
                result[6] = (byte) mode;
                break;
            case Constants.IR_AIR_CLASS.AIR_VOL://风量
                if (blowVol < 4) {
                    blowVol++;
                } else {
                    blowVol = 1;
                }
                result[1] = (byte) blowVol;
                break;
            case Constants.IR_AIR_CLASS.AIR_M://手动风向
                if (manualWind > 1) {
                    manualWind--;
                } else {
                    manualWind = 3;
                }
                result[2] = (byte) manualWind;
                break;
            case Constants.IR_AIR_CLASS.AIR_A://自动风向
                if (autoWind == 0) {
                    autoWind = 1;
                } else if (autoWind == 1) {
                    autoWind = 0;
                }
                result[3] = (byte) autoWind;
                break;
            case Constants.IR_AIR_CLASS.AIR_TMP_ADD://温度＋
                if (temperature < 30) {
                    temperature++;
                }
                result[0] = (byte) temperature;
                break;
            case Constants.IR_AIR_CLASS.AIR_TMP_RED://温度－
                if (temperature > 16) {
                    temperature--;
                }
                result[0] = (byte) temperature;
                break;
            default:
                break;

        }
        return result;
    }

    public byte[] analyzeData(int electricType, byte[] irData, int key) {
        byte checkSum = 0;
        byte[] tempArray = null;
        switch (electricType) {
            case Constants.IR_AIR:
                tempArray = new byte[13 + irData.length];
                byte[] airContolDataArray = airContolData(key);
                tempArray[0] = 0x30;
                tempArray[1] = 0x01;
                tempArray[2] = (byte) (dbManage.getSerialNum() >> 8);
                tempArray[3] = (byte) dbManage.getSerialNum();
                for (int i = 0; i < 7; i++) {
                    tempArray[4 + i] = airContolDataArray[0];
                }
                for (int i = 0; i < irData.length; i++) {
                    if (i == 0) {
                        tempArray[11] = (byte) (irData[0] + 1);
                    } else {
                        tempArray[11 + i] = irData[0];
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
                LogUtil.i("analyzeData" + ConstantUtil.bytes2HexString(tempArray));
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

//    private void setAirContolData(int id) {
//        SharedPreferences.Editor editor = mContext.getSharedPreferences("airControlData", Context.MODE_PRIVATE).edit();
//        editor.putInt("temperature" + id, airData.temperature);
//        editor.putInt("blowVol" + id, airData.blowVol);
//        editor.putInt("manualWind" + id, airData.manualWind);
//        editor.putInt("autoWind" + id, airData.autoWind);
////        editor.putInt("power",arcData.power);
//        editor.putInt("mode" + id, airData.mode);
//        editor.apply();
//    }

//    private void removeAirContolData(int id) {
//        SharedPreferences.Editor editor = mContext.getSharedPreferences("airControlData", Context.MODE_PRIVATE).edit();
//        editor.remove("temperature" + id);
//        editor.remove("blowVol" + id);
//        editor.remove("manualWind" + id);
//        editor.remove("autoWind" + id);
////        editor.putInt("power",arcData.power);
//        editor.remove("mode" + id);
//        editor.commit();
//    }
//
//    private void getAirContolData(int id) {
//        SharedPreferences preferences = mContext.getSharedPreferences("airControlData", Context.MODE_PRIVATE);
//        airData.temperature = preferences.getInt("temperature" + id, 25);
//        airData.blowVol = preferences.getInt("blowVol" + id, 1);
//        airData.manualWind = preferences.getInt("manualWind" + id, 2);
//        airData.autoWind = preferences.getInt("autoWind" + id, 1);
////        arcData.power = preferences.getInt("power",0);
//        airData.mode = preferences.getInt("mode" + id, 1);
//    }
}

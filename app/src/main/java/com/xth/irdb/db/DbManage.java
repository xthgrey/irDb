package com.xth.irdb.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import com.xth.irdb.bean.AirControlData;
import com.xth.irdb.bean.DbData;
import com.xth.irdb.util.ConstantUtil;
import com.xth.irdb.util.Constants;
import com.xth.irdb.util.LogUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by XTH on 2017/12/12.
 */

public class DbManage {
    private static final String DbName = "irlibaray.db";
//    private static final String PacketName = "com.drkon.sh.innolumi.inno_lumi_text";
    private static final String PacketName = "com.xth.irdb";
    private static final String DbPath = "/data" + Environment.getDataDirectory().getAbsolutePath() + "/" + PacketName;
    private static final String DbPathName = DbPath + "/" + DbName;
    private SQLiteDatabase database;
    private Context mContext;
    private int[] intelligentSerial;

    private AirControlData airControlData;
    private DbData dbData;

    public DbData getDbData() {
        return dbData;
    }




    public DbManage(Context context) {
        mContext = context;
        openDb();
        airControlData = new AirControlData();
        dbData = new DbData();
    }

    private void openDb() {
//        if (!new File(DbPathName).exists()) {
            // 如 SQLite 数据库文件不存在，再检查一下 database 目录是否存在
            File f = new File(DbPath);
            // 如 database 目录不存在，新建该目录
            if (!f.exists()) {
                f.mkdir();
            }
            try {
//                InputStream is = mContext.getResources().openRawResource(R.raw.irlibaray);
                InputStream is = mContext.getResources().getAssets().open(DbName);
                FileOutputStream fos = new FileOutputStream(DbPathName);
                byte[] buffer = new byte[8192];
                int count;
                while ((count = is.read(buffer)) > 0) {
                    fos.write(buffer, 0, count);
                }
                fos.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
//        }
        database = SQLiteDatabase.openOrCreateDatabase(DbPathName, null);
    }

    protected SQLiteDatabase getDatabase() {
        return database;
    }

    public void closeDatabase() {
        if (database != null) {
            database.close();
            database = null;
        }
        LogUtil.v("closeDatabase: ");
    }

    protected void createTable(String tableName) {
        boolean flag = false;
        String createTable = "create table " + tableName + " (" +
                "ID integer primary key autoincrement," +
                "SERIAL integer," +
                "BRAND_CN text," +
                "BRAND_EN text," +
                "MODEL text," +
                "CODE blob)";
        Cursor cursor = database.rawQuery("select name from sqlite_master where type='table' order by name", null);
        while (cursor.moveToNext()) {
            //遍历出表名
            String name = cursor.getString(0);
            LogUtil.v("tableName: " + name);
            if (name.equals(tableName) == true) {
                flag = true;
            }
        }
        if (flag == false) {
            LogUtil.v("createTable: " + createTable);
            database.execSQL(createTable);
        }
    }
    protected void createAirControlData() {
        boolean flag = false;
        String createTable = "create table " + Constants.air_control_table + " (" +
                "ID integer primary key autoincrement," +
                "INDEX integer," +
                "TEMP integer," +
                "VOL integer," +
                "M integer," +
                "A integer," +
                "POWER integer," +
                "MODE integer)";
        Cursor cursor = database.rawQuery("select name from sqlite_master where type='table' order by name", null);
        while (cursor.moveToNext()) {
            //遍历出表名
            String name = cursor.getString(0);
            //LogUtil.v("tableName: " + name);
            if (name.equals(Constants.air_control_table)) {
                flag = true;
            }
        }
        if (!flag) {
            //LogUtil.v("createTable: " + createTable);
            database.execSQL(createTable);
        }
    }

    protected void insertAirControlData(AirControlData airControlData) {
        this.airControlData = airControlData;
        if(airControlData.getIndex() == -1){
            return;
        }
        //实例化常量值
        ContentValues cValue = new ContentValues();
        cValue.put("INDEX", airControlData.getIndex());
        cValue.put("TEMP", airControlData.getTemp());
        cValue.put("VOL", airControlData.getVol());
        cValue.put("M", airControlData.getManualWind());
        cValue.put("A", airControlData.getAutoWind());
        cValue.put("POWER", airControlData.getPower());
        cValue.put("MODE", airControlData.getMode());
        database.insert(Constants.air_control_table, null, cValue);
    }
    protected void deleteAirControlData(int index) {
        database.delete(Constants.air_control_table, "INDEX = ?", new String[]{index + ""});
    }
    protected AirControlData getAirControlData(int key,int index) {
        if(index == -1){
            if(key == Constants.IR_AIR_CLASS.AIR_POWER && airControlData.getPower() == 0){
                airControlData.setIndex(index);
                airControlData.setTemp(25);
                airControlData.setVol(1);
                airControlData.setManualWind(2);
                airControlData.setAutoWind(1);
                airControlData.setPower(0);
                airControlData.setMode(1);
                return airControlData;
            }
        }
        Cursor cursor = database.query(Constants.air_control_table, null, "INDEX = ?", new String[]{index + ""}, null, null, null);
        if(cursor.moveToNext()){
            airControlData.setIndex(index);
            airControlData.setTemp(cursor.getInt(2));
            airControlData.setVol(cursor.getInt(3));
            airControlData.setManualWind(cursor.getInt(4));
            airControlData.setAutoWind(cursor.getInt(5));
            airControlData.setPower(cursor.getInt(6));
            airControlData.setMode(cursor.getInt(7));
        }else{
            airControlData.setIndex(index);
            airControlData.setTemp(25);
            airControlData.setVol(1);
            airControlData.setManualWind(2);
            airControlData.setAutoWind(1);
            airControlData.setPower(0);
            airControlData.setMode(1);
        }
        return airControlData;
    }

    protected void insert(String tableName, int serial, String brandCn, String brandEn,String model,byte[] code) {
        //实例化常量值
        ContentValues cValue = new ContentValues();
        cValue.put("SERIAL", serial);
        cValue.put("BRAND_CN", brandCn);
        cValue.put("BRAND_EN", brandEn);
        cValue.put("MODEL", model);
        cValue.put("CODE", code);
        database.insert(tableName, null, cValue);
    }

    protected byte[] matchDb(int electricType, int serial) {
        return modelMatch(electricType, getModelMatchIdSerial(electricType, serial));
    }

    protected byte[] intelligentDb(int electricType, int lengthIndex) {
        return intelligentIndex(electricType, lengthIndex);
    }

    private DbData dbData2Info;
    public DbData getDbData2Info() {
        return dbData2Info;
    }
    private int getModelMatchIdSerial(int electricType, int serial) {
        Cursor cursor = database.query(Constants.fileName[2][electricType], null, "SERIAL = ?", new String[]{serial + ""}, null, null, null);
        cursor.moveToNext();
        dbData.setId(cursor.getInt(0));
        dbData.setBrandCn(cursor.getString(2));
        dbData.setBrandEn(cursor.getString(3));
        dbData.setModel(cursor.getString(4));
        dbData.setCodeInt(cursor.getInt(5));
        dbData2Info = new DbData();
        dbData2Info = dbData;
        cursor.close();
        return dbData.getCodeInt();
    }

    private String getIntelligentIndexInfo(int electricType, int serial) {
        Cursor cursor = database.query(Constants.fileName[1][electricType], null, "SERIAL = ?", new String[]{serial + ""}, null, null, null);
        cursor.moveToNext();
        dbData.setId(cursor.getInt(0));
        dbData.setBrandCn(cursor.getString(2));
        dbData.setBrandEn(cursor.getString(3));
        dbData.setModel(cursor.getString(4));
        dbData.setCodeString(cursor.getString(5));
        cursor.close();
        return dbData.getCodeString();
    }

    protected byte[] getOneKeyMatchSerial(int electricType, byte[] learnData, int brandIndex) {
        int lastSameCounter = 0;
        int serial = 0;
        Cursor cursor = null;
        if (electricType == Constants.IR_STB || electricType == Constants.IR_DVD || electricType == Constants.IR_TV || electricType == Constants.IR_AUDIO) {
            cursor = database.query(Constants.fileName[1][electricType], null, "SERIAL = ?", new String[]{brandIndex + ""}, null, null, null);
            String brandCn = cursor.getString(2);

            cursor = database.query(Constants.fileName[3][electricType], null, "BRANDCN = ?", new String[]{brandCn}, null, null, null);
            int brandSerial = -1;
            while (cursor.moveToNext()) {
                brandSerial++;
                byte[] code = cursor.getBlob(5);
                int sameCounter = compareIrData(code, learnData);
                if (sameCounter > lastSameCounter) {
                    lastSameCounter = sameCounter;
                    serial = brandSerial;
                }
            }
            cursor.close();
            getIntelligentIndexLength(electricType, brandIndex);
            return modelMatch(electricType, intelligentSerial[serial + 1]);
        } else {
            cursor = database.query(Constants.fileName[3][electricType], null, null, null, null, null, null);
            while (cursor.moveToNext()) {
                byte[] code = cursor.getBlob(5);
                int sameCounter = compareIrData(code, learnData);
                if (sameCounter > lastSameCounter) {
                    lastSameCounter = sameCounter;
                    serial = cursor.getInt(1);
                }
            }
            cursor.close();
            return modelMatch(electricType, serial);
        }
    }

    private int compareIrData(byte[] dbData, byte[] revData) {
        int sameCount = 0;
        boolean ffFlag = false;
        if (dbData.length != 230 && revData.length != 230) {
            return 0;
        }
        for (int i = 0; i < 230; i++) {
            if (i < 4) {
                if (dbData[i] != revData[i]) {
                    return 0;
                } else {
                    sameCount++;
                }
            } else {
                if (revData[i] != 0xff) {
                    if (dbData[i] != 0xff) {
                        if (revData[i] == 0x00) {
                            if (dbData[i] == 0x00) {
                                sameCount++;
                            }
                        } else {
                            if (!ffFlag) {
                                float absResult = Math.abs(revData[i] - dbData[i]) / (float) revData[i];
                                if (absResult < 0.2) {
                                    sameCount++;
                                }
                            } else {
                                if (revData[i] == dbData[i]) {
                                    sameCount++;
                                }
                            }
                        }
                    } else {
                        return 0;
                    }
                } else if (revData[i] == 0xff) {
                    if (dbData[i] == 0xff) {
                        ffFlag = true;
                        sameCount++;
                    } else {
                        return 0;
                    }
                }
                if (ffFlag) {
                    if (((revData[i] & 0x0f) == 0x0f) || ((revData[i] & 0xf0) == 0xf0)) {
                        if (((dbData[i] & 0x0f) == 0x0f) || ((dbData[i] & 0xf0) == 0xf0)) {
                            return sameCount;
                        } else {
                            return 0;
                        }
                    }
                }
            }
        }
        return 0;
    }


    private byte[] modelMatch(int electricType, int serial) {
        Cursor cursor = database.query(Constants.fileName[0][electricType], null, "SERIAL = ?", new String[]{serial + ""}, null, null, null);
        cursor.moveToNext();
        dbData.setId(cursor.getInt(0));
        dbData.setBrandCn(cursor.getString(2));
        dbData.setBrandEn(cursor.getString(3));
        dbData.setModel(cursor.getString(4));
        dbData.setCodeByteArray(cursor.getBlob(5));
        cursor.close();
        return dbData.getCodeByteArray();
    }

    private byte[] intelligentIndex(int electricType, int lengthIndex) {
        Cursor cursor = database.query(Constants.fileName[0][electricType], null, "SERIAL = ?", new String[]{intelligentSerial[lengthIndex + 1] + ""}, null, null, null);
        cursor.moveToNext();
        dbData.setId(cursor.getInt(0));
        dbData.setBrandCn(cursor.getString(2));
        dbData.setBrandEn(cursor.getString(3));
        dbData.setModel(cursor.getString(4));
        dbData.setCodeByteArray(cursor.getBlob(5));
        cursor.close();
        return dbData.getCodeByteArray();
    }

    protected int getIntelligentIndexLength(int electricType, int serial) {
        intelligentSerial = ConstantUtil.irDataStringToByte(getIntelligentIndexInfo(electricType, serial));
        return intelligentSerial[0];
    }
}

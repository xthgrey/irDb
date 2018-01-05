package com.xth.irdb.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

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
    private static final String PacketName = "com.drkon.sh.innolumi.inno_lumi_text";
    //    private static final String PacketName = "com.xth.irdb";
    private static final String DbPath = "/data" + Environment.getDataDirectory().getAbsolutePath() + "/" + PacketName;
    private static final String DbPathName = DbPath + "/" + DbName;
    private SQLiteDatabase database;
    private Context mContext;
    private int serialNum;
    private int[] intelligentSerial;

    protected int getSerialNum() {
        return serialNum;
    }


    public DbManage(Context context) {
        mContext = context;
        openDb();
//        createTable(Constants.dynamic_ir_data);
    }

    private void openDb() {
        if (!new File(DbPathName).exists()) {
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
        }
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
        LogUtil.i("closeDatabase: ");
    }

    private void createTable(String tableName) {
        boolean flag = false;
        String createTable = "create table " + tableName + " (" +
                "ID integer primary key autoincrement," +
                "SERIAL integer," +
                "ELECTRICTYPE integer," +
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

    protected void insertIrData(int serial, int matchType, String electricType, byte[] code) {
        //实例化常量值
        ContentValues cValue = new ContentValues();
        cValue.put("SERIAL", serial);
        cValue.put("ELECTRICTYPE", electricType);
        cValue.put("CODE", code);
        database.insert(Constants.dynamic_ir_data, null, cValue);
    }

    protected void deleteIrData(int serial) {
        database.delete(Constants.dynamic_ir_data, "SERIAL = ?", new String[]{serial + ""});
    }

    protected byte[] matchDb(int electricType, int serial) {
        return modelMatch(electricType, getModeMatchIdSerial(electricType, serial));
    }

    protected byte[] intelligentDb(int electricType, int lengthIndex) {
        return intelligentIndex(electricType, lengthIndex);
    }

    private int getModeMatchIdSerial(int electricType, int serial) {
        Cursor cursor = database.query(Constants.fileName[2][electricType], null, "SERIAL = ?", new String[]{serial + ""}, null, null, null);
        cursor.moveToNext();
        int id = cursor.getInt(0);
        String brandCn = cursor.getString(2);
        String brandEn = cursor.getString(3);
        String model = cursor.getString(4);
        int code = cursor.getInt(5);
        //使用Log查看数据,未在界面展示
        LogUtil.i("fileName:" + Constants.fileName[2][electricType] + "--id:" + id + "---serial:" + serial + "---getBrandCn:" + brandCn + "---getBrandEn:" + brandEn +
                "---model:" + model + "---code:" + code);
        cursor.close();
        serialNum = code;
        return code;
    }

    private String getIntelligentIndexInfo(int electricType, int serial) {
        Cursor cursor = database.query(Constants.fileName[1][electricType], null, "SERIAL = ?", new String[]{serial + ""}, null, null, null);
        cursor.moveToNext();
        int id = cursor.getInt(0);
        String brandCn = cursor.getString(2);
        String brandEn = cursor.getString(3);
        String model = cursor.getString(4);
        String code = cursor.getString(5);
        //使用Log查看数据,未在界面展示
        LogUtil.i("fileName:" + Constants.fileName[2][electricType] + "--id:" + id + "---serial:" + serial + "---getBrandCn:" + brandCn + "---getBrandEn:" + brandEn +
                "---model:" + model + "---code:" + code);
        cursor.close();
        return code;
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
        int id = cursor.getInt(0);
        String brandCn = cursor.getString(2);
        String brandEn = cursor.getString(3);
        String model = cursor.getString(4);
        byte[] code = cursor.getBlob(5);
        //使用Log查看数据,未在界面展示
        LogUtil.i("fileName:" + Constants.fileName[0][electricType] + "--id:" + id + "---serial:" + serial + "---getBrandCn:" + brandCn + "---getBrandEn:" + brandEn +
                "---model:" + model + "---code:" + ConstantUtil.bytes2HexString(code));
        cursor.close();
        return code;
    }

    private byte[] intelligentIndex(int electricType, int lengthIndex) {
        Cursor cursor = database.query(Constants.fileName[0][electricType], null, "SERIAL = ?", new String[]{intelligentSerial[lengthIndex + 1] + ""}, null, null, null);
        cursor.moveToNext();
        int id = cursor.getInt(0);
        String brandCn = cursor.getString(2);
        String brandEn = cursor.getString(3);
        String model = cursor.getString(4);
        byte[] code = cursor.getBlob(5);
        //使用Log查看数据,未在界面展示
        LogUtil.i("fileName:" + Constants.fileName[0][electricType] + "--id:" + id + "---serial:" + intelligentSerial[lengthIndex + 1] + "---getBrandCn:" + brandCn + "---getBrandEn:" + brandEn +
                "---model:" + model + "---code:" + ConstantUtil.bytes2HexString(code));
        cursor.close();
        return code;
    }

    protected int getIntelligentIndexLength(int electricType, int serial) {
        intelligentSerial = ConstantUtil.irDataStringToByte(getIntelligentIndexInfo(electricType, serial));
        return intelligentSerial[0];
    }
}

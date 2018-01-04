package com.xth.irdb.db;

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
//    private static final String PacketName = "com.drkon.sh.innolumi.inno_lumi_text";
    private static final String PacketName = "com.xth.irdb";
    private static final String DbPath = "/data" + Environment.getDataDirectory().getAbsolutePath() + "/" + PacketName;
    private static final String DbPathName = DbPath + "/" + DbName;
    private SQLiteDatabase database;
    private Context mContext;
    private int serialNum;

    public int getSerialNum() {
        return serialNum;
    }


    public DbManage(Context context) {
        mContext = context;
        openDb();
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

    public SQLiteDatabase getDatabase() {
        return database;
    }

    public void closeDatabase() {
        if (database != null) {
            database.close();
            database = null;
        }
        LogUtil.i("closeDatabase: ");
    }

    public byte[] matchDb(int matchType, int electricType, int serial) {
        byte[] code = null;
        switch (matchType) {
            case 0:
                code = modelMatch(electricType, getModeMatchIdInfo2(electricType, serial));
                break;
            case 1:
                break;
            default:
                break;
        }
        return code;
    }

    private int getModeMatchIdInfo2(int electricType, int serial) {
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
//    private int[] getModeMatchIdInfo(int electricType, int serial) {
//        Cursor cursor = database.query(Constants.fileName[2][electricType], null, "SERIAL = ?", new String[]{serial + ""}, null, null, null);
//        cursor.moveToNext();
//        int id = cursor.getInt(0);
//        String brandCn = cursor.getString(2);
//        String brandEn = cursor.getString(3);
//        String model = cursor.getString(4);
//        int code = cursor.getInt(5);
//        //使用Log查看数据,未在界面展示
//        LogUtil.i("fileName:" + Constants.fileName[2][electricType] + "--id:" + id + "---serial:" + serial + "---getBrandCn:" + brandCn + "---getBrandEn:" + brandEn +
//                "---model:" + model + "---code:" + code);
//        cursor.close();
//        serialNum = code;
//        return code;
//    }

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
}

package com.xth.irdb.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.xth.irdb.bean.DbData;
import com.xth.irdb.util.ConstantUtil;
import com.xth.irdb.util.Constants;
import com.xth.irdb.util.LogUtil;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Created by XTH on 2018/1/9.
 */

public class ConBineDb {
    private Context mContext;
    private BackIrData backIrData;
    private String brandCn;
    private String brandEn;
    private String model;
    int[] serial;

    public ConBineDb(Context context) {
        mContext = context;
        serial = new int[11];
        backIrData = new BackIrData(mContext);
    }

    public void createConbineTable(String tableName, int type) {
        backIrData.getDbManage().createTable("ConBine" + tableName);
        int serialMax = getInfoTableMaxSerial(tableName);
        LogUtil.e("serialMax:---------------------------------------------------" + serialMax);
        for (int j = 0; j <= serialMax; j++) {
            int counter = backIrData.getIntelligentIndexLength(type, j);
            DbData dbData = backIrData.getDbManage().getDbDataInfo();
            LogUtil.e("j" + j + "----counter:" + counter);
            for (int k = 0; k < counter; k++) {
                byte[] code = backIrData.intelligentMatch(type, k);
                backIrData.getDbManage().insert("ConBine" + tableName, serial[type],dbData.getBrandCn() ,dbData.getBrandEn() ,dbData.getModel(), code);
                LogUtil.e("tableName:" + tableName + "---serial:" + serial[type] +
                        "---brandCn:" + dbData.getBrandCn()+
                        "---brandEn:" + dbData.getBrandEn()+
                        "---model:" + dbData.getModel()+
                        "---code:" + ConstantUtil.bytes2HexString(code));
                serial[type]++;
            }
        }
    }

    public void update(String tableName,String s,int serial) {
        //实例化内容值
        ContentValues values = new ContentValues();
        //在values中添加内容
        values.put("CODE", s);
        //修改条件
        String whereClause = "SERIAL=?";
        //修改添加参数
        String[] whereArgs = {String.valueOf(serial)};
        //修改
        backIrData.getDbManage().getDatabase().update(tableName, values, whereClause, whereArgs);
    }

    public void insertConbineTable(String tableName, int type) {
        backIrData.getDbManage().createTable("ConBine" + tableName);
        int serialMax = getInfoTableMaxSerial(tableName);
        LogUtil.e("insertConbineTable serialMax:---------------------------------------------------" + serialMax);
        for (int j = 0; j <= serialMax; j++) {
            byte[] code = backIrData.modelMatch(type, j);
            DbData dbData = backIrData.getDbManage().getDbDataInfo();
            backIrData.getDbManage().insert("ConBine" + tableName, serial[type],  dbData.getBrandCn(), dbData.getBrandEn(), dbData.getModel(), code);
            LogUtil.e("tableName:" + tableName + "---serial:" + serial[type] +
                    "---brandCn:" + dbData.getBrandCn()+
                    "---brandEn:" + dbData.getBrandEn()+
                    "---model:" + dbData.getModel()+
                    "---code:" + ConstantUtil.bytes2HexString(code));
            serial[type]++;
        }

    }

    public int getInfoTableMaxSerial(String tableName) {
        Cursor cursor = backIrData.getDbManage().getDatabase().query(tableName, null, null, null, null, null, null);
        int serial = 0;
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            serial = cursor.getInt(1);
            brandCn = cursor.getString(2);
            brandEn = cursor.getString(3);
            model = cursor.getString(4);
            LogUtil.e("id:" + id + "---serial:" + serial + "---getBrandCn:" + brandCn + "---getBrandEn:" + brandEn +
                    "---model:" + model);
        }
        return serial;
    }
}

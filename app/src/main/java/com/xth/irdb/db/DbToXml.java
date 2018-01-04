package com.xth.irdb.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.xth.irdb.util.ConstantUtil;
import com.xth.irdb.util.LogUtil;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Created by XTH on 2018/1/3.
 */

public class DbToXml {
    private Context mContext;
    private DbManage dbManage;
    public DbToXml(Context mContext) {
        this.mContext = mContext;
        dbManage = new DbManage(mContext);
    }

    public void readDbToXml(String tableName) {
        Cursor cursor = dbManage.getDatabase().query(tableName, null, null, null, null, null, null);
        String result = "";
        FileOutputStream out = null;
        BufferedWriter writer = null;
        try {
            out = mContext.openFileOutput(tableName+".xml", Context.MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(out));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (cursor.moveToNext()) {
            result = "";
            int id = cursor.getInt(0);
            int serial = cursor.getInt(1);
            String brandCn = cursor.getString(2);
            String brandEn = cursor.getString(3);
            String model = cursor.getString(4);
            int code = cursor.getInt(5);
            //使用Log查看数据,未在界面展示
            result = "<item>" + brandCn + "(" + brandEn + ")" + "-" + model + "</item>\n";
            try {
                writer.write(result);
            } catch (IOException e) {
                e.printStackTrace();
            }
            LogUtil.i("id:" + id + "---serial:" + serial + "---getBrandCn:" + brandCn + "---getBrandEn:" + brandEn +
                    "---model:" + model + "---code:" + code);
        }
        if (writer != null) {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

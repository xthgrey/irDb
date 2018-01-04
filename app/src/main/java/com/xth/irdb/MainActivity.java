package com.xth.irdb;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.xth.irdb.db.BackIrData;
import com.xth.irdb.db.DbToXml;
import com.xth.irdb.util.ConstantUtil;
import com.xth.irdb.util.LogUtil;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private DbToXml dbToXml;
    private BackIrData backIrData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        String a = "2,123,234";
//        LogUtil.i(Arrays.toString(ConstantUtil.irDataStringToByte(a)));
    }
}

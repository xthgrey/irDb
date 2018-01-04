package com.xth.irdb;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.xth.irdb.db.BackIrData;
import com.xth.irdb.db.DbManage;
import com.xth.irdb.db.DbToXml;
import com.xth.irdb.util.ConstantUtil;
import com.xth.irdb.util.Constants;
import com.xth.irdb.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private DbToXml dbToXml;
    private BackIrData backIrData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        dbToXml = new DbToXml(this);
//        for(int i = 0;i < 11; i++){
//            dbToXml.readDbToXml(Constants.fileName[2][i]);
//        }
        backIrData = new BackIrData(this);
        backIrData.airSerchKey(0,6,0,0,1);
        backIrData.airSerchKey(0,6,1,0,2);
        backIrData.removeAirContolData(1);
    }
}

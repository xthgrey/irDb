package com.xth.irdb;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.xth.irdb.db.BackIrData;
import com.xth.irdb.db.DbToXml;
import com.xth.irdb.db.ConBineDb;
import com.xth.irdb.util.Constants;

public class MainActivity extends AppCompatActivity {
    private DbToXml dbToXml;
    private BackIrData backIrData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        String a = "2,123,234";
//        LogUtil.i(Arrays.toString(ConstantUtil.irDataStringToByte(a)));
        ConBineDb textDb = new ConBineDb(this);
//        textDb.update("g_remote_arc_info","167,847,848,849,850,851,852,853,854,855,856,857,858,859,860,861,862,863,864,865,866,867,868,869,870,871,872,873,874,875,876,877,878,879,880,881,882,883,884,885,886,887,888,889,890,891,892,893,894,895,896,897,898,899,900,901,902,903,904,905,906,907,908,909,910,911,912,913,914,915,916,917,918,919,920,921,922,923,924,925,926,927,928,929,930,931,932,933,934,935,936,937,938,939,940,941,942,943,944,945,946,947,948,949,950,951,952,953,954,955,956,957,958,959,960,961,962,963,964,965,966,967,968,969,970,971,972,973,974,975,976,977,978,979,980,981,982,983,984,985,986,987,988,989,990,991,992,993,994,995,996,997,998,999,833,834,835,836,837,838,839,840,841,842,843,844,845,846",190);
//        textDb.update("g_remote_arc_info","24,617,595,601,597,602,605,596,599,600,598,603,604,606,39,70,71,209,312,313,488,489,490,508,509",174);
//        textDb.update("g_remote_arc_info","14,816,817,818,819,820,821,822,823,824,825,826,827,828,829",64);
//        textDb.update("g_remote_arc_info","17,816,21,22,27,817,818,819,820,821,822,823,824,825,826,827,828,829",65);
//        for(int i = 0;i < 11;i++){
//            textDb.createConbineTable(Constants.fileName[1][i],i);
//        }
        for(int i = 0;i < 11;i++){
            textDb.insertConbineTable(Constants.fileName[2][i],i);
        }
    }
}

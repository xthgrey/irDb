package com.xth.irdb.util;

import android.util.Log;

/**
 * Created by XTH on 2017/5/5.
 * 获取类型：this.getLocalClassName()
 * 获取方法名：new Throwable().getStackTrace()[0].getMethodName()
 * LogUtil.d(getClass().getName() + "---" + new Throwable().getStackTrace()[0].getMethodName() + " : ");
 * LogUtil.d(getComponentName()+"---"+new Throwable().getStackTrace()[0].getMethodName() + " : " );
 * LogUtil.d(getClass() + "---" + new Throwable().getStackTrace()[0].getMethodName() + " : ");
 */

public class LogUtil {
    public static final int VERBOSE = 1;
    public static final int DEBUG = 2;
    public static final int INFO = 3;
    public static final int WARNING = 4;
    public static final int ERROR = 5;
    public static final int NOTHING = 6;
    private static int level = NOTHING;
    private static final String TAG = "XTH:";

    public static void v(String msg){
        if(level <= VERBOSE){
            Log.v(TAG, msg);
        }
    }
    public static void d(String msg){
        if(level <= DEBUG){
            Log.d(TAG, msg);
        }
    }
    public static void i(String msg){
        if(level <= INFO){
            Log.i(TAG, msg);
        }
    }
    public static void w(String msg){
        if(level <= WARNING){
            Log.w(TAG, msg);
        }
    } public static void e(String msg){
        if(level <= ERROR){
            Log.e(TAG, msg);
        }
    }

}

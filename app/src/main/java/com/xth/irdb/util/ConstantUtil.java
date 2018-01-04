package com.xth.irdb.util;

/**
 * Created by XTH on 2017/12/25.
 */

public class ConstantUtil {
    public static char bigToSmall(char value){
        if(value >= 'A' && value <= 'Z'){
            value = (char) (value + 32);
        }
        return value;
    }
    public static int hexArrayTurnInt(char value){
        int result = 0;
        if(value >= 'a' && value <= 'f'){
            result = value - 'a' + 10;
        }else{
            result = value - '0';
        }
       return result;
    }
    public static byte intArrayTurnByte(int[] intArray){
        return (byte)(intArray[0] * 16 + intArray[1]);
    }
    public static int codeArrayToInt(char[] codeArray, int index) {
        int result = 0;
        for (int i = 0; i <= index; i++) {
            if(i == index){
                result = result + hexArrayTurnInt(codeArray[i]);
            }else{
                result = (int) (result + hexArrayTurnInt(codeArray[i]) *(Math.pow(10,index - i)));
            }
        }
        return result;
    }
    public static String codeArrayToString(char[] codeArray, int index) {
        String result = "";
        for (int i = 0; i <= index; i++) {
            result += codeArray[i];
        }
        return result;
    }
    /*
* 字节数组转String输出
* */
    public static String bytes2HexString(byte[] b) {
        String ret = "";
        for (int i = 0; i < b.length; i++) {
            String hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            ret += "0x";
            ret += hex.toUpperCase();
            ret += ",";
        }
        return ret;
    }
}

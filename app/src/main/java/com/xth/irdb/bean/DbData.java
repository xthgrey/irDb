package com.xth.irdb.bean;

/**
 * Created by XTH on 2018/1/10.
 */

public class DbData {
    private int id;
    private int serial;
    private String brandCn;
    private String brandEn;
    private String model;
    private String codeString;
    private int codeInt;
    private byte[] codeByteArray;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSerial() {
        return serial;
    }

    public void setSerial(int serial) {
        this.serial = serial;
    }

    public String getBrandCn() {
        return brandCn;
    }

    public void setBrandCn(String brandCn) {
        this.brandCn = brandCn;
    }

    public String getBrandEn() {
        return brandEn;
    }

    public void setBrandEn(String brandEn) {
        this.brandEn = brandEn;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCodeString() {
        return codeString;
    }

    public void setCodeString(String codeString) {
        this.codeString = codeString;
    }

    public int getCodeInt() {
        return codeInt;
    }

    public void setCodeInt(int codeInt) {
        this.codeInt = codeInt;
    }

    public byte[] getCodeByteArray() {
        return codeByteArray;
    }

    public void setCodeByteArray(byte[] codeByteArray) {
        this.codeByteArray = codeByteArray;
    }

}

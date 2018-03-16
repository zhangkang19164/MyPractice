package com.example;

/**
 * create time : 2017/10/23
 * desc        :
 */

class SecuTypeTime {
    public static final int LENGTH = 4;
    private int mOpenTime;
    private int mCloseTime;

    public SecuTypeTime(int openTime, int closeTime) {
        this.mOpenTime = openTime;
        this.mCloseTime = closeTime;
    }


    public int getOpenTime() {
        return this.mOpenTime;
    }

    public void setOpenTime(short openTime) {
        this.mOpenTime = openTime;
    }

    public int getCloseTime() {
        return this.mCloseTime;
    }

    public void setCloseTime(short closeTime) {
        this.mCloseTime = closeTime;
    }

    public int getLength() {
        return 4;
    }
}

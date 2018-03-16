package com.example.common.test;

import android.util.Log;

/**
 * create time : 2017/11/02
 * desc        :
 */

public class Test {
    private static final String TAG = "Test";
    private String testString;

    private static Test instance = null;

    private Test() {
        Log.i(TAG, "Test: 初始化");
    }

    public static Test getInstance() {
        synchronized (Test.class) {
            if (instance == null) {
                instance = new Test();
            }
        }
        return instance;
    }

    public void init() {
        testString = "测试内容";
    }

    public String getTestString() {
        return testString;
    }
}

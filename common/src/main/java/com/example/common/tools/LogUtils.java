package com.example.common.tools;

import android.text.TextUtils;
import android.util.Log;

/**
 * create time : 2017/10/17
 * desc        :
 */

public class LogUtils {
    private static final int OUT_MAX_LENGTH = 3 * 1024;

    public static void i(String tag, String msg) {
        if (TextUtils.isEmpty(msg)) {
            msg = "";
        }
        if (msg.length() > OUT_MAX_LENGTH) {
            for (int i = 0; i < msg.length(); i += OUT_MAX_LENGTH) {
                if (i + OUT_MAX_LENGTH < msg.length()) {
                    Log.i(tag, msg.substring(i, i + OUT_MAX_LENGTH));
                } else {
                    Log.i(tag, msg.substring(i, msg.length()));
                }
            }
        } else {
            Log.i(tag, msg);
        }
    }
}

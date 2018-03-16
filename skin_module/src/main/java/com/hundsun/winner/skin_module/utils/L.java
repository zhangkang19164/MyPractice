package com.hundsun.winner.skin_module.utils;

import android.util.Log;

/**
 * Created by william_zhang on 2017/9/18.
 */

public class L {
    private static final String TAG = "Skin";
    private static boolean debug = true;

    public static void e(String msg)
    {
        if (debug)
            Log.e(TAG, msg);
    }

}

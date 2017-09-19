package com.example.common.tools;

import android.support.annotation.Nullable;
import android.text.TextUtils;

/**
 * create time : 2017/09/18
 * desc        :
 */

public class StringTools {

    public static boolean isTrimEmpty(@Nullable String str) {
        return null == str || TextUtils.isEmpty(str.trim());
    }
}

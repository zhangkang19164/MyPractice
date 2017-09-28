package com.example.other.setting.language;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * create time : 2017/09/22
 * desc        :
 */

public class SharedPreferencesUtils {
    private static final String USER_CONFIG = "userConfig";

    public static void setUserSettingLanguage(Context context, int selectLanguage) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(USER_CONFIG, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(UserConfig.SP_LANGUAGE, selectLanguage);
        editor.apply();
    }

    public static int getUserSettingLanguage(Context context, int defaultLanguage) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(USER_CONFIG, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(UserConfig.SP_LANGUAGE, defaultLanguage);
    }
}

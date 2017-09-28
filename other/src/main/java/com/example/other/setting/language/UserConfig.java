package com.example.other.setting.language;

import android.app.Activity;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import java.util.Locale;

/**
 * create time : 2017/09/22
 * desc        : 用户配置
 */

public class UserConfig {
    public static final String SP_LANGUAGE = "language";
    public static final int FOLLOW_SYSTEM = 0;//跟随系统
    public static final int SIMPLIFIED_CHINESE = 1; //简体中文
    public static final int TRADITIONAL_CHINESE = 2; //繁体中文
    public static final int ENGLISH = 3; //英语


    public static void settingLanguage(Activity activity) {
        settingLanguage(activity,SharedPreferencesUtils.getUserSettingLanguage(activity, SIMPLIFIED_CHINESE));
    }

    public static void settingLanguage(Activity activity,int selectLanguage) {
        Locale locale = getLocale(selectLanguage);
        settingLanguage(activity,locale);
    }

    private static void settingLanguage(Activity activity,Locale loc) {
        if (null == loc) {
            return;
        }
        Resources resources = activity.getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        Configuration configuration = resources.getConfiguration();
        configuration.locale = loc;
        resources.updateConfiguration(configuration, displayMetrics);
    }

    private static Locale getLocale(int selectLocale) {
        if (selectLocale == FOLLOW_SYSTEM) {
            return Locale.SIMPLIFIED_CHINESE;
        } else if (selectLocale == SIMPLIFIED_CHINESE) {
            return Locale.SIMPLIFIED_CHINESE;
        } else if (selectLocale == TRADITIONAL_CHINESE) {
            return Locale.TRADITIONAL_CHINESE;
        } else if (selectLocale == ENGLISH) {
            return Locale.ENGLISH;
        } else {
            return Locale.SIMPLIFIED_CHINESE;
        }
    }

}

package com.example.other.readfile;

import android.text.TextUtils;

/**
 * create time : 2017/10/20
 * desc        : 用来储存单个配置信息的类
 */

public class ConfigItem {
    //名字
    private String name;
    //默认值
    private String defaultValue;
    //值
    private String value;


    public ConfigItem(String name, String defaultValue, String value) {
        this.name = name;
        this.defaultValue = defaultValue;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getValue() {
        if (TextUtils.isEmpty(value)) {
            return defaultValue;
        }
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

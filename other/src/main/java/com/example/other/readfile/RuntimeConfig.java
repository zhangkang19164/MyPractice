package com.example.other.readfile;

import android.content.Context;

import com.example.other.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

/**
 * create time : 2017/10/20
 * desc        :
 */

public class RuntimeConfig {
    //二维码前缀
    public static final String CONFIG_QR_CODE_PREFIX = "QRCodePrefix";
    public static final String CONFIG_TIME_STEP = "timeStep";
    public static final String CONFIG_DIGITS = "digits";


    private HashMap<String, ConfigItem> configMap = new HashMap<>();

    private static RuntimeConfig instance = null;

    private RuntimeConfig() {
    }

    public static RuntimeConfig getInstance() {
        synchronized (RuntimeConfig.class) {
            if (instance == null) {
                instance = new RuntimeConfig();
            }
        }

        return instance;
    }

    public void loadConfig(Context context) {
        loadDefaultConfig();
        loadProperties(context);
    }

    public String getStringConfig(String key) {
        if (configMap.containsKey(key)) {
            return configMap.get(key).getValue();
        } else {
            throw new RuntimeException("没有配置该配置项，请检查代码");
        }
    }

    public int getIntConfig(String key) {
        try {
            return Integer.parseInt(getStringConfig(key));
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    /**
     * 初始化配置项，加载默认的配置
     */
    private void loadDefaultConfig() {
        configMap.put(CONFIG_QR_CODE_PREFIX, new ConfigItem(CONFIG_QR_CODE_PREFIX, "HOTFA", ""));
        configMap.put(CONFIG_TIME_STEP, new ConfigItem(CONFIG_TIME_STEP, "60", ""));
        configMap.put(CONFIG_DIGITS, new ConfigItem(CONFIG_DIGITS, "6", ""));
    }

    /**
     * 读取文件中的配置项
     *
     * @param context
     */
    private void loadProperties(Context context) {
        InputStream inputSteam = context.getResources().openRawResource(R.raw.config);
        Properties properties = new Properties();
        try {
            //加载文件
            properties.load(inputSteam);
            Enumeration<Object> keys = properties.keys();
            while (keys.hasMoreElements()) {
                String key = keys.nextElement().toString();
                if (configMap.containsKey(key)) {
                    configMap.get(key).setValue(properties.getProperty(key));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package com.example.practice.other.databinding;

import android.databinding.BindingConversion;
import android.graphics.drawable.ColorDrawable;

import java.util.ArrayList;
import java.util.List;

/**
 * create time : 2017/08/09
 * desc        : DataBinding中使用的自定义的属性转换器
 */

public class DataBindingConverters {
    /**
     * 将String 转换为颜色
     *
     * @param string 输入是颜色
     * @return 颜色
     */
    @BindingConversion
    public static ColorDrawable convertStringToColorDrawable(String string) {
        if (string.equals("黑色")) {
            return new ColorDrawable(0xff000000);
        } else {
            return new ColorDrawable(0xFFFF3E30);
        }

    }
    /**
     * 将boolean转换为特定的颜色
     *
     * @param trueOrFalse true or false
     * @return 转换后的颜色
     */
    @BindingConversion
    public static ColorDrawable convertBooleanToDrawable(boolean trueOrFalse) {
        if (trueOrFalse) {
            return new ColorDrawable(0xff000000);
        } else {
            return new ColorDrawable(0xFFFF3E30);
        }
    }

}

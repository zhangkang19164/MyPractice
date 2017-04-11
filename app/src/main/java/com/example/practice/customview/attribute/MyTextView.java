package com.example.practice.customview.attribute;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * author : Android_张康
 * e-mail : zhangkang19164@hundsun.com
 * time   : 2017/03/30
 * desc   : 用来测试自定义属性的
 * version: 1.0
 */

public class MyTextView extends android.support.v7.widget.AppCompatTextView {


    public MyTextView(Context context) {
        super(context);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}

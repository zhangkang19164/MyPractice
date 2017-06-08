package com.example.practice.customview.attribute;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.practice.R;

/**
 * author : Android_张康
 * e-mail : zhangkang19164@hundsun.com
 * time   : 2017/03/30
 * desc   : 用来测试自定义属性的
 * version: 1.0
 */

public class MyTextView extends View {


    private static final String TAG = "MyTextView";

    public MyTextView(Context context) {
        this(context, null);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyTextView);
        String time = typedArray.getString(R.styleable.MyTextView_time);
        Log.i(TAG, "MyTextView: time = " + time);
        String timeNum = typedArray.getString(R.styleable.MyTextView_time_num);
        Log.i(TAG, "MyTextView: timeNum = " + timeNum);
        typedArray.recycle();
    }
}

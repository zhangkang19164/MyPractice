package com.example.practice.customview.attribute;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import com.example.practice.R;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * author : Android_张康
 * e-mail : zhangkang19164@hundsun.com
 * time   : 2017/03/30
 * desc   : 用来测试自定义属性的
 * version: 1.0
 */

public class MyTextView extends AppCompatTextView {


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

        ArrayList<Integer> arrayList=new ArrayList<>();

        Iterator<Integer> iterator = arrayList.iterator();

        if(iterator.hasNext()){
           Integer i=iterator.next();
        }
        for(Integer i:arrayList){

        }
    }


    public float px2sp(Context context, int unit, float value) {
        return TypedValue.applyDimension(unit, value, context.getResources().getDisplayMetrics());
    }

    public float px2sp(Context context, float value) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, value, context.getResources().getDisplayMetrics());
    }

    public int dp2px(Context context, float value) {
        // Get the screen's density scale
        final float scale = context.getResources().getDisplayMetrics().density;
        // Convert the dps to pixels, based on density scale
        return (int) (value * scale + 0.5f);
    }

    public int px2dp(Context context, int value) {
        // Get the screen's density scale
        final float scale = context.getResources().getDisplayMetrics().density;
        // Convert the dps to pixels, based on density scale
        return (int) (value / scale + 0.5f);
    }
}

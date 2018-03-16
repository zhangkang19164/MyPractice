package com.example.custom.bezier.indicator.view;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.util.AttributeSet;

/**
 * Created on 2018/3/9
 * Title:
 * Description:
 * author 张康
 * update 2018/3/9
 */
public class MyTabLayout extends TabLayout {
    public MyTabLayout(Context context) {
        this(context, null);
    }

    public MyTabLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyTabLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setTabMode(MODE_FIXED);
    }


}

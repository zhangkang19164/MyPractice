package com.example.system.expandablelistview.type.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ExpandableListView;

import com.example.system.R;


/**
 * 自定义的悬停ExpandableListView
 */

public class MyExpandableListView extends ExpandableListView {
    public MyExpandableListView(Context context) {
        this(context, null);
    }


    public MyExpandableListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyExpandableListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray=context.obtainStyledAttributes(attrs, R.styleable.MyExpandableListView);
        typedArray.recycle();
        boolean isShowChildDivider=typedArray.getBoolean(R.styleable.MyExpandableListView_isShowChildDivider,false);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }


    interface ExpandableListViewHoler {

        /**
         * 获取当前顶部GroupView
         */
        View getTopGroupView();


    }


}

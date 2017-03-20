package com.example.practice.customview.linearlayoutcompat.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * 单行或多行LinearLayoutCompat 有分隔线的
 */

public class MyLinearLayoutCompat extends LinearLayoutCompat {

    private Context mContext;
    //有多少个子View
    private int childViewCount;
    //每行有几个
    private int columnWidth;
    //水平方向的分隔线
    private Drawable horizontalDivider;
    //竖直方向的分隔线
    private Drawable verticalDivider;

    private int horizontalDividerPadding=0;

    private int verticalDividerPadding=0;

    private CallBack callBack;

    public MyLinearLayoutCompat(Context context) {
        super(context);
        mContext = context;
    }

    public MyLinearLayoutCompat(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }

    public MyLinearLayoutCompat(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    public void setHorizontalDivider(Drawable horizontalDivider) {
        this.horizontalDivider = horizontalDivider;
    }

    public void setVerticalDivider(Drawable verticalDivider) {
        this.verticalDivider = verticalDivider;
    }


    public void setHorizontalDividerPadding(int horizontalDividerPadding) {
        if(horizontalDividerPadding>0){
            this.horizontalDividerPadding = horizontalDividerPadding;
        }
    }

    public void setVerticalDividerPadding(int verticalDividerPadding) {
        if(verticalDividerPadding>0){
            this.verticalDividerPadding = verticalDividerPadding;
        }
    }

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
        childViewCount = callBack.getChildViewCount();
        columnWidth=callBack.getColumnWidth();
        init();
    }



    private void init() {
        if (childViewCount < 1) {
            return;
        }
        int lines = childViewCount / columnWidth;
        int endLineNum = childViewCount % columnWidth;
        if (lines == 0 || (lines == 1 && endLineNum == 0)) {
            drawSingleLine();
        } else {
            drawMultiLine();
        }
    }

    //只有一行的情况下
    private void drawSingleLine() {
        setOrientation(HORIZONTAL);
        if (null != horizontalDivider) {
            setDividerDrawable(horizontalDivider);
            setShowDividers(SHOW_DIVIDER_MIDDLE);
            setDividerPadding(horizontalDividerPadding);
        }
        for (int i = 0; i < childViewCount; i++) {
            View view = callBack.getView(i, this);
            LayoutParams viewLayoutParams = (LayoutParams) view.getLayoutParams();
            view.setLayoutParams(new LayoutParams(0, viewLayoutParams.height, 1));
            addView(view);
        }
        postInvalidate();
    }

    //有多行的情况下
    private void drawMultiLine() {
        if (null != verticalDivider) {
            setDividerDrawable(verticalDivider);
            setShowDividers(SHOW_DIVIDER_MIDDLE);
            setDividerPadding(verticalDividerPadding);
        }
        setOrientation(VERTICAL);
        //计算需要绘制几行
        int lines = childViewCount / columnWidth;
        int endLineNum = childViewCount % columnWidth;
        //依次添加前几行View
        for (int i = 0; i < lines; i++) {
            LinearLayoutCompat linearLayoutCompat = new LinearLayoutCompat(mContext);
            linearLayoutCompat.setOrientation(HORIZONTAL);
            if (null != horizontalDivider) {
                linearLayoutCompat.setDividerDrawable(horizontalDivider);
                linearLayoutCompat.setShowDividers(SHOW_DIVIDER_MIDDLE);
                linearLayoutCompat.setDividerPadding(horizontalDividerPadding);
            }
            for (int j = i * columnWidth; j < (i + 1) * columnWidth; j++) {
                View view = callBack.getView(j, linearLayoutCompat);
                LayoutParams viewLayoutParams = (LayoutParams) view.getLayoutParams();
                view.setLayoutParams(new LayoutParams(0, viewLayoutParams.height, 1));
                linearLayoutCompat.addView(view);
            }
            addView(linearLayoutCompat);
        }
        if (endLineNum > 0) {
            //添加最后一行View
            LinearLayoutCompat linearLayoutCompat = new LinearLayoutCompat(mContext);
            for (int i = lines * columnWidth; i < childViewCount; i++) {
                if (null != horizontalDivider) {
                    linearLayoutCompat.setDividerDrawable(horizontalDivider);
                    linearLayoutCompat.setShowDividers(SHOW_DIVIDER_MIDDLE);
                    linearLayoutCompat.setDividerPadding(horizontalDividerPadding);
                }
                View view = callBack.getView(i, linearLayoutCompat);
                LayoutParams viewLayoutParams = (LayoutParams) view.getLayoutParams();
                view.setLayoutParams(new LayoutParams(0, viewLayoutParams.height, 1));
                linearLayoutCompat.addView(view);

            }
            addView(linearLayoutCompat);
        }
        postInvalidate();
    }

    public interface CallBack {
        //获取子View的数量
        int getChildViewCount();
        //获取每行的数量
        int getColumnWidth();
        //获取子View
        View getView(int position, ViewGroup parent);
    }
}

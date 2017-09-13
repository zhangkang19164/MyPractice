package com.example.practice.customview.slidingmenu;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

/**
 * create time : 2017/08/30
 * desc        : RecyclerView侧滑菜单关键类
 */

public class SlidingMenu extends HorizontalScrollView {
    private static final float RADIO = 0.3F;
    private final int mScreenWidth;
    private final int mMenuWidth;
    private boolean once = true;

    private boolean isOpen;

    public SlidingMenu(Context context) {
        this(context, null);
    }

    public SlidingMenu(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlidingMenu(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mScreenWidth = getResources().getDisplayMetrics().widthPixels;
        mMenuWidth = (int) (mScreenWidth * RADIO);
        setOverScrollMode(OVER_SCROLL_NEVER);
        setHorizontalScrollBarEnabled(false);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (once) {
            LinearLayout linearLayout = (LinearLayout) getChildAt(0);
            linearLayout.getChildAt(0).getLayoutParams().width = mScreenWidth;
            linearLayout.getChildAt(1).getLayoutParams().width = mMenuWidth;
            once = false;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                closeOpenMenu();
                break;
            case MotionEvent.ACTION_UP:
                int scrollX = getScrollX();
                if (Math.abs(scrollX) > mMenuWidth / 2) {
                    this.smoothScrollTo(mMenuWidth, 0);
                    openMenu();
                } else {
                    this.smoothScrollTo(0, 0);
                }
                return true;
        }
        return super.onTouchEvent(ev);
    }

    public boolean isOpen() {
        return isOpen;
    }

    //打开菜单
    private void openMenu() {
        View view = this;
        while (true) {
            view = (View) view.getParent();
            if (null == view) {
                return;
            }
            if (view instanceof RecyclerView) {
                break;
            }
        }
        ((SlidingMenuAdapter) ((RecyclerView) view).getAdapter()).holdOpenMenu(this);
        isOpen = true;
    }

    //关闭菜单
    public void closeMenu() {
        this.smoothScrollTo(0, 0);
        isOpen = false;
    }

    private void closeOpenMenu() {
        if (!isOpen) {
            View view = this;
            while (true) {
                view = (View) view.getParent();
                if (null == view) {
                    return;
                }
                if (view instanceof RecyclerView) {
                    break;
                }
            }
            ((SlidingMenuAdapter) ((RecyclerView) view).getAdapter()).closeOpenMenu();
        }
    }

}

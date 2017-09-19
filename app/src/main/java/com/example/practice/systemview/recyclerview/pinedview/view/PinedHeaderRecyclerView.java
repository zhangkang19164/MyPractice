package com.example.practice.systemview.recyclerview.pinedview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * create time : 2017/09/08
 * desc        :
 */

public class PinedHeaderRecyclerView extends RecyclerView {
    private PinedHeaderRecyclerView.PinedListener pinedListener;
    private View mHeaderView;
    private View mTouchTarget;

    public PinedHeaderRecyclerView(Context context) {
        this(context,null);
    }

    public PinedHeaderRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public PinedHeaderRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.initView();
    }

    private void initView() {
        this.addOnScrollListener(new PinedHeaderRecyclerView.ScrollListener());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (this.mHeaderView != null) {
            this.measureChild(this.mHeaderView, widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (this.mHeaderView != null) {
            this.drawChild(canvas, this.mHeaderView, this.getDrawingTime());
        }

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int x = (int) ev.getX();
        int y = (int) ev.getY();
        if (this.mHeaderView != null && y >= this.mHeaderView.getTop() && y <= this.mHeaderView.getBottom()) {
            if (ev.getAction() == 0) {
                this.mTouchTarget = this.getTouchTarget(this.mHeaderView, x, y);
            } else if (ev.getAction() == 1) {
                View touchTarget = this.getTouchTarget(this.mHeaderView, x, y);
                if (touchTarget == this.mTouchTarget && this.mTouchTarget.isClickable()) {
                    this.mTouchTarget.performClick();
                    this.refreshHeadView();
                }
            }

            return true;
        } else {
            return super.dispatchTouchEvent(ev);
        }
    }

    private View getTouchTarget(View view, int x, int y) {
        if (!(view instanceof ViewGroup)) {
            return view;
        } else {
            ViewGroup parent = (ViewGroup) view;
            int childrenCount = parent.getChildCount();
            boolean customOrder = this.isChildrenDrawingOrderEnabled();
            Object target = null;

            for (int i = childrenCount - 1; i >= 0; --i) {
                int childIndex = customOrder ? this.getChildDrawingOrder(childrenCount, i) : i;
                View child = parent.getChildAt(childIndex);
                if (this.isTouchPointInView(child, x, y)) {
                    target = child;
                    break;
                }
            }

            if (target == null) {
                target = parent;
            }

            return (View) target;
        }
    }

    private boolean isTouchPointInView(View view, int x, int y) {
        return view.isClickable() && y >= view.getTop() && y <= view.getBottom() && x >= view.getLeft() && x <= view.getRight();
    }

    private void refreshHeadView() {
        if (this.pinedListener != null) {
            int firstVisiblePos = this.getFirstVisiblePosition();
            this.mHeaderView.setVisibility(VISIBLE);
            if (this.pinedListener.isHeader(firstVisiblePos)) {
                this.pinedListener.updateHeaderView(this.mHeaderView, firstVisiblePos);
            } else {
                if (!this.pinedListener.isItem(firstVisiblePos)) {
                    this.mHeaderView.layout(0, 0, 0, 0);
                    return;
                }

                this.pinedListener.updateHeaderView(this.mHeaderView, this.pinedListener.findPreHeaderIndex(firstVisiblePos));
            }

            int nextGroupIndex = this.pinedListener.findLastHeaderIndex(firstVisiblePos + 1);
            if (nextGroupIndex == firstVisiblePos + 1) {
                View view = this.getChildAt(1);
                if (view == null) {
                    return;
                }

                if (view.getTop() <= this.mHeaderView.getMeasuredHeight()) {
                    int delta = this.mHeaderView.getMeasuredHeight() - view.getTop();
                    this.mHeaderView.layout(0, -delta, this.mHeaderView.getMeasuredWidth(), this.mHeaderView.getMeasuredHeight() - delta);
                }
            } else {
                this.mHeaderView.layout(0, 0, this.mHeaderView.getMeasuredWidth(), this.mHeaderView.getMeasuredHeight());
            }

        }
    }

    private int getFirstVisiblePosition() {
        RecyclerView.LayoutManager layoutManager = this.getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager) {
            LinearLayoutManager linearManager = (LinearLayoutManager) layoutManager;
            return linearManager.findFirstVisibleItemPosition();
        } else {
            return -1;
        }
    }

    public void setPinedListener(PinedHeaderRecyclerView.PinedListener pinedListener) {
        this.pinedListener = pinedListener;
        this.mHeaderView = pinedListener.getHeaderView();
        this.requestLayout();
        this.postInvalidate();
    }

    private class ScrollListener extends RecyclerView.OnScrollListener {
        private ScrollListener() {
        }

        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            PinedHeaderRecyclerView.this.refreshHeadView();
        }
    }

    interface PinedListener {
        View getHeaderView();

        void updateHeaderView(View headerView, int groupIndex);

        boolean isHeader(int position);

        int findLastHeaderIndex(int fromIndex);

        boolean isItem(int position);

        int findPreHeaderIndex(int fromIndex);
    }
}

package com.example.practice.other.databinding.twoway;

import android.content.Context;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;

/**
 * create time : 2017/08/09
 * desc        :
 */

public class CustomView extends View {

    private OnVisibilityChangedListener onVisibilityChangedListener;

    public CustomView(Context context) {
        this(context, null);
    }

    public CustomView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

    }

    @Override
    protected void onVisibilityChanged(@NonNull View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        if (null != onVisibilityChangedListener) {
            onVisibilityChangedListener.onChange();
        }
    }

    public void setOnVisibilityChangedListener(OnVisibilityChangedListener onVisibilityChangedListener) {
        this.onVisibilityChangedListener = onVisibilityChangedListener;
    }

    public interface OnVisibilityChangedListener {
        void onChange();
    }
}

package com.example.practice.customview.view;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * create time : 2017/09/08
 * desc        : 可展开的RecyclerView
 */

public class ExpandableRecyclerView extends RecyclerView {

    public ExpandableRecyclerView(Context context) {
        this(context, null);
    }

    public ExpandableRecyclerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ExpandableRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void setLayoutManager(LayoutManager layout) {
        if (layout instanceof LinearLayoutManager) {
            ((LinearLayoutManager) layout).setOrientation(VERTICAL);
        } else {
            layout = new LinearLayoutManager(getContext());
        }
        super.setLayoutManager(layout);
    }
}

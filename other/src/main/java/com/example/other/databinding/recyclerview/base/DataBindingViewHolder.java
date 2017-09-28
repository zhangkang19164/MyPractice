package com.example.other.databinding.recyclerview.base;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

/**
 * create time : 2017/08/09
 * desc        : DataBinding 中RecyclerView 使用的ViewHolder
 */

public class DataBindingViewHolder extends RecyclerView.ViewHolder {

    private ViewDataBinding viewDataBinding;

    public DataBindingViewHolder(ViewDataBinding viewDataBinding) {
        super(viewDataBinding.getRoot());
        this.viewDataBinding = viewDataBinding;
    }

    public ViewDataBinding getViewDataBinding() {
        return viewDataBinding;
    }

    public void setVariable(int variableId, Object value) {
        viewDataBinding.setVariable(variableId, value);
        viewDataBinding.executePendingBindings();
    }
}

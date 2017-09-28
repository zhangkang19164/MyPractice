package com.example.other.databinding.recyclerview.adapter;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

/**
 * create time : 2017/08/07
 * desc        :
 */

public class MVVMViewHolder extends RecyclerView.ViewHolder {
    private ViewDataBinding viewDataBinding;

    public MVVMViewHolder(ViewDataBinding viewDataBinding) {
        super(viewDataBinding.getRoot());
        this.viewDataBinding=viewDataBinding;
    }

    public ViewDataBinding getViewDataBinding() {
        return viewDataBinding;
    }
    public void setVariable(int variableId, Object value){
        viewDataBinding.setVariable(variableId,value);
        viewDataBinding.executePendingBindings();
    }
}

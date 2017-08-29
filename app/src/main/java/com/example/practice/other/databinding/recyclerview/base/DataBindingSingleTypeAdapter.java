package com.example.practice.other.databinding.recyclerview.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

/**
 * create time : 2017/08/09
 * desc        : DataBinding中RecyclerView使用的Adapter
 */

public abstract class DataBindingSingleTypeAdapter<T> extends RecyclerView.Adapter<DataBindingViewHolder> {

    private List<T> list;

    @Override
    public DataBindingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding inflate = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), getLayoutId(), parent, false);
        return new DataBindingViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(DataBindingViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return null == list ? 0 : list.size();
    }

    protected abstract int getLayoutId();

    protected abstract void set();

}

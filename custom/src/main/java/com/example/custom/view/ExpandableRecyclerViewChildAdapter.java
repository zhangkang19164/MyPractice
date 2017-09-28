package com.example.custom.view;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * create time : 2017/09/08
 * desc        :
 */

public interface ExpandableRecyclerViewChildAdapter<VH extends RecyclerView.ViewHolder> {

    VH onCreateChildViewHolder(ViewGroup parent, int childViewType);

    void onBindChildViewHolder(VH holder, int groupPosition, int childPosition);

    int getChildCount(int groupPosition);

    int getChildViewType(int groupPosition, int childPosition);
}

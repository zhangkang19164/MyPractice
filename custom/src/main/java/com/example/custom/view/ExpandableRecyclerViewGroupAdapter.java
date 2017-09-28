package com.example.custom.view;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * create time : 2017/09/08
 * desc        :
 */

public interface ExpandableRecyclerViewGroupAdapter<VH extends RecyclerView.ViewHolder> {

    VH onCreateGroupViewHolder(ViewGroup parent, int groupViewType);

    void onBindGroupViewHolder(VH holder, int groupPosition);

    int getGroupCount();

    int getGroupViewType(int groupPosition);
}

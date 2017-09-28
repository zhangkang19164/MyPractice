package com.example.custom.view;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * create time : 2017/09/08
 * desc        :
 */

public class ExpandableRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
        implements ExpandableRecyclerViewGroupAdapter<RecyclerView.ViewHolder>,
        ExpandableRecyclerViewChildAdapter<RecyclerView.ViewHolder> {
    private ArrayList<Object> showArray = new ArrayList<>();
    private ArrayList<Object> groupArray = new ArrayList<>();
    private ArrayList<ArrayList<Object>> childArray = new ArrayList<>();
    private SparseArray<Integer> groupPositionArray = null;

    @Override
    public final RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }


    @Override
    public final void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int groupPosition;

    }

    @Override
    public final int getItemCount() {
        return showArray.size();
    }

    @Override
    public final int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateChildViewHolder(ViewGroup parent, int childViewType) {
        return null;
    }

    @Override
    public void onBindChildViewHolder(RecyclerView.ViewHolder holder, int groupPosition, int childPosition) {

    }

    @Override
    public int getChildCount(int groupPosition) {
        return childArray.get(groupPosition).size();
    }

    @Override
    public int getChildViewType(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateGroupViewHolder(ViewGroup parent, int groupViewType) {
        return null;
    }

    @Override
    public void onBindGroupViewHolder(RecyclerView.ViewHolder holder, int groupPosition) {

    }

    @Override
    public int getGroupCount() {
        return 0;
    }

    @Override
    public int getGroupViewType(int groupPosition) {
        return 0;
    }

    /**
     * 设置group数据
     */
    public void setGroupData(ArrayList<Object> groupData) {
        this.groupArray = groupData;
    }

    /**
     * 设置child数据
     */
    public void setChildData(ArrayList<ArrayList<Object>> array) {
        this.childArray = array;
    }

    /**
     * 初始化真正显示的数据
     */
    public void initializeData() {
        showArray.clear();
        if (null == groupPositionArray) {
            groupPositionArray = new SparseArray<>();
        } else {
            groupPositionArray.clear();
        }

        for (int i = 0; i < groupArray.size(); i++) {
            showArray.add(groupArray.get(i));
            groupPositionArray.append(groupArray.size() - 1, i);
            showArray.add(childArray.get(i));
        }
    }


}

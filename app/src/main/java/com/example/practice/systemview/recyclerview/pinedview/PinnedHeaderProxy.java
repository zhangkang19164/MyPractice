package com.example.practice.systemview.recyclerview.pinedview;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.view.View;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * create time : 2017/09/08
 * desc        :
 */

public class PinnedHeaderProxy implements PinedHeaderRecyclerView.PinedListener {
    private ObservableList dataList;
    private PinedHeaderCallBack callBack;
    private Map<PinedHeaderGroup, ObservableList> groupMap = new HashMap();

    public PinnedHeaderProxy(PinedHeaderCallBack callBack) {
        this.callBack = callBack;
    }

    public void setDataList(ObservableList dataList) {
        this.dataList = dataList;
        this.initMap();
    }

    private void initMap() {
        this.groupMap.clear();
        ObservableArrayList tempList = new ObservableArrayList();

        for(int i = 0; i < this.dataList.size(); ++i) {
            Object itemModel = this.dataList.get(i);
            if(itemModel instanceof PinedHeaderGroup) {
                tempList = new ObservableArrayList();
                this.groupMap.put((PinedHeaderGroup)itemModel, tempList);
            } else if(itemModel instanceof PinedHeaderItem) {
                tempList.add(itemModel);
            }
        }

    }

    public void updateChildList(PinedHeaderGroup group, ObservableList childList) {
        if(this.groupMap.containsKey(group)) {
            this.groupMap.put(group, childList);
        }

    }

    public ObservableList getDataList(PinedHeaderGroup group) {
        return this.groupMap.get(group);
    }

    public ObservableList getChildList(int childIndex) {
        for(int i = childIndex - 1; i >= 0; --i) {
            if(this.dataList.get(i) instanceof PinedHeaderGroup) {
                return this.getDataList((PinedHeaderGroup)this.dataList.get(i));
            }
        }

        return new ObservableArrayList();
    }

    private void expandGroup(PinedHeaderGroup group) {
        this.dataList.addAll(this.dataList.indexOf(group) + 1, this.groupMap.get(group));
    }

    private void collapseGroup(PinedHeaderGroup group) {
        if(this.groupMap.containsKey(group)) {
            Iterator var2 = this.groupMap.get(group).iterator();

            while(var2.hasNext()) {
                Object o = var2.next();
                this.dataList.remove(o);
            }

        }
    }

    public PinedHeaderGroup getGroupModel(int groupIndex) {
        return (PinedHeaderGroup)this.dataList.get(groupIndex);
    }

    public void updateListState(int groupIndex) {
        if(groupIndex < this.dataList.size() && this.dataList.get(groupIndex) instanceof PinedHeaderGroup) {
            this.updateListState((PinedHeaderGroup)this.dataList.get(groupIndex));
        }
    }

    public void updateListState(PinedHeaderGroup group) {
        if(group.isExpanded()) {
            this.collapseGroup(group);
        } else {
            this.expandGroup(group);
        }

        group.setExpanded(!group.isExpanded());
    }

    public View getHeaderView() {
        return this.callBack.getHeaderView();
    }

    public void updateHeaderView(View headerView, int groupIndex) {
        this.callBack.updateHeaderView(headerView, groupIndex);
    }

    public boolean isHeader(int position) {
        return (this.dataList != null && !this.dataList.isEmpty() && position >= 0) && this.dataList.get(position) instanceof PinedHeaderGroup;
    }

    public int findLastHeaderIndex(int fromIndex) {
        if(this.dataList != null && !this.dataList.isEmpty() && fromIndex >= 0) {
            for(int i = fromIndex; i < this.dataList.size(); ++i) {
                if(this.dataList.get(i) instanceof PinedHeaderGroup) {
                    return i;
                }
            }

            return -1;
        } else {
            return -1;
        }
    }

    public boolean isItem(int position) {
        return (this.dataList != null && !this.dataList.isEmpty() && position >= 0) && this.dataList.get(position) instanceof PinedHeaderItem;
    }

    public int findPreHeaderIndex(int fromIndex) {
        if(this.dataList != null && !this.dataList.isEmpty() && fromIndex >= 0) {
            for(int i = fromIndex; i >= 0; --i) {
                if(this.dataList.get(i) instanceof PinedHeaderGroup) {
                    return i;
                }
            }

            return -1;
        } else {
            return -1;
        }
    }
}

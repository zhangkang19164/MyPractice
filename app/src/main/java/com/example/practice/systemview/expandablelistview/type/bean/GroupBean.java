package com.example.practice.systemview.expandablelistview.type.bean;

/**
 * Created by zhangkang19164 on 2017/2/24.
 */

public class GroupBean {
    private int groupType;
    private String titleName;

    public GroupBean(int groupType, String titleName) {
        this.groupType = groupType;
        this.titleName = titleName;
    }

    public int getGroupType() {
        return groupType;
    }

    public void setGroupType(int groupType) {
        this.groupType = groupType;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }
}

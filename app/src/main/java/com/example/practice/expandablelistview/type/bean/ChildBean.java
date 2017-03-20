package com.example.practice.expandablelistview.type.bean;

/**
 * Created by zhangkang19164 on 2017/2/24.
 */

public class ChildBean {
    private int childType;
    private String name;

    public ChildBean(String name, int childType) {
        this.name = name;
        this.childType = childType;
    }

    public int getChildType() {
        return childType;
    }

    public void setChildType(int childType) {
        this.childType = childType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

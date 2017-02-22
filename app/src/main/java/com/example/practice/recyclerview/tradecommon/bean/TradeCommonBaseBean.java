package com.example.practice.recyclerview.tradecommon.bean;

/**
 * 交易通用按钮基类
 */

public class TradeCommonBaseBean {
    //标题类型
    public static final int BEAN_TYPE_TITLE = 1;
    //列表类型
    public static final int BEAN_TYPE_LIST = 2;
    //区别View类型
    private int viewType;
    //分组的类别
    private int groupType;
    //要跳转的Activity id
    private String toActivityId;

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    public int getGroupType() {
        return groupType;
    }

    public void setGroupType(int groupType) {
        this.groupType = groupType;
    }

    public String getToActivityId() {
        return toActivityId;
    }

    public void setToActivityId(String toActivityId) {
        this.toActivityId = toActivityId;
    }
}

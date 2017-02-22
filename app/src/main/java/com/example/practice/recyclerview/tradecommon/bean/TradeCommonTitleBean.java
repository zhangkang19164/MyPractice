package com.example.practice.recyclerview.tradecommon.bean;

/**
 * Created by zhangkang19164 on 2017/2/21.
 */

public class TradeCommonTitleBean extends TradeCommonBaseBean {
    private String titleName;
    private int titleIcon;
    private String toActivityId;

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public int getTitleIcon() {
        return titleIcon;
    }

    public void setTitleIcon(int titleIcon) {
        this.titleIcon = titleIcon;
    }

    public String getToActivityId() {
        return toActivityId;
    }

    public void setToActivityId(String toActivityId) {
        this.toActivityId = toActivityId;
    }
}

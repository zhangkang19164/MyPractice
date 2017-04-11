package com.example.practice.systemview.recyclerview.tradecommon.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.practice.R;
import com.example.practice.systemview.recyclerview.tradecommon.view.TradeCommonRecyclerView;
import com.example.practice.systemview.recyclerview.tradecommon.bean.TradeCommonBaseBean;
import com.example.practice.systemview.recyclerview.tradecommon.bean.TradeCommonListBean;
import com.example.practice.systemview.recyclerview.tradecommon.bean.TradeCommonTitleBean;

import java.util.ArrayList;
import java.util.List;

public class TradeCommonActivity extends AppCompatActivity {
    private List<TradeCommonBaseBean> baseBeanList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trade_common);
        initData();
        initView();
    }

    private void initData() {
        baseBeanList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            TradeCommonTitleBean bean = new TradeCommonTitleBean();
            bean.setTitleName("标题" + i);
            bean.setToActivityId("标题" + i);
            bean.setViewType(TradeCommonBaseBean.BEAN_TYPE_TITLE);
            bean.setGroupType(0);
            baseBeanList.add(bean);
        }

        for (int i = 0; i < 3; i++) {
            TradeCommonListBean bean = new TradeCommonListBean();
            bean.setTitle("第一组一级标题 " + i);
            bean.setSubTitle("第一组二级标题");
            bean.setToActivityId("第一组一级标题" + i);
            bean.setViewType(TradeCommonBaseBean.BEAN_TYPE_LIST);
            bean.setGroupType(1);
            baseBeanList.add(bean);
        }
        for (int i = 0; i < 3; i++) {
            TradeCommonListBean bean = new TradeCommonListBean();
            bean.setTitle("第二组一级标题 " + i);
            bean.setSubTitle("第二组二级标题");
            bean.setToActivityId("第二组一级标题" + i);
            bean.setViewType(TradeCommonBaseBean.BEAN_TYPE_LIST);
            bean.setGroupType(2);
            baseBeanList.add(bean);
        }
        for (int i = 0; i < 1; i++) {
            TradeCommonListBean bean = new TradeCommonListBean();
            bean.setTitle("第三组一级标题 " + i);
            bean.setSubTitle("第三组二级标题");
            bean.setToActivityId("第三组一级标题 " + i);
            bean.setViewType(TradeCommonBaseBean.BEAN_TYPE_LIST);
            bean.setGroupType(3);
            baseBeanList.add(bean);
        }
//        for (int i = 0; i < 5; i++) {
//            TradeCommonListBean bean = new TradeCommonListBean();
//            bean.setTitle("我是第四组的一级标题");
//            bean.setSubTitle("我是第四组的二级标题");
//            bean.setViewType(TradeCommonBaseBean.BEAN_TYPE_LIST);
//            bean.setGroupType(4);
//            baseBeanList.add(bean);
//        }
    }

    private void initView() {
        TradeCommonRecyclerView tradeCommonRecyclerView = (TradeCommonRecyclerView) findViewById(R.id.trade_common_recycler);
        tradeCommonRecyclerView.setBaseBeanList(baseBeanList);
    }
}

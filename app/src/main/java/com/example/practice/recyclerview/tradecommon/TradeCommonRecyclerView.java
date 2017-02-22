package com.example.practice.recyclerview.tradecommon;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

import com.example.practice.recyclerview.tradecommon.bean.TradeCommonBaseBean;
import com.example.practice.recyclerview.tradecommon.itemdecoration.TradeCommonItemDecoration;

import java.util.List;

/**
 * 交易通用RecyclerView
 */

public class TradeCommonRecyclerView extends RecyclerView {
    private List<TradeCommonBaseBean> baseBeanList;

    public TradeCommonRecyclerView(Context context) {
        super(context);
    }

    public TradeCommonRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TradeCommonRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setBaseBeanList(List<TradeCommonBaseBean> baseBeanList) {
        this.baseBeanList = baseBeanList;
        if (null != baseBeanList) {
            init(getContext());
        }

    }

    private void init(Context context) {
        initLayoutManager(context);
        initDivider(context);
        initAdapter(context);
    }

    //设置LayoutManager
    private void initLayoutManager(Context context) {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 5);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int viewType = baseBeanList.get(position).getViewType();
                if (viewType == TradeCommonBaseBean.BEAN_TYPE_TITLE) {
                    return 1;
                } else {
                    return 5;
                }
            }
        });
        setLayoutManager(gridLayoutManager);
    }

    //初始化分隔线
    private void initDivider(Context context) {
        addItemDecoration(new TradeCommonItemDecoration(context, new TradeCommonItemDecoration.TradeCommonItemDecorationCallBack() {
            @Override
            public boolean isInGroupEnd(int position) {
                return position == baseBeanList.size() - 1 || baseBeanList.get(position).getGroupType() != baseBeanList.get(position + 1).getGroupType();
            }


            @Override
            public boolean isInGroupFirst(int position) {
                return position != 0 && baseBeanList.get(position).getGroupType() != baseBeanList.get(position - 1).getGroupType();
            }

            @Override
            public boolean isItemNeedDrawDivider(int position) {
                return baseBeanList.get(position).getViewType() != TradeCommonBaseBean.BEAN_TYPE_TITLE;
            }
        }));
    }

    //初始化Adapter
    private void initAdapter(Context context) {
        TradeCommonAdapter tradeCommonAdapter = new TradeCommonAdapter(context, baseBeanList);
        tradeCommonAdapter.setOnItemClickListener(new TradeCommonAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int position, TradeCommonBaseBean baseBean) {
                Toast.makeText(view.getContext(), baseBean.getToActivityId(), Toast.LENGTH_SHORT).show();
            }
        });
        setAdapter(tradeCommonAdapter);
    }


}

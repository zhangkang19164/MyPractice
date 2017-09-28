package com.example.dependencies.smartrefresh;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;


import com.example.common.view.common.CommonRecyclerViewAdapter;
import com.example.common.view.common.CommonViewHolder;
import com.example.dependencies.R;
import com.example.dependencies.databinding.ActivitySmartRefreshLayoutBinding;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

public class SmartRefreshLayoutActivity extends AppCompatActivity {
    private ActivitySmartRefreshLayoutBinding binding;
    private Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_smart_refresh_layout);
        handler=new Handler();
        initView();
    }

    private void initView() {
        final List<String> stringList = new ArrayList<>();
        for (int i = 0; i < 19; i++) {
            stringList.add(i + 1 + "");
        }
        final CommonRecyclerViewAdapter<String> recyclerViewAdapter = new CommonRecyclerViewAdapter<String>(stringList, R.layout.decoration_item) {
            @Override
            public void convert(CommonViewHolder holder, String s) {
                holder.setText(R.id.decoration_item_text, s);
            }
        };
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        binding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        binding.recyclerView.setAdapter(recyclerViewAdapter);
        binding.smartRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(final RefreshLayout refreshlayout) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        final List<String> stringList = new ArrayList<>();
                        for (int i = 0; i < 19; i++) {
                            stringList.add(i + 1 + "刷新后的数据");
                        }
                        recyclerViewAdapter.setList(stringList);
                        refreshlayout.finishRefresh();
                    }
                },1500);

            }
        });
        binding.smartRefresh.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(final RefreshLayout refreshlayout) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        final List<String> stringList = new ArrayList<>();
                        for (int i = 0; i < 10; i++) {
                            stringList.add(i + 1 + "新加的数据");
                        }
                        recyclerViewAdapter.addList(stringList);
                        refreshlayout.finishLoadmore();
                    }
                },1500);
            }
        });

    }

}

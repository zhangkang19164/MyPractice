package com.example.custom.slidingmenu;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;


import com.example.custom.R;
import com.example.custom.databinding.ActivitySlidingMenuBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * 侧滑菜单思路
 * 1、在item布局中实现
 * 2、在RecyclerView的Adapter中记录当前打开的item，点击其他菜单的时候关闭已经打开的
 */
public class SlidingMenuActivity extends AppCompatActivity {
    private ActivitySlidingMenuBinding slidingMenuBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        slidingMenuBinding = DataBindingUtil.setContentView(this, R.layout.activity_sliding_menu);
        initView();
    }

    private void initView() {
        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            stringList.add("第 " + i + " 个");
        }
        slidingMenuBinding.recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        slidingMenuBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        slidingMenuBinding.recyclerView.setAdapter(new SlidingMenuAdapter(stringList, R.layout.item_sliding_menu));
    }
}

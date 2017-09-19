package com.example.practice.systemview.recyclerview.grouping;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;

import com.example.common.view.common.CommonRecyclerViewAdapter;
import com.example.common.view.common.CommonViewHolder;
import com.example.practice.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewGroupingActivity extends AppCompatActivity {
    private List<String> strings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_grouping);
        initDate();
        initView();
    }

    private void initDate() {
        strings = new ArrayList<>();
        for (int i = 1; i < 31; i++) {
            strings.add("第 " + i + " 个");
        }
    }

    private void initView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.grouping_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new CommonRecyclerViewAdapter<String>(strings,R.layout.item_grouping) {
            @Override
            public void convert(CommonViewHolder holder, String s) {
                holder.setText(R.id.item_grouping_text,s);
            }
        });
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

        LinearSnapHelper linearSnapHelper=new LinearSnapHelper();
        linearSnapHelper.attachToRecyclerView(recyclerView);
    }
}

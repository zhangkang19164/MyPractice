package com.example.practice.dependencies.xrecyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.example.practice.R;
import com.example.practice.systemview.recyclerview.common.CommonRecyclerViewAdapter;
import com.example.practice.systemview.recyclerview.common.CommonViewHoler;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class XRecyclerViewActivity extends AppCompatActivity {

    @BindView(R.id.x_recycler_view)
    XRecyclerView xRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xrecycler_view);
        ButterKnife.bind(this);
        initViews();
    }

    private void initViews() {
        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            stringList.add(i + 1 + "");
        }
        xRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        CommonRecyclerViewAdapter<String> recyclerViewAdapter = new CommonRecyclerViewAdapter<String>(stringList, R.layout.decoration_item) {
            @Override
            public void convert(CommonViewHoler holder, String s) {
                holder.setText(R.id.decoration_item_text, s);
            }
        };
        xRecyclerView.setAdapter(recyclerViewAdapter);
        xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                xRecyclerView.refreshComplete();
            }

            @Override
            public void onLoadMore() {

            }
        });
        xRecyclerView.setLoadingMoreEnabled(true);
    }
}

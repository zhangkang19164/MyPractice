package com.example.dependencies.xrecyclerview;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;

import com.example.common.view.common.CommonRecyclerViewAdapter;
import com.example.common.view.common.CommonViewHolder;
import com.example.dependencies.R;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;



public class XRecyclerViewActivity extends AppCompatActivity {

    XRecyclerView xRecyclerView;
    private Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xrecycler_view);
        xRecyclerView= (XRecyclerView) findViewById(R.id.x_recycler_view);
        handler=new Handler();
        initViews();

    }

    private void initViews() {
        final List<String> stringList = new ArrayList<>();
        for (int i = 0; i < 19; i++) {
            stringList.add(i + 1 + "");
        }
        xRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
        xRecyclerView.addHeaderView(LayoutInflater.from(this).inflate(R.layout.header_expandable,xRecyclerView,false));
        final CommonRecyclerViewAdapter<String> recyclerViewAdapter = new CommonRecyclerViewAdapter<String>(stringList, R.layout.decoration_item) {
            @Override
            public void convert(CommonViewHolder holder, String s) {
                holder.setText(R.id.decoration_item_text, s);
            }
        };
        xRecyclerView.setAdapter(recyclerViewAdapter);

        xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        xRecyclerView.refreshComplete();
                    }
                },1500);

            }

            @Override
            public void onLoadMore() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

//                        for (int i = 20; i < 30; i++) {
//                            stringList.add(i + 1 + "");
//                        }
//                        recyclerViewAdapter.notifyDataSetChanged();
//                        xRecyclerView.loadMoreComplete();
                        xRecyclerView.setNoMore(true);

                    }
                },1500);
            }
        });
    }
}

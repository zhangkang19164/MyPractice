package com.example.practice.systemview.listview.empty;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.practice.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EmptyMainActivity extends AppCompatActivity {

    @BindView(R.id.empty_view)
    TextView emptyView;
    @BindView(R.id.empty_view2)
    TextView emptyView2;
    @BindView(R.id.list_view)
    ListView listView;
    @BindView(R.id.button_01)
    Button button01;
    @BindView(R.id.button_02)
    Button button02;

    private List<String> list = new ArrayList<>();
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty_main);
        ButterKnife.bind(this);
        for (int i = 0; i < 10; i++) {
            list.add("");
        }
        initView();
    }

    private void initView() {
//        View emptyView = LayoutInflater.from(this).inflate(R.layout.zhi_shu_quote_item, null);
//        ((ViewGroup)listView.getParent()).addView(emptyView);
        myAdapter = new MyAdapter();
        listView.setAdapter(myAdapter);
        listView.setEmptyView(emptyView);
        listView.setEmptyView(emptyView2);


    }

    @OnClick({R.id.button_01, R.id.button_02})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button_01:
                list.clear();
                myAdapter.notifyDataSetChanged();
                break;
            case R.id.button_02:
                for (int i = 0; i < 10; i++) {
                    list.add("");
                }
                myAdapter.notifyDataSetChanged();
                break;
        }
    }

    private class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (null == convertView) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
            }
            return convertView;
        }

        @Override
        public int getItemViewType(int position) {
            return super.getItemViewType(position);
        }
    }
}

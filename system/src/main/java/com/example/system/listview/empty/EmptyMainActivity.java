package com.example.system.listview.empty;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.system.R;

import java.util.ArrayList;
import java.util.List;



public class EmptyMainActivity extends AppCompatActivity {

    TextView emptyView;
    TextView emptyView2;
    ListView listView;

    private List<String> list = new ArrayList<>();
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty_main);
        for (int i = 0; i < 10; i++) {
            list.add("");
        }
        emptyView= (TextView) findViewById(R.id.empty_view);
        emptyView2= (TextView) findViewById(R.id.empty_view2);
        listView= (ListView) findViewById(R.id.list_view);
        emptyView= (TextView) findViewById(R.id.empty_view);
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

    public void onViewClicked(View view) {
        int i1 = view.getId();
        if (i1 == R.id.button_01) {
            list.clear();
            myAdapter.notifyDataSetChanged();

        } else if (i1 == R.id.button_02) {
            for (int i = 0; i < 10; i++) {
                list.add("");
            }
            myAdapter.notifyDataSetChanged();

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

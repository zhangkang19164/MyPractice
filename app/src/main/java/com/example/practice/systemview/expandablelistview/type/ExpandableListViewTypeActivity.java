package com.example.practice.systemview.expandablelistview.type;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ExpandableListView;

import com.example.practice.R;
import com.example.practice.systemview.expandablelistview.type.adapter.ExpandableListViewTypeAdapter;
import com.example.practice.systemview.expandablelistview.type.bean.ChildBean;
import com.example.practice.systemview.expandablelistview.type.bean.GroupBean;

import java.util.ArrayList;
import java.util.List;

public class ExpandableListViewTypeActivity extends AppCompatActivity {
    private List<GroupBean> groupBeanList;
    private List<List<ChildBean>> childBeanList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable_list_view_type);
        initData();
        initView();
    }

    private void initData() {
        groupBeanList = new ArrayList<>();
        groupBeanList.add(new GroupBean(ExpandableListViewTypeAdapter.GROUP_TYPE_ONE, "板块监测"));
        groupBeanList.add(new GroupBean(ExpandableListViewTypeAdapter.GROUP_TYPE_TWO, "涨幅榜"));
        groupBeanList.add(new GroupBean(ExpandableListViewTypeAdapter.GROUP_TYPE_TWO, "跌幅榜"));
        groupBeanList.add(new GroupBean(ExpandableListViewTypeAdapter.GROUP_TYPE_TWO, "换手率榜"));

        childBeanList = new ArrayList<>();
        List<ChildBean> childBeenOne = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            childBeenOne.add(new ChildBean("板块监测Item", ExpandableListViewTypeAdapter.CHILDREN_TYPE_TWO));
        }
        childBeanList.add(childBeenOne);
        List<ChildBean> childBeenTwo1 = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            childBeenTwo1.add(new ChildBean("涨幅榜Item", ExpandableListViewTypeAdapter.CHILDREN_TYPE_THREE));
        }
        childBeanList.add(childBeenTwo1);
        List<ChildBean> childBeenTwo2 = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            childBeenTwo2.add(new ChildBean("跌幅榜Item", ExpandableListViewTypeAdapter.CHILDREN_TYPE_THREE));
        }
        childBeanList.add(childBeenTwo2);
        List<ChildBean> childBeenTwo3 = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            childBeenTwo3.add(new ChildBean("换手率榜Item", ExpandableListViewTypeAdapter.CHILDREN_TYPE_THREE));
        }
        childBeanList.add(childBeenTwo3);
    }

    private void initView() {
        ExpandableListView expandableListView = (ExpandableListView) findViewById(R.id.expandable_listview_type);
        final ExpandableListViewTypeAdapter adapter = new ExpandableListViewTypeAdapter(this, groupBeanList, childBeanList);
        expandableListView.setAdapter(adapter);
        int groupCount = adapter.getGroupCount();
        for (int i = 0; i < groupCount; i++) {
            expandableListView.expandGroup(i);
        }
        View headerView1= LayoutInflater.from(this).inflate(R.layout.header_expandable,expandableListView,false);
        expandableListView.addHeaderView(headerView1);

        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
            }
        });
    }
}

package com.example.practice.systemview.dialog.system;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.practice.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hspcadmin on 2017/1/14.
 */

public class SimpleAdapter extends BaseAdapter {
	private List<String[]> list;

	public SimpleAdapter() {
		list = new ArrayList<>();
		list.add(new String[]{"姓名:", "张XX"});
		list.add(new String[]{"性别:", "男"});
		list.add(new String[]{"年龄:", "24岁"});
		list.add(new String[]{"籍贯:", "河南"});
	}

	@Override
	public int getCount() {
		return null == list ? 0 : list.size();
	}

	@Override
	public String[] getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent,false);
		TextView t1= (TextView) view.findViewById(R.id.text_00);
		TextView t2= (TextView) view.findViewById(R.id.text_01);
		t1.setText(list.get(position)[0]);
		t2.setText(list.get(position)[1]);
		return view;
	}

	public void setList(List<String[]> list) {
		this.list = list;
		notifyDataSetChanged();
	}
}

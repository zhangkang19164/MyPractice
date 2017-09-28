package com.example.system.recyclerview.decoration.other;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;


import com.example.common.view.common.CommonRecyclerViewAdapter;
import com.example.common.view.common.CommonViewHolder;
import com.example.system.R;
import com.example.system.recyclerview.decoration.decoration.SampleOnDrawOverItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class DecorationActivity extends AppCompatActivity {
	private RecyclerView recyclerView;
	private List<String> stringList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_decoration);
		initData();
		initView();
	}

	private void initData() {
		stringList = new ArrayList<>();
		for (int i = 0; i < 8; i++) {
			stringList.add(i + 1 + "");
		}
	}

	private void initView() {
		recyclerView = (RecyclerView) findViewById(R.id.decoration_recyclerview);
		recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
		CommonRecyclerViewAdapter<String> recyclerViewAdapter = new CommonRecyclerViewAdapter<String>(stringList, R.layout.decoration_item) {
			@Override
			public void convert(CommonViewHolder holder, String s) {
				holder.setText(R.id.decoration_item_text, s);
			}
		};
		recyclerView.setAdapter(recyclerViewAdapter);
		recyclerView.addItemDecoration(new SampleOnDrawOverItemDecoration(this));
		recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.HORIZONTAL));
	}
}

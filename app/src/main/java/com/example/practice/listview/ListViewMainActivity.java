package com.example.practice.listview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.ExpandedMenuView;
import android.view.View;
import android.widget.ExpandableListView;

import com.example.practice.R;
import com.example.practice.listview.divider.ListViewDividerActivity;

public class ListViewMainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_view_main);
		findViewById(R.id.divider).setOnClickListener(onClickListener);
	}

	private View.OnClickListener onClickListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
				case R.id.divider:
					startActivity(new Intent(v.getContext(), ListViewDividerActivity.class));
					break;
				default:
					break;
			}
		}
	};
}

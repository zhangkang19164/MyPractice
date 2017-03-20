package com.example.practice.listview.divider;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.example.practice.R;

public class ListViewDividerActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_view_divider);
		ListView listView= (ListView) findViewById(R.id.list_view);
		listView.addHeaderView(new ViewStub(this));
		listView.addFooterView(new ViewStub(this));
		MyAdapter myAdapter=new MyAdapter();
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

			}
		});
		listView.setAdapter(myAdapter);
	}
	private static class MyAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return 20;
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
			if(null==convertView){
				convertView= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);
			}
			return convertView;
		}

		@Override
		public int getItemViewType(int position) {
			return super.getItemViewType(position);
		}
	}
}

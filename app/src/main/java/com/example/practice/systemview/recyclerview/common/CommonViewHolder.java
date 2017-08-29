package com.example.practice.systemview.recyclerview.common;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

/**
 * 自定义 RecyclerView 	ViewHolder
 */

public class CommonViewHolder extends RecyclerView.ViewHolder {
	private SparseArray<View> sparseArray;

	public CommonViewHolder(View itemView) {
		super(itemView);
		sparseArray = new SparseArray<>();
	}

	public View getView(int viewID) {
		View view = sparseArray.get(viewID);
		if (null == view) {
			view = itemView.findViewById(viewID);
		}
		return view;
	}

	public CommonViewHolder setText(int viewID, String text) {
		TextView textView = (TextView) getView(viewID);
		if (null != textView) {
			textView.setText(text);
		}
		return this;
	}

}

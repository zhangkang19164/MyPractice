package com.example.practice.systemview.recyclerview.decoration.decoration;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.practice.R;


/**
 * getItemOffsets 方法使用
 */

public class SampleGetItemOffsetsItemDecoration extends RecyclerView.ItemDecoration {
	private int divierHeight = 0;

	public SampleGetItemOffsetsItemDecoration(Context context) {
		divierHeight = context.getResources().getDimensionPixelSize(R.dimen.divier_height);
	}

	/**
	 * 该方法主要是为每个Item的四周留下一定的距离
	 *
	 * @param outRect item四周
	 * @param view    当前的item
	 * @param parent  当前RecyclerView
	 * @param state   当前RecyclerView的状态
	 */
	@Override
	public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
		//outRect.top 								上方的距离
		//outRect.set(left, top, right,  bottom)	设置四周的距离
		//parent.getChildAdapterPosition(view)		获取当前View的位置
		//parent.getAdapter().getItemCount()		获取当前RecyclerView共有多少item
		//parent.getChildCount()					获取当前显示的Item的数量

		if (parent.getChildAdapterPosition(view) == 0) {
			outRect.top = divierHeight * 10;
			outRect.bottom = divierHeight;
		} else if (parent.getChildAdapterPosition(view) == parent.getAdapter().getItemCount() - 1) {
			outRect.top = divierHeight;
			outRect.bottom = divierHeight * 10;
		} else {
			outRect.top = divierHeight;
			outRect.bottom = divierHeight;
		}
		outRect.left = divierHeight * 100;
		outRect.right = divierHeight * 100;


	}
}

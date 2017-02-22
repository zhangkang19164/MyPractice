package com.example.practice.recyclerview.decoration.decoration;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * 用来说明ItemDecoration各个方法
 */

public class SampleDecoration extends RecyclerView.ItemDecoration {


	/**
	 * 类似于绘制背景,内容在上方;需要配合getItemOffsets使用
	 *
	 * @param c      画布
	 * @param parent 当前RecyclerView
	 * @param state  当前RecyclerView的状态
	 */
	@Override
	public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
		super.onDraw(c, parent, state);
	}

	/**
	 * 在布局上方绘制,内容在下方
	 *
	 * @param c      画布
	 * @param parent 当前RecyclerView
	 * @param state  当前RecyclerView的状态
	 */
	@Override
	public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
		super.onDrawOver(c, parent, state);
	}

	/**
	 * 该方法主要是为每个Item的四周留下一定的距离,类似于padding的效果
	 *
	 * @param outRect item四周
	 * @param view    当前的item
	 * @param parent  当前RecyclerView
	 * @param state   当前RecyclerView的状态
	 */
	@Override
	public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
		super.getItemOffsets(outRect, view, parent, state);
	}
}

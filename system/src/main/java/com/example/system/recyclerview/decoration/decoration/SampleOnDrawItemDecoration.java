package com.example.system.recyclerview.decoration.decoration;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.system.R;


/**
 * onDraw方法使用
 */

public class SampleOnDrawItemDecoration extends RecyclerView.ItemDecoration {
	private int divier_Height = 0;
	private Paint mPaint;

	public SampleOnDrawItemDecoration(Context context) {
		mPaint = new Paint();
		divier_Height = context.getResources().getDimensionPixelSize(R.dimen.divier_height);
	}

	@Override
	public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
		int count = parent.getChildCount();
		int left = parent.getPaddingLeft();
		int right = parent.getWidth() - parent.getPaddingRight();

		for (int i = 0; i < count - 1; i++) {
			View view = parent.getChildAt(i);
			int top = view.getBottom();
			int bottom = view.getBottom() + divier_Height;
			mPaint.setColor(0xFF008DFE);
			c.drawRect(left + 100, top, right, bottom, mPaint);
			mPaint.setColor(0xFFFF4081);
			c.drawRect(left, top, left + 100, bottom, mPaint);
		}
	}

	@Override
	public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
		super.getItemOffsets(outRect, view, parent, state);
		outRect.bottom = divier_Height;
	}
}

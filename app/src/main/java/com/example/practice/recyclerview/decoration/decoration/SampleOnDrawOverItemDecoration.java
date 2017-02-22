package com.example.practice.recyclerview.decoration.decoration;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.practice.R;


/**
 * Created by hspcadmin on 2017/1/6.
 */

public class SampleOnDrawOverItemDecoration extends RecyclerView.ItemDecoration {
	private int divier_Height = 0;
	private Paint mPaint;

	public SampleOnDrawOverItemDecoration(Context context) {
		mPaint = new Paint();
		divier_Height = context.getResources().getDimensionPixelSize(R.dimen.divier_height);
		mPaint.setColor(0xFFFFFFFF);
	}

	@Override
	public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
		super.onDrawOver(c, parent, state);
		int count = parent.getChildCount();
		for (int i = 0; i < count; i++) {
			View view = parent.getChildAt(i);

			int ss = (i + 1) % 3;
			int sss = (i + 1) / 3;

			int left = view.getPaddingLeft()+view.getWidth()*(ss==0?2:ss-1);
			int right =left+view.getWidth();
			int top = view.getBottom();
			int bottom = view.getBottom() + divier_Height;
			c.drawRect(left+20, top, right-20, bottom, mPaint);

			if (ss != 0) {
				left = view.getWidth() * ss;
				right = left + divier_Height;
				top = parent.getPaddingTop() + view.getHeight() * sss;
				bottom = view.getHeight() + view.getHeight() * sss;
				c.drawRect(left, top + 30, right, bottom - 30, mPaint);
			}
		}
	}

}

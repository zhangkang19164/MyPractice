package com.example.practice.other.bezier;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * author      : Android_张康
 * e-mail      : zhangkang19164@hundsun.com
 * create time : 2017/04/17
 * desc        :    贝塞尔曲线
 * version     :   1.0
 */

public class BezierView extends View {
    private Paint mPaint;
    private PointF start, end, control;

    public BezierView(Context context) {
        this(context, null);
    }

    public BezierView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BezierView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setColor(Color.GRAY);
        mPaint.setStrokeWidth(20);
        canvas.drawPoint(start.x, start.y, mPaint);
        canvas.drawPoint(end.x, end.y, mPaint);
        canvas.drawPoint(control.x, control.y, mPaint);

        //绘制辅助线
        mPaint.setStrokeWidth(4);
        canvas.drawLine(start.x,start.y,control.x,control.y,mPaint);
        canvas.drawLine(end.x,end.y,control.x,control.y,mPaint);

        //绘制贝赛尔曲线
        mPaint.setStrokeWidth(8);
        mPaint.setColor(Color.RED);
        Path path=new Path();
        path.moveTo(start.x,start.y);
        path.quadTo(control.x,control.y,end.x,end.y);
        canvas.drawPath(path,mPaint);

    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(8);
        start = new PointF(0, 0);
        end = new PointF(0, 0);
        control = new PointF(0, 0);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        start.x = 200;
        start.y = h / 2;

        end.x = w - 200;
        end.y = h / 2;

        control.x = w / 2;
        control.y = h / 2 - 200;
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //根据触摸点更新控制点，并重绘
        control.x = event.getX();
        control.y = event.getY();
        invalidate();
        return true;
    }
}

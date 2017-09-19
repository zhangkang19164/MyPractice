package com.example.practice.customview.bezier.indicator.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.common.tools.ViewTools;
import com.example.practice.R;

/**
 * create time : 2017/09/11
 * desc        : 贝塞尔曲线圆
 */

public class BezierCircleView extends View {
    //用来得到位置信息的固定参数
    private static final float C = 0.551915024494F;
    private Paint mPaint;
    private Path mPath;
    //用来储存12个点的位置信息
    private DotLocation[] dotLocations = new DotLocation[]{
            new DotLocation(0, 0), new DotLocation(0, 0), new DotLocation(0, 0),
            new DotLocation(0, 0), new DotLocation(0, 0), new DotLocation(0, 0),
            new DotLocation(0, 0), new DotLocation(0, 0), new DotLocation(0, 0),
            new DotLocation(0, 0), new DotLocation(0, 0), new DotLocation(0, 0),
    };
    //圆的半径
    private float circleRadius = 100;

    private DotLocation circleCentre;
    private float lastX;
    private float moveX = 0;

    public BezierCircleView(Context context) {
        this(context, null);
    }

    public BezierCircleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BezierCircleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(false);
        //设置画笔的样式为只绘制路径
//        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(ContextCompat.getColor(context, R.color._3C9FFC));
        ViewTools.paintSetTextSizeBySp(mPaint, 18);

        mPath = new Path();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        circleCentre = new DotLocation(getWidth() / 4, getHeight() / 2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        changeLocation();

        mPaint.setStyle(Paint.Style.STROKE);
        String drawText = "当前滑动距离：" + moveX;
        canvas.drawText(drawText, getWidth() / 2 - mPaint.measureText(drawText) / 2, getHeight() / 4, mPaint);


        canvas.drawCircle(getWidth() / 4, getHeight() / 2, circleRadius, mPaint);
        canvas.drawCircle(getWidth() / 4 * 3, getHeight() / 2, circleRadius, mPaint);

        drawBezierCircle(canvas);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = event.getX();
               return true;
            case MotionEvent.ACTION_MOVE:
                moveX = event.getX() - lastX;
                if (moveX > 0) {
                    invalidate();
                    return true;
                }
                break;
            case MotionEvent.ACTION_UP:
                lastX = event.getX();
                return true;
        }
        return super.onTouchEvent(event);
    }
    private ValueAnimator valueAnimator;
    private void startAnimator(float start, float end) {
        if (null == valueAnimator) {
            valueAnimator = ValueAnimator
                    .ofFloat(start, end)
                    .setDuration(1000);
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    lastX = (Float) animation.getAnimatedValue();
                    invalidate();
                }
            });
        } else {
            valueAnimator.cancel();
            valueAnimator.setFloatValues(start, end);
        }
        valueAnimator.start();
    }
    //改变位置信息
    private void changeLocation() {
        dotLocations[0].setXY(circleCentre.x, circleCentre.y + circleRadius);
        dotLocations[1].setXY(dotLocations[0].x + circleRadius * C, dotLocations[0].y);
        dotLocations[11].setXY(dotLocations[0].x - circleRadius * C, dotLocations[0].y);

        dotLocations[3].setXY(circleCentre.x + circleRadius + moveX, circleCentre.y);
        dotLocations[2].setXY(dotLocations[3].x, circleCentre.y + circleRadius * C);
        dotLocations[4].setXY(dotLocations[3].x, circleCentre.y - circleRadius * C);

        dotLocations[6].setXY(circleCentre.x, circleCentre.y - circleRadius);
        dotLocations[5].setXY(dotLocations[6].x + circleRadius * C, dotLocations[6].y);
        dotLocations[7].setXY(dotLocations[6].x - circleRadius * C, dotLocations[6].y);

        dotLocations[9].setXY(circleCentre.x - circleRadius, circleCentre.y);
        dotLocations[8].setXY(dotLocations[9].x, circleCentre.y - circleRadius * C);
        dotLocations[10].setXY(dotLocations[9].x, circleCentre.y + circleRadius * C);
    }

    private void drawBezierCircle(Canvas canvas) {
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPath.moveTo(dotLocations[0].x, dotLocations[0].y);
        mPath.cubicTo(dotLocations[1].x, dotLocations[1].y, dotLocations[2].x, dotLocations[2].y, dotLocations[3].x, dotLocations[3].y);

        mPath.cubicTo(dotLocations[4].x, dotLocations[4].y, dotLocations[5].x, dotLocations[5].y, dotLocations[6].x, dotLocations[6].y);

        mPath.cubicTo(dotLocations[7].x, dotLocations[7].y, dotLocations[8].x, dotLocations[8].y, dotLocations[9].x, dotLocations[9].y);

        mPath.cubicTo(dotLocations[10].x, dotLocations[10].y, dotLocations[11].x, dotLocations[11].y, dotLocations[0].x, dotLocations[0].y);

        canvas.drawPath(mPath, mPaint);
    }

    //点的位置信息
    private static class DotLocation {

        float x;
        float y;

        DotLocation(float x, float y) {
            this.x = x;
            this.y = y;
        }

        void setXY(float x, float y) {
            this.x = x;
            this.y = y;
        }
    }
}

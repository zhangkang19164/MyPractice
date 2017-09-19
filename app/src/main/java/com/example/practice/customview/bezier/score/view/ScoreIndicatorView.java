package com.example.practice.customview.bezier.score.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.View;

import com.example.common.tools.StringTools;
import com.example.common.tools.ViewTools;
import com.example.practice.R;

import org.jsoup.helper.StringUtil;

/**
 * create time : 2017/09/18
 * desc        : 评分指示器
 */

public class ScoreIndicatorView extends View {
    /**
     * 默认动画时间
     */
    private static final long DEFAULT_ANIMATOR_DURATION = 1500;
    //起始角度
    private static final float START_ANGLE = 180;
    //划过的角度
    private static final float END_ANGLE = 180;
    private ValueAnimator valueAnimator;
    private Paint mPaint = new Paint();
    private RectF drawRectF = new RectF();
    private RectF outsideRectF = new RectF();
    private float radius = ViewTools.dp2Px(100);
    //外部指示线的半径
    private float outsideRadius = ViewTools.dp2Px(105);
    //当前值需要划过的角度
    private float sweepAngle;
    //最大值
    private float maxValue = 100;
    //最小值
    private float minValue = 0;
    //当前值
    private float currentValue;

    //中间文字内容
    private String middleText;

    private PointF middleTextPointF = new PointF();

    public ScoreIndicatorView(Context context) {
        this(context, null);
    }

    public ScoreIndicatorView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScoreIndicatorView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        Paint paint = mPaint;
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int measureSize = heightSize < widthSize ? heightSize : widthSize;
        setMeasuredDimension(measureSize, measureSize);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        int width = w / 2;
        int height = h / 2;
        drawRectF.left = width - radius;
        drawRectF.top = height - radius;
        drawRectF.right = width + radius;
        drawRectF.bottom = height + radius;
        outsideRectF.left = width - outsideRadius;
        outsideRectF.top = height - outsideRadius;
        outsideRectF.right = width + outsideRadius;
        outsideRectF.bottom = height + outsideRadius;
        middleTextPointF.y = height - radius / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPoint(getWidth() / 2, getHeight() / 2, mPaint);
        drawInsideArc(canvas, mPaint);
        drawOutsideArc(canvas, mPaint);
//        if (!StringTools.isTrimEmpty(middleText)) {
//            mPaint.setTextSize(ViewTools.sp2Px(20));
//            canvas.drawText(middleText, middleTextPointF.x, middleTextPointF.y, mPaint);
//        }
        canvas.save();
        canvas.rotate(sweepAngle, getWidth() / 2, getHeight() / 2);
        canvas.drawLine(getWidth() / 2, getHeight() / 2, getWidth() / 2 - radius / 3 * 2, getHeight() / 2, mPaint);
        canvas.restore();

    }

    private void drawInsideArc(Canvas canvas, Paint paint) {
        int[] colors = {0xffff4639, 0xffCDD513, 0xff3CDF5F, 0xFFFF3E30};
        //circleWidth 圆的直径 取中心点
        SweepGradient sweepGradient = new SweepGradient(getWidth() / 2, getHeight() / 2, colors, null);
        //旋转 不然是从0度开始渐变
        Matrix matrix = new Matrix();
        matrix.setRotate(-180, getWidth() / 2, getHeight() / 2);
        sweepGradient.setLocalMatrix(matrix);
        paint.setShader(sweepGradient);
        paint.setStrokeWidth(20);
        paint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawArc(drawRectF, START_ANGLE, END_ANGLE, false, paint);

        paint.setShader(null);
        paint.setStrokeWidth(1);
        canvas.drawLine(drawRectF.left, getHeight() / 2, drawRectF.left + 20, getHeight() / 2, paint);
        for (int i = 1; i < 30; i++) {
            canvas.save();
            canvas.rotate(6 * i, getWidth() / 2, getHeight() / 2);
            canvas.drawLine(drawRectF.left, getHeight() / 2, i % 5 == 0 ? drawRectF.left + 20 : drawRectF.left + 10, getHeight() / 2, paint);
            canvas.drawText(i + "度", i % 5 == 0 ? drawRectF.left + 20 : drawRectF.left + 10, getHeight() / 2, paint);
            canvas.restore();
        }
        canvas.drawLine(drawRectF.right, getHeight() / 2, drawRectF.right - 20, getHeight() / 2, paint);
    }

    private void drawOutsideArc(Canvas canvas, Paint paint) {
        canvas.drawArc(outsideRectF, START_ANGLE, sweepAngle, false, paint);
        canvas.save();
        canvas.rotate(sweepAngle, getWidth() / 2, getHeight() / 2);
        Path path = new Path();
        path.moveTo(outsideRectF.left - 5, getHeight() / 2);
        path.lineTo(outsideRectF.left + 5, getHeight() / 2);
        path.lineTo(outsideRectF.left, getHeight() / 2-10);
        path.close();
        canvas.drawPath(path,mPaint);
        canvas.restore();
    }

    /**
     * 设置当前值
     *
     * @param currentValue    当前值
     * @param isNeedAnimator  是否需要动画
     * @param isStartAnimator 是否立刻开始动画
     */
    public void setCurrentValue(float currentValue, boolean isNeedAnimator, boolean isStartAnimator) {
//        if (this.currentValue == currentValue) {
//            return;
//        }
        this.currentValue = currentValue;
        float value = (this.currentValue - minValue) / (maxValue - minValue);
        if (!isNeedAnimator) {
            viewInvalidate(value);
            return;
        }
        if (isStartAnimator) {
            startAnimator(0, value);
        }
    }

    private void startAnimator(float startValue, float endValue) {
        ValueAnimator valueAnimator = getValueAnimator();
        if (valueAnimator.isRunning()) {
            valueAnimator.cancel();
        }
        valueAnimator.setFloatValues(startValue, endValue);
        valueAnimator.start();
    }

    private ValueAnimator getValueAnimator() {
        if (null == valueAnimator) {
            valueAnimator = ValueAnimator.ofFloat()
                    .setDuration(DEFAULT_ANIMATOR_DURATION);
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    Float animatedValue = (Float) animation.getAnimatedValue();
                    viewInvalidate(animatedValue);
                }
            });
        }
        return valueAnimator;
    }

    private void viewInvalidate(float animatedValue) {
        sweepAngle = END_ANGLE * animatedValue;
        middleText = getResources().getString(R.string.fom, maxValue * animatedValue);
        middleTextPointF.x = getWidth() / 2 - mPaint.measureText(middleText) / 2;
        invalidate();
    }
}

package com.example.custom.bezier.round.view;

import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import com.example.common.tools.StringTools;
import com.example.common.tools.ViewTools;
import com.example.custom.R;

/**
 * create time : 2017/09/14
 * desc        : 贝塞尔圆形数值指示器
 */

public class BezierRoundView extends View {
       /**
     * 圆弧默认宽度
     */
    private static final float DEFAULT_ARC_WIDTH = 20;
    /**
     * 默认动画时间
     */
    private static final long DEFAULT_ANIMATOR_DURATION = 1500;
    //起始角度
    private static final float START_ANGLE = 120;
    //划过的角度
    private static final float END_ANGLE = 300;

    private Paint mPaint = new Paint();
    //绘制文字的画笔
    private Paint mTextPaint = new Paint();
    //半径
    private float radius;
    //最大值
    private float maxValue;
    //最小值
    private float minValue;
    //当前值
    private float currentValue;
    //当前值需要划过的角度
    private float sweepAngle;

    private float arcWidth;
    private int arcBackground;
    private int indicatorColor;


    private ValueAnimator valueAnimator;
    //中心文字位置
    private PointF middleTextPoint = new PointF();
    //中间文字内容
    private String middleText;
    //中间文字的大小
    private float middleTextSize;
    //中间文字的颜色
    private int middleTextColor;
    //中间文字单位的坐标
    private PointF middleUnitPoint = new PointF();
    //中间文字单位
    private String middleUnit;
    //中间文字单位的大小
    private float middleUnitSize;
    //中间文字单位的颜色
    private int middleUnitColor;
    //底部文字
    private String bottomText;
    //底部文字大小
    private float bottomTextSize;
    //底部文字颜色
    private int bottomTextColor;
    RectF rectF = new RectF();

    public BezierRoundView(Context context) {
        this(context, null);
    }

    public BezierRoundView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BezierRoundView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        final TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.BezierRoundView);
        radius = typedArray.getDimension(R.styleable.BezierRoundView_radius, 0);
        maxValue = typedArray.getFloat(R.styleable.BezierRoundView_max_value, -1);
        minValue = typedArray.getFloat(R.styleable.BezierRoundView_min_value, -1);
        currentValue = typedArray.getFloat(R.styleable.BezierRoundView_current_value, -1);
        arcWidth = typedArray.getDimension(R.styleable.BezierRoundView_arc_width, DEFAULT_ARC_WIDTH);
        arcBackground = typedArray.getColor(R.styleable.BezierRoundView_arc_background, 0xFF476BB2);
        indicatorColor = typedArray.getColor(R.styleable.BezierRoundView_arc_indicator_color, Color.WHITE);
        middleTextSize = typedArray.getDimension(R.styleable.BezierRoundView_middle_text_size, ViewTools.sp2Px(16));
        middleTextColor = typedArray.getColor(R.styleable.BezierRoundView_middle_text_color, Color.WHITE);
        middleUnit = typedArray.getString(R.styleable.BezierRoundView_middle_unit);
        middleUnitSize = typedArray.getDimension(R.styleable.BezierRoundView_middle_unit_size, ViewTools.sp2Px(10));
        middleUnitColor = typedArray.getColor(R.styleable.BezierRoundView_middle_unit_color, Color.WHITE);
        bottomText = typedArray.getString(R.styleable.BezierRoundView_bottom_text);
        bottomTextSize = typedArray.getDimension(R.styleable.BezierRoundView_bottom_text_size, ViewTools.sp2Px(16));
        bottomTextColor = typedArray.getColor(R.styleable.BezierRoundView_bottom_text_color, Color.WHITE);
        typedArray.recycle();


        Paint paint = mPaint;
        //设置抗锯齿
        paint.setAntiAlias(true);
        //设置抗抖动
        paint.setDither(true);
        paint.setStyle(Paint.Style.STROKE);

        Paint textPaint = mTextPaint;
        //设置抗锯齿
        textPaint.setAntiAlias(true);
        //设置抗抖动
        textPaint.setDither(true);
        textPaint.setColor(Color.WHITE);
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
        middleTextPoint.set(w / 2, h / 2);
        int width = w / 2;
        int height = h / 2;
        rectF.left = width - radius;
        rectF.top = height - radius;
        rectF.right = width + radius;
        rectF.bottom = height + radius;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawArc(canvas, mPaint, rectF);
        drawText(canvas, mTextPaint);
        drawIndicator(canvas, mPaint, rectF);
    }


    /**
     * 画圆弧
     *
     * @param canvas 画布
     * @param paint  画笔
     */
    private void drawArc(Canvas canvas, Paint paint, RectF rectF) {

        paint.setStrokeWidth(arcWidth);
        //设置画笔的笔触样式 Cap.ROUND(圆形线冒)、Cap.SQUARE(方形线冒)、Paint.Cap.BUTT(无线冒)
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setColor(arcBackground);
        canvas.drawArc(rectF, START_ANGLE, END_ANGLE, false, paint);
    }

    private void drawIndicator(Canvas canvas, Paint paint, RectF rectF) {
        paint.setColor(indicatorColor);
        canvas.drawArc(rectF, START_ANGLE, sweepAngle, false, paint);
    }

    /**
     * 绘制文字
     *
     * @param canvas 画布
     * @param paint  画笔
     */
    private void drawText(Canvas canvas, Paint paint) {
        if (!StringTools.isTrimEmpty(middleText)) {
            drawContentText(canvas, paint);
        }
        if (!StringTools.isTrimEmpty(bottomText)) {
            drawBottomText(canvas, paint, bottomText);
        }
    }

    /**
     * 绘制中间的文字
     *
     * @param canvas 画布
     * @param paint  画笔
     */
    private void drawContentText(Canvas canvas, Paint paint) {
        //绘制中间文字
        paint.setTextSize(middleTextSize);
        paint.setColor(middleTextColor);
        canvas.drawText(middleText, middleTextPoint.x, middleTextPoint.y, paint);

        //绘制中间文字的单位
        if (!TextUtils.isEmpty(middleUnit)) {
            paint.setTextSize(middleUnitSize);
            paint.setColor(middleUnitColor);
            canvas.drawText(middleUnit, middleUnitPoint.x, middleUnitPoint.y, paint);
        }
    }

    /**
     * 绘制底部的文字
     *
     * @param canvas 画布
     * @param paint  画笔
     */
    private void drawBottomText(Canvas canvas, Paint paint, String bottomText) {
        paint.setTextSize(bottomTextSize);
        paint.setColor(bottomTextColor);
        float w = getWidth() / 2;
        float h = getHeight() / 2;
        //画左边的点
        float xx = radius / 2;
        float yy = (float) (xx * Math.sqrt(3));
        paint.setTextSize(middleTextSize);

        Rect rect = new Rect();
        paint.getTextBounds(bottomText, 0, bottomText.length(), rect);
        //储存空间
        canvas.drawText(bottomText, w - rect.width() / 2, h + yy + rect.height() / 2, paint);
    }


    /**
     * 计算中间文字的位置信息
     *
     * @param paint      画笔
     * @param middleText 中间文字
     * @param middleUnit 中间文字的单位
     */
    private void calculatePoint(Paint paint, String middleText, String middleUnit) {
        Rect rect = new Rect();
        //设置中间文字的文字大小
        paint.setTextSize(middleTextSize);
        //获取中间文字的位置信息
        paint.getTextBounds(middleText, 0, middleText.length(), rect);
        //获取中间文字的宽度
        int middleTextWidth = rect.width();
        //获取中间文字的高度
        int middleTextHeight = rect.height();
        //如果单位为空，不进行下面的计算
        if (StringTools.isTrimEmpty(middleUnit)) {
            //设置中间文字绘制时的位置信息
            middleTextPoint.set(getWidth() / 2 - middleTextWidth / 2, getHeight() / 2);
            return;
        }

        paint.setTextSize(middleUnitSize);
        //获取中间文字的单位的位置信息
        paint.getTextBounds(middleUnit, 0, middleUnit.length(), rect);
        //获取单位文字的高度
        int middleUnitWidth = rect.width();
        int middleUnitHeight = rect.height();

        //设置中间文字绘制时的位置信息
        middleTextPoint.set(getWidth() / 2 - middleTextWidth / 2 - middleUnitWidth / 2, getHeight() / 2);
        //计算单位文字的绘制的y点位置
        float y = middleTextPoint.y - middleTextHeight + middleUnitHeight;
        //
        middleUnitPoint.set(middleTextPoint.x + middleTextWidth + middleUnitWidth, y);
    }

    public void setMaxValue(float maxValue) {
        if (this.maxValue == maxValue) {
            return;
        }
        this.maxValue = maxValue;
        invalidate();
    }

    public void setMinValue(float minValue) {
        if (this.minValue == minValue) {
            return;
        }
        this.minValue = minValue;
        invalidate();
    }


    public void setArcWidth(float arcWidth) {
        this.arcWidth = arcWidth;
    }

    public void setArcBackground(int arcBackground) {
        this.arcBackground = arcBackground;
    }

    public void setIndicatorColor(int indicatorColor) {
        this.indicatorColor = indicatorColor;
    }

    public void setMiddleText(String middleText) {
        this.middleText = middleText;
    }

    public void setMiddleTextSize(float middleTextSize) {
        this.middleTextSize = middleTextSize;
    }

    public void setMiddleTextColor(int middleTextColor) {
        this.middleTextColor = middleTextColor;
    }

    public void setMiddleUnit(String middleUnit) {
        this.middleUnit = middleUnit;
    }

    public void setMiddleUnitSize(float middleUnitSize) {
        this.middleUnitSize = middleUnitSize;
    }

    public void setMiddleUnitColor(int middleUnitColor) {
        this.middleUnitColor = middleUnitColor;
    }

    public void setBottomText(String bottomText) {
        this.bottomText = bottomText;
    }

    public void setBottomTextSize(float bottomTextSize) {
        this.bottomTextSize = bottomTextSize;
    }

    public void setBottomTextColor(int bottomTextColor) {
        this.bottomTextColor = bottomTextColor;
    }

    /**
     * 设置当前值
     *
     * @param currentValue 当前值
     */
    public void setCurrentValue(float currentValue) {
        setCurrentValue(currentValue, false);
    }

    /**
     * 设置当前值
     *
     * @param currentValue   当前值
     * @param isNeedAnimator 是否需要动画
     */
    public void setCurrentValue(float currentValue, boolean isNeedAnimator) {
        setCurrentValue(currentValue, isNeedAnimator, false);
    }

    /**
     * 设置当前值
     *
     * @param currentValue    当前值
     * @param isNeedAnimator  是否需要动画
     * @param isStartAnimator 是否立刻开始动画
     */
    public void setCurrentValue(float currentValue, boolean isNeedAnimator, boolean isStartAnimator) {
        if (this.currentValue == currentValue) {
            return;
        }
        this.currentValue = currentValue;
        checkValue();
        middleText = String.valueOf(currentValue);
        calculatePoint(mTextPaint, middleText, middleUnit);
        float value = (this.currentValue - minValue) / (maxValue - minValue);
        if (!isNeedAnimator) {
            viewInvalidate(value);
            return;
        }
        if (isStartAnimator) {
            startAnimator(0, value);
        }
    }

    public void setAnimatorDuration(long animatorDuration) {
        getValueAnimator().setDuration(animatorDuration);
    }

    public void setInterpolator(TimeInterpolator value) {
        getValueAnimator().setInterpolator(value);
    }

    public void startAnimator() {
        float value = (currentValue - minValue) / (maxValue - minValue);
        startAnimator(0, value);
    }

    private void startAnimator(float startValue, float endValue) {
        ValueAnimator valueAnimator = getValueAnimator();
        if (valueAnimator.isRunning()) {
            valueAnimator.cancel();
        }
        valueAnimator.setFloatValues(startValue, endValue);
        valueAnimator.start();
    }

    /**
     * 检查数值
     */
    private void checkValue() {
        if (maxValue == -1) {
            throw new RuntimeException("没有设置最大值！");
        } else if (minValue == -1) {
            throw new RuntimeException("没有设置最小值！");
        } else if (maxValue <= minValue) {
            throw new RuntimeException("最大值必须大于最小值！");
        }
        //进行数据保护 如果当前值小于最小值，就设置为最小值，如果当前值大于最大值就设置为最大值
        if (currentValue <= minValue) {
            currentValue = minValue;
        } else if (currentValue >= maxValue) {
            currentValue = maxValue;
        }
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
        invalidate();
    }
}

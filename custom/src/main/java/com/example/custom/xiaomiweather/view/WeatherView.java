package com.example.custom.xiaomiweather.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Scroller;

import com.example.common.tools.ViewTools;

import java.util.List;


/**
 * create time : 2017/08/30
 * desc        :
 */

public class WeatherView extends View {
    //每个时间占控件的宽度比例
    private static final float WIDTH_RATIO = 6.0F;
    //顶部预留高度
    private static final float TOP_RESERVE = 0.2F;
    //底部预留高度
    private static final float BOTTOM_RESERVE = 0.4F;
    //左右两边预留的宽度
    private static final float LEFT_RIGHT_RESERVE = 0.4F;

    private Paint mPaint;
    //圆形的虚线
    private PathDashPathEffect pathDashPathEffect;
    //画圆的画笔
    private Paint mWeatherCirclePaint;
    //用来绘制文本的画笔
    private Paint mTextPaint;


    //时间刻度的宽度
    private int timeScaleWidth;
    //温度圆圈的半径
    private float weatherCircleRadius = 10;
    //最低温度
    private int minTemperature = -1;
    //最高温度
    private int maxTemperature = -1;

    //最后点击的x轴坐标
    private float lastX;
    private int screenWidth;
    private int viewWidth;

    private VelocityTracker velocityTracker;
    private Scroller scroller;
    private ViewConfiguration viewConfiguration;

    private List<WeatherSBean> weatherBeanList;
    private SparseArray<Bitmap> weatherIconSparseArray;
    //天气图标的最大宽度，天气图标的最大高度
    private int bitmapMaxWidth, bitmapMaxHeight;

    public WeatherView(Context context) {
        this(context, null);
    }

    public WeatherView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WeatherView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        scroller = new Scroller(context);
        viewConfiguration = ViewConfiguration.get(context);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setStyle(Paint.Style.STROKE);
        Path path = new Path();
        path.addCircle(0, 0, 3, Path.Direction.CW);
        pathDashPathEffect = new PathDashPathEffect(path, 15, 0, PathDashPathEffect.Style.ROTATE);


        mWeatherCirclePaint = new Paint();
        mWeatherCirclePaint.setStrokeWidth(ViewTools.dp2Px(1));
        mWeatherCirclePaint.setAntiAlias(true);
        mWeatherCirclePaint.setDither(true);

        mTextPaint = new Paint();
        mTextPaint.setStyle(Paint.Style.FILL);
        mTextPaint.setAntiAlias(true);
        mTextPaint.setDither(true);
        ViewTools.paintSetTextSizeBySp(mTextPaint, 9);
        screenWidth = ViewTools.getScreenWidth();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), ViewTools.dp2Px(180F));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        timeScaleWidth = (int) (w / WIDTH_RATIO);
        bitmapMaxWidth = (int) (timeScaleWidth / 3.0F);
        bitmapMaxHeight = (int) (getHeight() / 4.0F);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(null==weatherBeanList||weatherBeanList.isEmpty()){
            return;
        }
        viewWidth = (int) (timeScaleWidth * (weatherBeanList.size() - 1 + LEFT_RIGHT_RESERVE * 2));
        drawTimeScale(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (null == velocityTracker) {
            velocityTracker = VelocityTracker.obtain();
        }
        velocityTracker.addMovement(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (!scroller.isFinished()) {  //fling还没结束
                    scroller.abortAnimation();
                }
                lastX = event.getX();
                return true;
            case MotionEvent.ACTION_MOVE:
                float scrollX = lastX - event.getX();
                if (getScrollX() + scrollX < 0) {
                    scrollTo(0, 0);
                    return true;
                } else if (getScrollX() + scrollX > viewWidth - screenWidth) {
                    scrollTo(viewWidth - screenWidth, 0);
                    return true;
                }

                scrollBy((int) scrollX, 0);
                lastX = event.getX();
                invalidate();
                return true;
            case MotionEvent.ACTION_UP:
                velocityTracker.computeCurrentVelocity(1000);  //计算1秒内滑动过多少像素
                int xVelocity = (int) velocityTracker.getXVelocity();
                if (Math.abs(xVelocity) > viewConfiguration.getScaledMinimumFlingVelocity()) {  //滑动速度可被判定为抛动
                    scroller.fling(getScrollX(), 0, -xVelocity, 0, 0, viewWidth - screenWidth, 0, 0);
                    invalidate();
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (scroller.computeScrollOffset()) {
            scrollTo(scroller.getCurrX(), scroller.getCurrY());
            invalidate();
        }
    }

    //设置数据
    public void setWeatherBeanList(List<WeatherSBean> weatherBeanList) {
        if (null == weatherBeanList || weatherBeanList.isEmpty()) {
            return;
        }
        this.weatherBeanList = weatherBeanList;
        minTemperature = -1;
        screenMinAndMaxTemperature();
        initWeatherIcon();
        invalidate();
    }


    //画时间刻度
    private void drawTimeScale(Canvas canvas) {
        mPaint.setColor(Color.GRAY);
        mPaint.setStrokeWidth(ViewTools.dp2Px(1));
        mTextPaint.setColor(Color.BLACK);

        float leftRightReserve = timeScaleWidth * LEFT_RIGHT_RESERVE;
        //画时间线
        canvas.drawLine(leftRightReserve, getHeight() - 30, leftRightReserve + timeScaleWidth * (weatherBeanList.size() - 1), getHeight() - 30, mPaint);

        //获取温度的绘制高度范围
        int heightRange = (int) (getHeight() * (1 - TOP_RESERVE - BOTTOM_RESERVE));
        int topHeight = (int) (getHeight() * TOP_RESERVE);
        //获取最高温度和最低温度的差
        float temperatureDisparity = maxTemperature - minTemperature;
        float previousX = -1;
        float previousY = -1;
        int lastIndex = 0;
        for (int i = 0; i < weatherBeanList.size(); i++) {
            //绘制时间文本
            String string = WeatherUtils.times[i];
            Rect rect = new Rect();
            mTextPaint.getTextBounds(string, 0, string.length(), rect);
            canvas.drawText(string, leftRightReserve + timeScaleWidth * i - rect.width() / 2, getHeight() - 25 + rect.height(), mTextPaint);

            //绘制当前时刻的温度
            float cx = timeScaleWidth * i + leftRightReserve;
            WeatherSBean weatherBean = weatherBeanList.get(i);
            int temperature = weatherBean.getTemperature();
            float cy = topHeight + heightRange * ((maxTemperature - temperature) / temperatureDisparity) - weatherCircleRadius;

            //绘制两个天气之间的连线
            if (previousX != -1 && previousY != -1) {
                canvas.drawLine(previousX, previousY, cx, cy, mWeatherCirclePaint);
                mWeatherCirclePaint.setStyle(Paint.Style.FILL);
                mWeatherCirclePaint.setColor(Color.WHITE);
                canvas.drawCircle(previousX, previousY, weatherCircleRadius + 5, mWeatherCirclePaint);

                //画天气的圆圈
                mWeatherCirclePaint.setStyle(Paint.Style.STROKE);
                mWeatherCirclePaint.setColor(Color.BLUE);
                canvas.drawCircle(previousX, previousY, weatherCircleRadius, mWeatherCirclePaint);
            }

            if (i == weatherBeanList.size() - 1) {
                mWeatherCirclePaint.setStyle(Paint.Style.FILL);
                mWeatherCirclePaint.setColor(Color.WHITE);
                canvas.drawCircle(cx, cy, weatherCircleRadius + 5, mWeatherCirclePaint);

                //画天气的圆圈
                mWeatherCirclePaint.setStyle(Paint.Style.STROKE);
                mWeatherCirclePaint.setColor(Color.BLUE);
                canvas.drawCircle(cx, cy, weatherCircleRadius, mWeatherCirclePaint);
            }

            drawWeatherText(canvas, temperature, cx, cy);
            previousX = cx;
            previousY = cy;

            //绘制不同天气之间的虚线
            if (i == 0 || i == weatherBeanList.size() - 1 || !weatherBeanList.get(i - 1).getStateOfWeather().equals(weatherBean.getStateOfWeather())) {
                //将画笔设置为虚线
                mPaint.setPathEffect(pathDashPathEffect);
                Path path = new Path();
                path.moveTo(timeScaleWidth * i + leftRightReserve, getHeight() - 40);
                path.lineTo(timeScaleWidth * i + leftRightReserve, cy);
                canvas.drawPath(path, mPaint);
                //将画笔设置为null
                mPaint.setPathEffect(null);

                if (i > 0 && (weatherIconSparseArray.indexOfKey(i)) != -1) {
                    Bitmap bitmap = weatherIconSparseArray.get(i);
                    int scrollX = getScrollX();
                    float bitmapLeft;
                    if (scrollX > timeScaleWidth * lastIndex + leftRightReserve) {
                        if (leftRightReserve + timeScaleWidth * i - scrollX <= bitmap.getWidth()) {
                            bitmapLeft = leftRightReserve + timeScaleWidth * i - bitmap.getWidth();
                        } else {
                            bitmapLeft = (leftRightReserve + timeScaleWidth * i + scrollX - bitmap.getWidth()) / 2.0F;
                        }
                    } else {
                        bitmapLeft = leftRightReserve + (timeScaleWidth * (i + lastIndex) - bitmap.getWidth()) / 2.0F;
                    }

                    canvas.drawBitmap(bitmap, bitmapLeft, (getHeight() - bitmap.getHeight()) / 4.0F * 3, mPaint);
                    lastIndex = i;
                }

            }
        }
    }

    //画天气的文字
    private void drawWeatherText(Canvas canvas, int temperature, float cx, float cy) {
        String temperatureStr = temperature + "°";
        Rect rect = new Rect();
        mTextPaint.getTextBounds(temperatureStr, 0, temperatureStr.length(), rect);
        canvas.drawText(temperatureStr, cx - rect.width() / 2, cy - weatherCircleRadius - 5, mTextPaint);
    }

    //筛选出最高温度和最低温度
    private void screenMinAndMaxTemperature() {
        if (minTemperature == -1 || maxTemperature == -1) {
            minTemperature = weatherBeanList.get(0).getTemperature();
            maxTemperature = weatherBeanList.get(0).getTemperature();
            for (WeatherSBean weatherBean : weatherBeanList) {
                minTemperature = Math.min(minTemperature, weatherBean.getTemperature());
                maxTemperature = Math.max(maxTemperature, weatherBean.getTemperature());
            }
        }
    }

    //初始化天气图标
    private void initWeatherIcon() {
        if (null == weatherIconSparseArray) {
            weatherIconSparseArray = new SparseArray<>();
        } else {
            weatherIconSparseArray.clear();
        }
        for (int i = 0; i < weatherBeanList.size(); i++) {
            if (i == 0 || i == weatherBeanList.size() - 1) {
                weatherIconSparseArray.append(weatherBeanList.size() - 1, getWeatherIcon(WeatherUtils.selectWeatherIcon(weatherBeanList.get(i).getStateOfWeather())));
                continue;
            }
            if (!weatherBeanList.get(i).getStateOfWeather().equals(weatherBeanList.get(i - 1).getStateOfWeather())) {
                weatherIconSparseArray.append(i, getWeatherIcon(WeatherUtils.selectWeatherIcon(weatherBeanList.get(i).getStateOfWeather())));
            }
        }
    }

    //将图标按照一定比例缩放
    private Bitmap getWeatherIcon(int drawableId) {
        int outWidth, outHeight;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), drawableId, options);
        outWidth = options.outWidth;
        outHeight = options.outHeight;
        options.inSampleSize = 1;
        if (outWidth > bitmapMaxWidth || outHeight > bitmapMaxHeight) {
            int rationW = Math.round(outWidth / bitmapMaxWidth);
            int rationH = Math.round(outHeight / bitmapMaxHeight);
            options.inSampleSize = Math.max(rationW, rationH);
        }
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(getResources(), drawableId, options);
    }
}

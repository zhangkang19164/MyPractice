package com.example.practice.customview.bezier.circle.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewParent;

import com.example.common.tools.ViewTools;
import com.example.practice.R;

/**
 * create time : 2017/09/11
 * desc        : 贝塞尔曲线ViewPage指示器
 */

public class CircleIndicatorView extends View {

    private Paint mPaint = new Paint();
    private Path mPath = new Path();
    //View的宽度，用来判断页面是否加载完成，用来初始化位置信息
    private float viewWidth = -1;
    //用来储存5个绘制点的数组
    private DotLocation[] dotLocations;
    //用来保存所有的指示器的圆心位置
    private DotLocation[] indicators;
    //指示器的半径
    private float indicatorRadius;
    //指示器的颜色
    private int indicatorColor;
    //指示器的边框宽度
    private float indicatorWidth;
    private float lastX;
    //当前选中的指示器下标
    private int selectPosition = 0;
    //指示器之间的间距
    private float indicatorDistance;
    //左右两边的边距
    private float leftAndRightDistance;
    //用来判断是否为隐式的和ViewPage产生关联，即在布局中关联
    private boolean mSetupViewPagerImplicitly;

    private ViewPager mViewPager;
    private PagerAdapter mPagerAdapter;
    private ViewPageChangeListener mViewPageChangeListener;
    private AdapterChangeListener mAdapterChangeListener;
    private DataSetObserver mPagerAdapterObserver;
    private OnSelectIndicatorColor onSelectIndicatorColor;
    //指示器的数量
    private int indicatorCount;

    public CircleIndicatorView(Context context) {
        this(context, null);
    }

    public CircleIndicatorView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleIndicatorView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleIndicatorView);
        indicatorRadius = typedArray.getDimension(R.styleable.CircleIndicatorView_indicator_radius, 4 * context.getResources().getDisplayMetrics().density);
        indicatorColor = typedArray.getColor(R.styleable.CircleIndicatorView_indicator_color, ContextCompat.getColor(context, android.R.color.holo_blue_dark));
        indicatorDistance = typedArray.getDimension(R.styleable.CircleIndicatorView_indicator_distance, 25 * context.getResources().getDisplayMetrics().density);
        indicatorWidth = typedArray.getDimension(R.styleable.CircleIndicatorView_indicator_width, 2.0F);
        typedArray.recycle();

        Paint paint = mPaint;
        //设置抗锯齿
        paint.setAntiAlias(true);
        //设置抗抖动
        paint.setDither(false);

        paint.setStrokeWidth(indicatorWidth);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        switch (heightMode) {
            case MeasureSpec.AT_MOST:
                heightSize = ViewTools.dp2Px(40F);
                break;
            case MeasureSpec.EXACTLY:
                break;
            case MeasureSpec.UNSPECIFIED:
                heightSize = ViewTools.dp2Px(40F);
                break;

        }
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), heightSize);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        viewWidth = w;
        populateFromPagerAdapter(false);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (null == mViewPager) {
            return;
        }
        if (selectPosition >= indicatorCount) {
            return;
        }
        //改变绘制的位置信息
        changeLocation();
        //绘制指示器圆
        drawIndicatorCircle(canvas);
        //绘制中间滑动时的指示圆
        drawSlideCircle(canvas);
        //绘制当前页面指示圆与滑动指示圆之间的连线
        drawBezier(canvas);
    }

    //绘制指示圆
    private void drawIndicatorCircle(Canvas canvas) {
        DotLocation[] indicators = this.indicators;
        Paint paint = mPaint;
        for (int i = 0; i < indicators.length; i++) {
            paint.setStyle(i == selectPosition ? Paint.Style.FILL_AND_STROKE : Paint.Style.STROKE);
            int paintColor = onSelectIndicatorColor.getSelectIndicatorColor(i);
            if (paintColor != 0) {
                paint.setColor(paintColor);
            }
            canvas.drawCircle(indicators[i].x, indicators[i].y, indicatorRadius, paint);
        }
    }

    //绘制滑动的圆
    private void drawSlideCircle(Canvas canvas) {
        int paintColor = onSelectIndicatorColor.getSelectIndicatorColor(selectPosition);
        mPaint.setColor(paintColor);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawCircle(lastX, indicators[0].y, indicatorRadius, mPaint);
    }

    //绘制滑动的圆和指示圆之间的连线
    private void drawBezier(Canvas canvas) {
        int paintColor = onSelectIndicatorColor.getSelectIndicatorColor(selectPosition);
        mPaint.setColor(paintColor);
        mPaint.setStyle(Paint.Style.FILL);
        Path path = mPath;
        DotLocation[] dotLocations = this.dotLocations;
        path.reset();
        path.moveTo(dotLocations[0].x, dotLocations[0].y);
        path.quadTo(dotLocations[4].x, dotLocations[4].y, dotLocations[2].x, dotLocations[2].y);
        path.lineTo(dotLocations[3].x, dotLocations[3].y);
        path.quadTo(dotLocations[4].x, dotLocations[4].y, dotLocations[1].x, dotLocations[1].y);
        canvas.drawPath(path, mPaint);
    }

    public void setOnSelectIndicatorColor(OnSelectIndicatorColor onSelectIndicatorColor) {
        this.onSelectIndicatorColor = onSelectIndicatorColor;
    }

    public void setupWithViewPager(ViewPager viewPage) {
        setupWithViewPager(viewPage, false);
    }

    public void setupWithViewPager(ViewPager viewPage, boolean implicitSetup) {
        if (this.mViewPager == viewPage) {
            return;
        }
        if (null != mViewPager) {
            if (null != mViewPageChangeListener) {
                mViewPager.removeOnPageChangeListener(mViewPageChangeListener);
            }
        }
        if (null != viewPage) {
            mViewPager = viewPage;
            if (null == mViewPageChangeListener) {
                mViewPageChangeListener = new ViewPageChangeListener();
            }
            mViewPager.addOnPageChangeListener(mViewPageChangeListener);
            final PagerAdapter adapter = mViewPager.getAdapter();
            if (null != adapter) {
                setPagerAdapter(adapter);
            }
            if (null == mAdapterChangeListener) {
                mAdapterChangeListener = new AdapterChangeListener();
            }
            mViewPager.addOnAdapterChangeListener(mAdapterChangeListener);
        } else {
            mViewPager = null;
            setPagerAdapter(null);
        }

        mSetupViewPagerImplicitly = implicitSetup;
    }

    private void setPagerAdapter(PagerAdapter pagerAdapter) {
        if (null != mPagerAdapter && null != mPagerAdapterObserver) {
            mPagerAdapter.unregisterDataSetObserver(mPagerAdapterObserver);
        }
        mPagerAdapter = pagerAdapter;
        if (pagerAdapter != null) {
            if (null == mPagerAdapterObserver) {
                mPagerAdapterObserver = new PagerAdapterObserver();
            }
            pagerAdapter.registerDataSetObserver(mPagerAdapterObserver);
        }

        populateFromPagerAdapter();
    }

    private void populateFromPagerAdapter() {
        populateFromPagerAdapter(true);
    }

    /**
     * 根据adapter来初始化指示器的位置信息 同时根据viewWidth来判断页面是否加载完成，如果值为-1说明页面没有加载完成
     * 不进行初始化,在{@link #onSizeChanged(int, int, int, int)} 中进行初始化
     *
     * @param isInvalidate 是否重绘页面
     */
    private void populateFromPagerAdapter(boolean isInvalidate) {
        if (viewWidth != -1 && null != mPagerAdapter) {
            indicatorCount = mPagerAdapter.getCount();
            leftAndRightDistance = (viewWidth - getPaddingLeft() - getPaddingRight() - indicatorDistance * (indicatorCount - 1)) / 2;
            DotLocation[] indicators = this.indicators;
            if (null == indicators || indicatorCount > indicators.length) {
                indicators = new DotLocation[indicatorCount];
                for (int i = 0; i < indicators.length; i++) {
                    indicators[i] = new DotLocation(leftAndRightDistance + indicatorDistance * i, getHeight() / 2);
                }
            } else {
                for (int i = 0; i < indicatorCount; i++) {
                    indicators[i].setXY(leftAndRightDistance + indicatorDistance * i, getHeight() / 2);
                }

            }
            lastX = indicators[0].x;
            this.indicators = indicators;
            if (null == onSelectIndicatorColor) {
                setOnSelectIndicatorColor(new DefaultOnSelectIndicatorColor());
            }
            if (isInvalidate) {
                invalidate();
            }
        }
    }

    private void dotLocations() {
        if (null == dotLocations) {
            dotLocations = new DotLocation[]{
                    new DotLocation(0, 0), new DotLocation(0, 0), new DotLocation(0, 0),
                    new DotLocation(0, 0), new DotLocation(0, 0), new DotLocation(0, 0),
                    new DotLocation(0, 0),
            };
        }
    }


    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (null == mViewPager) {
            final ViewParent parent = getParent();
            if (parent instanceof ViewPager) {
                setupWithViewPager((ViewPager) parent);
            }
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mSetupViewPagerImplicitly) {
            setupWithViewPager(null);
            mSetupViewPagerImplicitly = false;
        }
    }

    //改变绘制点的位置信息
    private void changeLocation() {
        dotLocations();
        DotLocation[] dotLocations = this.dotLocations;
        DotLocation selectIndicator = this.indicators[selectPosition];
        dotLocations[0].setXY(selectIndicator.x, selectIndicator.y - indicatorRadius);
        dotLocations[1].setXY(selectIndicator.x, selectIndicator.y + indicatorRadius);
        dotLocations[2].setXY(lastX, selectIndicator.y - indicatorRadius);
        dotLocations[3].setXY(lastX, selectIndicator.y + indicatorRadius);
        dotLocations[4].setXY(selectIndicator.x + (lastX - selectIndicator.x) / 2, selectIndicator.y);
    }

    //用来监听ViewPage的Adapter数据发生变化
    private class PagerAdapterObserver extends DataSetObserver {
        PagerAdapterObserver() {
        }

        @Override
        public void onChanged() {
            populateFromPagerAdapter();
        }

        @Override
        public void onInvalidated() {
            populateFromPagerAdapter();
        }
    }

    private class ViewPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            lastX = leftAndRightDistance + indicatorDistance * (positionOffset + position);
            invalidate();
        }

        @Override
        public void onPageSelected(int position) {
            if (selectPosition == position) {
                return;
            }
            selectPosition = position;
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    private class AdapterChangeListener implements ViewPager.OnAdapterChangeListener {

        @Override
        public void onAdapterChanged(@NonNull ViewPager viewPager, @Nullable PagerAdapter oldAdapter,
                                     @Nullable PagerAdapter newAdapter) {
            if (mViewPager == viewPager) {
                setPagerAdapter(newAdapter);
            }
        }
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

    private class DefaultOnSelectIndicatorColor implements OnSelectIndicatorColor {

        @Override
        public int getSelectIndicatorColor(int position) {
            return indicatorColor;
        }
    }


    public interface OnSelectIndicatorColor {
        int getSelectIndicatorColor(int position);
    }
}

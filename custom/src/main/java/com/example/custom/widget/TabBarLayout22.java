package com.example.custom.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.Pools;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.SoundEffectConstants;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.custom.R;

import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created on 2018/3/15
 * Title: 标题选择布局
 * Description: 主要用于标题选择 使用方法和tabLayout一致
 * author 张康
 * update 2018/3/15
 */
public class TabBarLayout22 extends LinearLayout {
    ViewPager mViewPager;
    private PagerAdapter mPagerAdapter;
    private DataSetObserver mPagerAdapterObserver;
    private TabBarLayoutOnPageChangeListener mPageChangeListener;
    private boolean mSetupViewPagerImplicitly;

    // Pool we use as a simple RecyclerBin
    private final Pools.Pool<TabBarView> mTabBarViewPool = new Pools.SimplePool<>(12);
    private static final Pools.Pool<TabBar> sTabBarPool = new Pools.SynchronizedPool<>(16);

    private final ArrayList<OnTabBarSelectedListener> mSelectedListeners = new ArrayList<>();
    private OnTabBarSelectedListener mCurrentVpSelectedListener;

    private final ArrayList<TabBar> mTabBars = new ArrayList<>();
    private TabBar mSelectedTabBar;
    final int mTabBarBackgroundResId;
    final int mTabBarLeftBackgroundResId;
    final int mTabBarRightBackgroundResId;
    ColorStateList mTabBarColors;
    float mTabBarTextSize;
    boolean mTabBarTextBold;


    private int mBackgroundStrokeWidth;
    private float[] mBackgroundRadiusArray;

    public TabBarLayout22(Context context) {
        this(context, null);
    }

    public TabBarLayout22(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TabBarLayout22(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        Drawable background = getBackground();
        if (background instanceof GradientDrawable) {
            GradientDrawable gradientDrawable = (GradientDrawable) background;
            Class<? extends GradientDrawable> aClass = gradientDrawable.getClass();
            try {
                Field mGradientState = aClass.getDeclaredField("mGradientState");
                mGradientState.setAccessible(true);
                Object o1 = mGradientState.get(gradientDrawable);
                Class<?> aClass1 = o1.getClass();
                Field mStrokeWidth = aClass1.getDeclaredField("mStrokeWidth");
                mStrokeWidth.setAccessible(true);
                mBackgroundStrokeWidth = (int) mStrokeWidth.get(o1);
                Field mRadiusArray = aClass1.getDeclaredField("mRadiusArray");
                mRadiusArray.setAccessible(true);
                mBackgroundRadiusArray = (float[]) mRadiusArray.get(o1);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TabBarLayout);
        mTabBarBackgroundResId = typedArray.getResourceId(R.styleable.TabBarLayout_tabBarBackground, 0);
        mTabBarLeftBackgroundResId = typedArray.getResourceId(R.styleable.TabBarLayout_tabBarLeftBackground, 0);
        mTabBarRightBackgroundResId = typedArray.getResourceId(R.styleable.TabBarLayout_tabBarRightBackground, 0);

        if (typedArray.hasValue(R.styleable.TabBarLayout_tabBarTextColor)) {
            // If we have an explicit text color set, use it instead
            mTabBarColors = typedArray.getColorStateList(R.styleable.TabBarLayout_tabBarTextColor);
        }

        if (typedArray.hasValue(R.styleable.TabBarLayout_tabBarSelectedTextColor)) {
            // We have an explicit selected text color set, so we need to make merge it with the
            // current colors. This is exposed so that developers can use theme attributes to set
            // this (theme attrs in ColorStateLists are Lollipop+)
            final int selected = typedArray.getColor(R.styleable.TabBarLayout_tabBarSelectedTextColor, 0);
            mTabBarColors = createColorStateList(mTabBarColors.getDefaultColor(), selected);
        }
        mTabBarTextSize = typedArray.getDimensionPixelSize(R.styleable.TabBarLayout_tabBarTextSize, 0);
        mTabBarTextBold = typedArray.getBoolean(R.styleable.TabBarLayout_tabBarTextBold, false);

        typedArray.recycle();
        if (mBackgroundStrokeWidth > 0) {
            setPadding(getPaddingLeft() + mBackgroundStrokeWidth, getPaddingTop() + mBackgroundStrokeWidth, getRight() + mBackgroundStrokeWidth, getBottom() + mBackgroundStrokeWidth);
        }
    }

    public void setupWithViewPager(@Nullable ViewPager viewPager) {
        setupWithViewPager(viewPager, true);
    }

    public void setupWithViewPager(@Nullable final ViewPager viewPager, boolean autoRefresh) {
        setupWithViewPager(viewPager, autoRefresh, false);
    }

    private void setupWithViewPager(@Nullable final ViewPager viewPager, boolean autoRefresh,
                                    boolean implicitSetup) {
        if (mViewPager != null) {
            // If we've already been setup with a ViewPager, remove us from it
            if (mPageChangeListener != null) {
                mViewPager.removeOnPageChangeListener(mPageChangeListener);
            }

        }

        if (mCurrentVpSelectedListener != null) {
            // If we already have a tab selected listener for the ViewPager, remove it
            removeOnTabBarSelectedListener(mCurrentVpSelectedListener);
            mCurrentVpSelectedListener = null;
        }

        if (viewPager != null) {
            mViewPager = viewPager;

            // Add our custom OnPageChangeListener to the ViewPager
            if (mPageChangeListener == null) {
                mPageChangeListener = new TabBarLayoutOnPageChangeListener(this);
            }
            mPageChangeListener.reset();
            viewPager.addOnPageChangeListener(mPageChangeListener);

            // Now we'll add a tab selected listener to set ViewPager's current item
            mCurrentVpSelectedListener = new ViewPagerOnTabSelectedListener(viewPager);
            addOnTabBarSelectedListener(mCurrentVpSelectedListener);

            final PagerAdapter adapter = viewPager.getAdapter();
            if (adapter != null) {
                // Now we'll populate ourselves from the pager adapter, adding an observer if
                // autoRefresh is enabled
                setPagerAdapter(adapter, autoRefresh);
            }


        } else {
            // We've been given a null ViewPager so we need to clear out the internal state,
            // listeners and observers
            mViewPager = null;
            setPagerAdapter(null, false);
        }

        mSetupViewPagerImplicitly = implicitSetup;
    }

    public void addOnTabBarSelectedListener(@NonNull OnTabBarSelectedListener listener) {
        if (!mSelectedListeners.contains(listener)) {
            mSelectedListeners.add(listener);
        }
    }


    public void removeOnTabBarSelectedListener(@NonNull OnTabBarSelectedListener listener) {
        mSelectedListeners.remove(listener);
    }

    public void clearOnTabBarSelectedListeners() {
        mSelectedListeners.clear();
    }

    void setPagerAdapter(@Nullable final PagerAdapter adapter, final boolean addObserver) {
        if (mPagerAdapter != null && mPagerAdapterObserver != null) {
            // If we already have a PagerAdapter, unregister our observer
            mPagerAdapter.unregisterDataSetObserver(mPagerAdapterObserver);
        }

        mPagerAdapter = adapter;

        if (addObserver && adapter != null) {
            // Register our observer on the new adapter
            if (mPagerAdapterObserver == null) {
                mPagerAdapterObserver = new PagerAdapterObserver();
            }
            adapter.registerDataSetObserver(mPagerAdapterObserver);
        }

        // Finally make sure we reflect the new adapter
        populateFromPagerAdapter();
    }

    void populateFromPagerAdapter() {
        removeAllTabs();

        if (mPagerAdapter != null) {
            final int adapterCount = mPagerAdapter.getCount();
            for (int i = 0; i < adapterCount; i++) {
                addTab(newTab().setText(mPagerAdapter.getPageTitle(i)), false);
            }
            if (mBackgroundStrokeWidth > 0 && null != mBackgroundRadiusArray) {
                updateTabBarBackground();
            }


            // Make sure we reflect the currently set ViewPager item
            if (mViewPager != null && adapterCount > 0) {
                final int curItem = mViewPager.getCurrentItem();
                if (curItem != getSelectedTabPosition() && curItem < getTabBarCount()) {
                    selectTab(getTabBarAt(curItem));
                }
            }
        }
    }

    private void updateTabBarBackground() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (i == 0 && mTabBarLeftBackgroundResId != 0) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    childAt.setBackground(ContextCompat.getDrawable(getContext(), mTabBarLeftBackgroundResId));
                } else {
                    childAt.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), mTabBarLeftBackgroundResId));
                }
            } else if (i == childCount - 1 && mTabBarRightBackgroundResId != 0) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    childAt.setBackground(ContextCompat.getDrawable(getContext(), mTabBarRightBackgroundResId));
                } else {
                    childAt.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), mTabBarRightBackgroundResId));
                }
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    childAt.setBackground(ContextCompat.getDrawable(getContext(), mTabBarBackgroundResId));
                } else {
                    childAt.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), mTabBarBackgroundResId));
                }
            }
        }
    }

    public int getTabBarCount() {
        return mTabBars.size();
    }

    @Nullable
    public TabBar getTabBarAt(int index) {
        return (index < 0 || index >= getTabBarCount()) ? null : mTabBars.get(index);
    }

    /**
     * Remove all tabs from the action bar and deselect the current tab.
     */
    public void removeAllTabs() {
        // Remove all the views
        for (int i = getChildCount() - 1; i >= 0; i--) {
            removeTabViewAt(i);
        }

        for (final Iterator<TabBar> i = mTabBars.iterator(); i.hasNext(); ) {
            final TabBar tabBar = i.next();
            i.remove();
            tabBar.reset();
            sTabBarPool.release(tabBar);
        }

        mSelectedTabBar = null;
    }


    public void addTab(@NonNull TabBar tabBar, boolean setSelected) {
        addTab(tabBar, mTabBars.size(), setSelected);
    }


    public void addTab(@NonNull TabBar tabBar, int position, boolean setSelected) {
        if (tabBar.mParent != this) {
            throw new IllegalArgumentException("Tab belongs to a different TabLayout.");
        }
        configureTab(tabBar, position);
        addTabView(tabBar);

        if (setSelected) {
            tabBar.select();
        }
    }

    private void configureTab(TabBar tabBar, int position) {
        tabBar.setPosition(position);
        mTabBars.add(position, tabBar);

        final int count = mTabBars.size();
        for (int i = position + 1; i < count; i++) {
            mTabBars.get(i).setPosition(i);
        }
    }

    private void addTabView(TabBar tabBar) {
        final TabBarView tabView = tabBar.mView;
        addView(tabView, tabBar.getPosition(), createLayoutParamsForTabs());
    }

    private LayoutParams createLayoutParamsForTabs() {
        final LayoutParams lp = new LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.MATCH_PARENT);
        updateTabViewLayoutParams(lp);
        return lp;
    }

    private void updateTabViewLayoutParams(LayoutParams lp) {
        lp.width = 0;
        lp.weight = 1;
    }

    @NonNull
    public TabBar newTab() {
        TabBar tabBar = sTabBarPool.acquire();
        if (tabBar == null) {
            tabBar = new TabBar();
        }
        tabBar.mParent = this;
        tabBar.mView = createTabView(tabBar);
        return tabBar;
    }

    private TabBarView createTabView(@NonNull final TabBar tabBar) {
        TabBarView tabBarView = mTabBarViewPool != null ? mTabBarViewPool.acquire() : null;
        if (tabBarView == null) {
            tabBarView = new TabBarView(getContext());
        }
        tabBarView.setTabBar(tabBar);
        tabBarView.setFocusable(true);
        return tabBarView;
    }

    private void removeTabViewAt(int position) {
        final TabBarView view = (TabBarView) getChildAt(position);
        removeViewAt(position);
        if (view != null) {
            view.reset();
            mTabBarViewPool.release(view);
        }
        requestLayout();
    }


    void selectTab(final TabBar tabBar) {
        final TabBar currentTabBar = mSelectedTabBar;

        if (currentTabBar == tabBar) {
            if (currentTabBar != null) {
                dispatchTabReselected(tabBar);
            }
        } else {
            final int newPosition = tabBar != null ? tabBar.getPosition() : TabBar.INVALID_POSITION;
            if (newPosition != TabLayout.Tab.INVALID_POSITION) {
                setSelectedTabView(newPosition);
            }
            if (currentTabBar != null) {
                dispatchTabUnselected(currentTabBar);
            }
            mSelectedTabBar = tabBar;
            if (tabBar != null) {
                dispatchTabSelected(tabBar);
            }
        }
    }

    private void setSelectedTabView(int position) {
        final int tabCount = getChildCount();
        if (position < tabCount) {
            for (int i = 0; i < tabCount; i++) {
                final View child = getChildAt(i);
                child.setSelected(i == position);
            }
        }
    }

    private void dispatchTabSelected(@NonNull final TabBar tabBar) {
        for (int i = mSelectedListeners.size() - 1; i >= 0; i--) {
            mSelectedListeners.get(i).onTabBarSelected(tabBar);
        }
    }

    private void dispatchTabUnselected(@NonNull final TabBar tabBar) {
        for (int i = mSelectedListeners.size() - 1; i >= 0; i--) {
            mSelectedListeners.get(i).onTabBarUnselected(tabBar);
        }
    }

    private void dispatchTabReselected(@NonNull final TabBar tabBar) {
        for (int i = mSelectedListeners.size() - 1; i >= 0; i--) {
            mSelectedListeners.get(i).onTabBarReselected(tabBar);
        }
    }

    public int getSelectedTabPosition() {
        return mSelectedTabBar != null ? mSelectedTabBar.getPosition() : -1;
    }


    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();

        if (mSetupViewPagerImplicitly) {
            // If we've been setup with a ViewPager implicitly, let's clear out any listeners, etc
            setupWithViewPager(null);
            mSetupViewPagerImplicitly = false;
        }
    }

    private static ColorStateList createColorStateList(int defaultColor, int selectedColor) {
        final int[][] states = new int[2][];
        final int[] colors = new int[2];
        int i = 0;

        states[i] = SELECTED_STATE_SET;
        colors[i] = selectedColor;
        i++;

        // Default enabled state
        states[i] = EMPTY_STATE_SET;
        colors[i] = defaultColor;
        i++;

        return new ColorStateList(states, colors);
    }

    class TabBarView extends AppCompatTextView {
        private TabBar mTabBar;

        public TabBarView(Context context) {
            super(context);
            if (mTabBarBackgroundResId != 0) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    setBackground(ContextCompat.getDrawable(getContext(), mTabBarBackgroundResId));
                } else {
                    setBackgroundDrawable(ContextCompat.getDrawable(getContext(), mTabBarBackgroundResId));
                }
            }
            setGravity(Gravity.CENTER);
            setClickable(true);
        }

        @Override
        public boolean performClick() {
            final boolean handled = super.performClick();
            if (mTabBar != null) {
                if (!handled) {
                    playSoundEffect(SoundEffectConstants.CLICK);
                }
                mTabBar.select();
                return true;
            } else {
                return handled;
            }
        }

        @Override
        public void setSelected(final boolean selected) {
            final boolean changed = isSelected() != selected;
            super.setSelected(selected);
            if (changed && selected && Build.VERSION.SDK_INT < 16) {
                // Pre-JB we need to manually send the TYPE_VIEW_SELECTED event
                sendAccessibilityEvent(AccessibilityEvent.TYPE_VIEW_SELECTED);
            }

        }


        void setTabBar(@Nullable final TabBar tabBar) {
            if (tabBar != mTabBar) {
                mTabBar = tabBar;
                update();
            }
        }

        void reset() {
            setTabBar(null);
            setSelected(false);
        }

        final void update() {
            final TabBar tabBar = mTabBar;
            updateTextAndIcon(this);
            // Finally update our selected state
            setSelected(tabBar != null && tabBar.isSelected());
        }

        private void updateTextAndIcon(@Nullable final TextView textView) {
            final CharSequence text = mTabBar != null ? mTabBar.getText() : null;
            final boolean hasText = !TextUtils.isEmpty(text);
            if (textView != null) {
                if (hasText) {
                    textView.setText(text);
                    textView.setVisibility(VISIBLE);
                    setVisibility(VISIBLE);
                } else {
                    textView.setVisibility(GONE);
                    textView.setText(null);
                }
                if (mTabBarColors != null) {
                    textView.setTextColor(mTabBarColors);
                }
                if (mTabBarTextSize > 0) {
                    textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTabBarTextSize);
                } else {
                    textView.setTextSize(17);
                }
                if (mTabBarTextBold) {
                    textView.setTypeface(Typeface.DEFAULT_BOLD);
                } else {
                    textView.setTypeface(Typeface.DEFAULT);
                }
            }
        }

        public TabBar getTabBar() {
            return mTabBar;
        }

    }

    public final class TabBar {
        /**
         * An invalid position for a tab.
         *
         * @see #getPosition()
         */
        public static final int INVALID_POSITION = -1;

        private Object mTag;
        private CharSequence mText;
        private CharSequence mContentDesc;
        private int mPosition = INVALID_POSITION;

        TabBarLayout22 mParent;
        TabBarLayout22.TabBarView mView;

        TabBar() {
            // Private constructor
        }

        /**
         * @return This Tab's tag object.
         */
        @Nullable
        public Object getTag() {
            return mTag;
        }

        /**
         * Give this Tab an arbitrary object to hold for later use.
         *
         * @param tag Object to store
         * @return The current instance for call chaining
         */
        @NonNull
        public TabBar setTag(@Nullable Object tag) {
            mTag = tag;
            return this;
        }


        /**
         * Return the current position of this tab in the action bar.
         *
         * @return Current position, or {@link #INVALID_POSITION} if this tab is not currently in
         * the action bar.
         */
        public int getPosition() {
            return mPosition;
        }

        void setPosition(int position) {
            mPosition = position;
        }

        /**
         * Return the text of this tab.
         *
         * @return The tab's text
         */
        @Nullable
        public CharSequence getText() {
            return mText;
        }


        /**
         * Set the text displayed on this tab. Text may be truncated if there is not room to display
         * the entire string.
         *
         * @param text The text to display
         * @return The current instance for call chaining
         */
        @NonNull
        public TabBar setText(@Nullable CharSequence text) {
            mText = text;
            updateView();
            return this;
        }

        /**
         * Set the text displayed on this tab. Text may be truncated if there is not room to display
         * the entire string.
         *
         * @param resId A resource ID referring to the text that should be displayed
         * @return The current instance for call chaining
         */
        @NonNull
        public TabBar setText(@StringRes int resId) {
            if (mParent == null) {
                throw new IllegalArgumentException("Tab not attached to a TabLayout");
            }
            return setText(mParent.getResources().getText(resId));
        }

        /**
         * Select this tab. Only valid if the tab has been added to the action bar.
         */
        public void select() {
            if (mParent == null) {
                throw new IllegalArgumentException("Tab not attached to a TabLayout");
            }
            mParent.selectTab(this);
        }

        /**
         * Returns true if this tab is currently selected.
         */
        public boolean isSelected() {
            if (mParent == null) {
                throw new IllegalArgumentException("Tab not attached to a TabLayout");
            }
            return mParent.getSelectedTabPosition() == mPosition;
        }

        /**
         * Set a description of this tab's content for use in accessibility support. If no content
         * description is provided the title will be used.
         *
         * @param resId A resource ID referring to the description text
         * @return The current instance for call chaining
         * @see #setContentDescription(CharSequence)
         * @see #getContentDescription()
         */
        @NonNull
        public TabBar setContentDescription(@StringRes int resId) {
            if (mParent == null) {
                throw new IllegalArgumentException("Tab not attached to a TabLayout");
            }
            return setContentDescription(mParent.getResources().getText(resId));
        }

        /**
         * Set a description of this tab's content for use in accessibility support. If no content
         * description is provided the title will be used.
         *
         * @param contentDesc Description of this tab's content
         * @return The current instance for call chaining
         * @see #setContentDescription(int)
         * @see #getContentDescription()
         */
        @NonNull
        public TabBar setContentDescription(@Nullable CharSequence contentDesc) {
            mContentDesc = contentDesc;
            updateView();
            return this;
        }

        /**
         * Gets a brief description of this tab's content for use in accessibility support.
         *
         * @return Description of this tab's content
         * @see #setContentDescription(CharSequence)
         * @see #setContentDescription(int)
         */
        @Nullable
        public CharSequence getContentDescription() {
            return mContentDesc;
        }

        void updateView() {
            if (mView != null) {
                mView.update();
            }
        }

        void reset() {
            mParent = null;
            mView = null;
            mTag = null;
            mText = null;
            mContentDesc = null;
            mPosition = INVALID_POSITION;
        }
    }


    public static class ViewPagerOnTabSelectedListener implements OnTabBarSelectedListener {
        private final ViewPager mViewPager;

        public ViewPagerOnTabSelectedListener(ViewPager viewPager) {
            mViewPager = viewPager;
        }

        @Override
        public void onTabBarSelected(TabBar tabBar) {
            mViewPager.setCurrentItem(tabBar.getPosition());
        }

        @Override
        public void onTabBarUnselected(TabBar tabBar) {
            // No-op
        }

        @Override
        public void onTabBarReselected(TabBar tabBar) {
            // No-op
        }
    }

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

    public static class TabBarLayoutOnPageChangeListener implements ViewPager.OnPageChangeListener {
        private final WeakReference<TabBarLayout22> mTabBarLayoutRef;

        public TabBarLayoutOnPageChangeListener(TabBarLayout22 TabBarLayout) {
            mTabBarLayoutRef = new WeakReference<>(TabBarLayout);
        }

        @Override
        public void onPageScrollStateChanged(final int state) {
        }

        @Override
        public void onPageScrolled(final int position, final float positionOffset,
                                   final int positionOffsetPixels) {


        }

        @Override
        public void onPageSelected(final int position) {
            final TabBarLayout22 tabBarLayout = mTabBarLayoutRef.get();
            if (tabBarLayout != null && tabBarLayout.getSelectedTabPosition() != position
                    && position < tabBarLayout.getTabBarCount()) {
                tabBarLayout.selectTab(tabBarLayout.getTabBarAt(position));
            }
        }

        void reset() {
        }
    }

    /**
     * Callback interface invoked when a tab's selection state changes.
     */
    public interface OnTabBarSelectedListener {

        /**
         * Called when a tabBar enters the selected state.
         *
         * @param tabBar The tabBar that was selected
         */
        void onTabBarSelected(TabBar tabBar);

        /**
         * Called when a tabBar exits the selected state.
         *
         * @param tabBar The tabBar that was unselected
         */
        void onTabBarUnselected(TabBar tabBar);

        /**
         * Called when a tabBar that is already selected is chosen again by the user. Some applications
         * may use this action to return to the top level of a category.
         *
         * @param tabBar The tabBar that was reselected.
         */
        void onTabBarReselected(TabBar tabBar);
    }

    private static class ViewCompatHelper {

        public static void setBackground() {

        }
    }
}

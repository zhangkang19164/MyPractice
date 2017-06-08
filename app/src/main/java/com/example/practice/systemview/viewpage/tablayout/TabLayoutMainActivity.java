package com.example.practice.systemview.viewpage.tablayout;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.practice.R;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TabLayoutMainActivity extends Activity {

    @BindView(R.id.tablayout_tablayout)
    TabLayout tablayoutTablayout;
    @BindView(R.id.tablayout_viewpage)
    ViewPager tablayoutViewpage;

    //    private String[] tabs = new String[]{"沪深", "板块", "沪港通", "深港通", "港股", "环球", "更多",};
    private String[] tabs = new String[]{"沪深", "板块",};
    private List<View> views = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout_main);
        ButterKnife.bind(this);
        initViews();
    }

    private void initViews() {
        for (String tab : tabs) {
            TextView t = new TextView(this);
            t.setGravity(Gravity.CENTER);
            t.setText(tab);
            t.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);
            t.setTextColor(ContextCompat.getColor(this, R.color._1E1E1E));
            t.setBackgroundColor(ContextCompat.getColor(this, R.color._3C9FFC));
            views.add(t);
        }
        tablayoutViewpage.setAdapter(pagerAdapter);
        //设置显示方式
        tablayoutTablayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        //和ViewPager关联
        tablayoutTablayout.setupWithViewPager(tablayoutViewpage);
        setIndicator(tablayoutTablayout, 40, 40);
    }

    private PagerAdapter pagerAdapter = new PagerAdapter() {
        @Override
        public int getCount() {
            return views.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabs[position];
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(views.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(views.get(position));
            return views.get(position);
        }
    };

    public void setIndicator(TabLayout tabs, int leftDip, int rightDip) {
        Class<?> tabLayout = tabs.getClass();
        Field tabStrip = null;
        try {
            tabStrip = tabLayout.getDeclaredField("mTabStrip");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        tabStrip.setAccessible(true);
        LinearLayout llTab = null;
        try {
            llTab = (LinearLayout) tabStrip.get(tabs);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, leftDip, Resources.getSystem().getDisplayMetrics());
        int right = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rightDip, Resources.getSystem().getDisplayMetrics());

        for (int i = 0; i < llTab.getChildCount(); i++) {
            View child = llTab.getChildAt(i);
            child.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            params.leftMargin = left;
            params.rightMargin = right;
            child.setLayoutParams(params);
            child.invalidate();
        }
    }
}
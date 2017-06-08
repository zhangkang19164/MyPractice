package com.example.practice.systemview.viewpage.indicator;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.practice.R;

import java.util.ArrayList;
import java.util.List;

public class ViewPageIndicatorMainActivity extends AppCompatActivity {
    private static final String TAG = "ViewPageIndicatorMain";
    private String[] strings = new String[]{"沪深", "板块", "沪港通", "深港通", "港股", "环球", "更多"};

    private List<TextView> views = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_page_indicator_main);
        initDatas();
        initViews();
    }

    private void initDatas() {
        for (String string : strings) {
            TextView textView = new TextView(this);
            textView.setBackgroundColor(ContextCompat.getColor(this, android.R.color.white));
            textView.setGravity(Gravity.CENTER);
            textView.setText(string);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);
            views.add(textView);
        }
    }

    private void initViews() {
        final Indicator indicator = (Indicator) findViewById(R.id.indictor_indiator);
        ViewPager viewPager = (ViewPager) findViewById(R.id.indictor_viewpage);
        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return views.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                TextView textView = views.get(position);
                container.addView(textView);
                return textView;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(views.get(position));
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                indicator.setPageScrolled(position,positionOffset);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}

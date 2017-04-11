package com.example.practice.systemview.viewpage.indicator;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.practice.R;

import java.util.ArrayList;
import java.util.List;

public class ViewPageIndicatorMainActivity extends AppCompatActivity {
    private String[] strings = new String[]{"短信", "收藏", "推荐"};

    private List<ViewPageIndicatorFragment> views = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_page_indicator_main);
        initDatas();
        initViews();
    }

    private void initDatas() {
        for (String string : strings) {
            ViewPageIndicatorFragment fragment = ViewPageIndicatorFragment.newIntance(string);
            views.add(fragment);
        }
    }

    private void initViews() {
        Indicator indicator = (Indicator) findViewById(R.id.indictor_indiator);
        ViewPager viewPager = (ViewPager) findViewById(R.id.indictor_viewpage);
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return views.get(position);
            }

            @Override
            public int getCount() {
                return views.size();
            }
        });
    }
}

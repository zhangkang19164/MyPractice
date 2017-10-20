package com.example.system.fragment;

import android.databinding.DataBindingUtil;
import android.support.v4.app.*;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.system.R;
import com.example.system.databinding.ActivityFragmentMainBinding;

import java.util.ArrayList;
import java.util.List;

public class FragmentMainActivity extends AppCompatActivity {
    private ActivityFragmentMainBinding binding;
    private String[] titles;
    private List<AbstractFragment> fragments;
    private AbstractFragment lastFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_fragment_main);
        initView();
    }

    public void initView() {
        titles = new String[]{"标题1", "标题2", "标题2"};
        fragments = new ArrayList<>();
        fragments.add(AbstractFragment.newInstance(AbstractFragment.TAG_1, true));
        fragments.add(AbstractFragment.newInstance(AbstractFragment.TAG_2, false));
        fragments.add(AbstractFragment.newInstance(AbstractFragment.TAG_3, false));
        binding.viewPage.setAdapter(new MyAdapter(getSupportFragmentManager()));

        binding.viewPage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                lastFragment.reset();
                lastFragment=fragments.get(position);
                lastFragment.requestData();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        lastFragment=fragments.get(0);
    }

    private class MyAdapter extends android.support.v4.app.FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }

}

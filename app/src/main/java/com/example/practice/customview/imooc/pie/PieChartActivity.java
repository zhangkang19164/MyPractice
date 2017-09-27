package com.example.practice.customview.imooc.pie;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.practice.R;
import com.example.practice.customview.imooc.pie.fragment.PieChartFragment;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.TypeVariable;
import java.util.ArrayList;

public class PieChartActivity extends AppCompatActivity {

    private ViewPager viewPage;
    private String json = "[{\"date\":\"2016年5月\",\"obj\":[{\"title\":\"外卖\",\"value\":34},{\"title\":\"娱乐\",\"value\":21},{\"title\":\"其他\",\"value\":45}]},{\"date\":\"2016年6月\",\"obj\":[{\"title\":\"外卖\",\"value\":32},{\"title\":\"娱乐\",\"value\":22},{\"title\":\"其他\",\"value\":42}]}]";
    private ArrayList<PieChartBean> fromJson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);
        //.cast 类型转换快捷键
        //.filed 创建变量快捷键
        viewPage = ((ViewPager) findViewById(R.id.vp_pie_chart));
        initData();
        initView();
    }

    private void initData() {
        Gson gson = new Gson();
        fromJson = gson.fromJson(json, new TypeToken<ArrayList<PieChartBean>>() {
        }.getType());
    }

    private void initView() {
        viewPage.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return PieChartFragment.newInstance(fromJson.get(position));
            }

            @Override
            public int getCount() {
                return fromJson.size();
            }
        });
    }
}

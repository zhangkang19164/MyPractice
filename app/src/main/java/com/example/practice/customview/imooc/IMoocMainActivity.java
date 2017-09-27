package com.example.practice.customview.imooc;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.practice.R;
import com.example.practice.customview.imooc.pie.PieChartActivity;

/**
 * 慕课网学习相关样例
 */
public class IMoocMainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imooc_main);
        //.cast 转换类型
        //.field 创建变量
    }

    public void onViewClick(View view) {
        Class<? extends Activity> toClass = null;
        switch (view.getId()) {
            case R.id.to_pie:
                toClass = PieChartActivity.class;
                break;
        }
        startActivity(new Intent(view.getContext(), toClass));
    }

}

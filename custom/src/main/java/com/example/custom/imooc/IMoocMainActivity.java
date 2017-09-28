package com.example.custom.imooc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.custom.R;
import com.example.custom.imooc.pie.PieChartActivity;


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
        int i = view.getId();
        if (i == R.id.to_pie) {
            toClass = PieChartActivity.class;
        }
        startActivity(new Intent(view.getContext(), toClass));
    }

}

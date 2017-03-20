package com.example.practice.customview.linearlayoutcompat;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.practice.R;
import com.example.practice.customview.linearlayoutcompat.view.MyLinearLayoutCompat;

public class LinearLayoutCompatMainActivity extends AppCompatActivity {
    private String[] strings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear_layout_compat_main);
        initData();
        initView();
    }

    private void initData() {
        strings = new String[]{"0", "1", "2","3", "4",};
    }

    private void initView() {
        MyLinearLayoutCompat myLinearLayoutCompat = (MyLinearLayoutCompat) findViewById(R.id.mylinearlayoutcompat);
        myLinearLayoutCompat.setHorizontalDivider(ContextCompat.getDrawable(this,R.drawable.zhishuquto_divider));
        myLinearLayoutCompat.setVerticalDivider(ContextCompat.getDrawable(this,R.drawable.zhishuquto_divider_2));
        myLinearLayoutCompat.setCallBack(new MyLinearLayoutCompat.CallBack() {
            @Override
            public int getChildViewCount() {
                return strings.length;
            }

            @Override
            public int getColumnWidth() {
                return 3;
            }

            @Override
            public View getView(int position, ViewGroup parent) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my_linearlayoutcompat, parent, false);
                TextView textView = (TextView) view.findViewById(R.id.item_my_linearlayoutcompat_text);
                textView.setText(strings[position]);
                return view;
            }
        });
    }
}

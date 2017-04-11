package com.example.practice.systemview.popupwindow.custom;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.practice.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CustomPopupWindowMainActivity extends AppCompatActivity {

    @BindView(R.id.button_01)
    Button button01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_popup_window_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.button_01,})
    public void onViewClicked(View view) {

    }

    private void button01(View view) {
    }
}

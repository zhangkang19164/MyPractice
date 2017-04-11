package com.example.practice.customview.detail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.example.practice.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailMainActivity extends AppCompatActivity {

    @BindView(R.id.dialog)
    Button dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.dialog)
    public void onViewClicked() {
    }
}

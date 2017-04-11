package com.example.practice.designpattern;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.practice.MainActivity;
import com.example.practice.R;
import com.example.practice.customview.CustomMainActivity;
import com.example.practice.dependencies.DependenciesMainActivity;
import com.example.practice.designpattern.refresh.RefreshMainActivity;
import com.example.practice.systemview.SystemViewMainActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class DesignPatternMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design_pattern_main);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.to_refresh,

    })
    public void onViewClicked(View view) {
        Class<? extends Activity> toActivity;
        switch (view.getId()) {

            case R.id.to_refresh:
                toActivity = RefreshMainActivity.class;
                break;

            default:
                toActivity = MainActivity.class;
                break;
        }
        startActivity(new Intent(view.getContext(), toActivity));
    }
}

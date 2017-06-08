package com.example.practice.other;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.practice.MainActivity;
import com.example.practice.R;
import com.example.practice.customview.CustomMainActivity;
import com.example.practice.dependencies.DependenciesMainActivity;
import com.example.practice.designpattern.DesignPatternMainActivity;
import com.example.practice.other.bezier.BezierMainActivity;
import com.example.practice.other.loadconfig.LoadConfigMainActivity;
import com.example.practice.systemview.SystemViewMainActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class OtherMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_main);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.to_load_config,R.id.to_bezier
    })
    public void onViewClicked(View view) {
        Class<? extends Activity> toActivity;
        switch (view.getId()) {
            case R.id.to_load_config:
                toActivity = LoadConfigMainActivity.class;
                break;
            case R.id.to_bezier:
                toActivity = BezierMainActivity.class;
                break;
            default:
                toActivity = MainActivity.class;
                break;
        }
        startActivity(new Intent(view.getContext(), toActivity));
    }
}

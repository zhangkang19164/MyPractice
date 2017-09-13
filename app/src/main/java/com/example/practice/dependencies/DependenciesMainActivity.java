package com.example.practice.dependencies;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.practice.MainActivity;
import com.example.practice.R;
import com.example.practice.dependencies.lottie.SplashActivity;
import com.example.practice.dependencies.rxjava.RxJavaMainActivity;
import com.example.practice.dependencies.wheelview.WheelviewMainActivity;
import com.example.practice.dependencies.xrecyclerview.XRecyclerViewActivity;

public class DependenciesMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dependencies_main);
    }


    public void onViewClicked(View view) {
        Class<? extends Activity> toActivity;
        switch (view.getId()) {
            case R.id.to_lottie:
                toActivity = SplashActivity.class;
                break;
            case R.id.to_wheelview:
                toActivity = WheelviewMainActivity.class;
                break;
            case R.id.to_x_recycler_view:
                toActivity = XRecyclerViewActivity.class;
                break;

            case R.id.to_rx_java:
                toActivity = RxJavaMainActivity.class;
                break;
            default:
                toActivity = MainActivity.class;
                break;
        }
        startActivity(new Intent(view.getContext(), toActivity));
    }
}

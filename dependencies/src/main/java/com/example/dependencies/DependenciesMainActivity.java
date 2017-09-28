package com.example.dependencies;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.dependencies.lottie.SplashActivity;
import com.example.dependencies.rxjava.RxJavaMainActivity;
import com.example.dependencies.wheelview.WheelviewMainActivity;
import com.example.dependencies.xrecyclerview.XRecyclerViewActivity;


public class DependenciesMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dependencies_main);
    }


    public void onViewClicked(View view) {
        Class<? extends Activity> toActivity = null;
        int i = view.getId();
        if (i == R.id.to_lottie) {
            toActivity = SplashActivity.class;
        } else if (i == R.id.to_wheelview) {
            toActivity = WheelviewMainActivity.class;
        } else if (i == R.id.to_x_recycler_view) {
            toActivity = XRecyclerViewActivity.class;
        } else if (i == R.id.to_rx_java) {
            toActivity = RxJavaMainActivity.class;
        }
        startActivity(new Intent(view.getContext(), toActivity));
    }
}

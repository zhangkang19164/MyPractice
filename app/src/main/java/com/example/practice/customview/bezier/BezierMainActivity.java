package com.example.practice.customview.bezier;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.practice.R;
import com.example.practice.customview.bezier.circle.BezierCircleActivity;

public class BezierMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bezier_main2);
    }

    public void onViewClick(View view) {
        Class<? extends Activity> toActivity;
        switch (view.getId()) {
            case R.id.to_bezier_circle:
                toActivity = BezierCircleActivity.class;
                break;
            default:
                toActivity = this.getClass();
                break;
        }

        startActivity(new Intent(view.getContext(), toActivity));
    }
}

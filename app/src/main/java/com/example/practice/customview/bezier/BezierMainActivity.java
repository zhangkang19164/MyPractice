package com.example.practice.customview.bezier;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.practice.R;
import com.example.practice.customview.bezier.indicator.BezierCircleActivity;
import com.example.practice.customview.bezier.round.BezierRoundActivity;
import com.example.practice.customview.bezier.score.ScoreIndicatorActivity;

public class BezierMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bezier_main);
    }

    public void onViewClick(View view) {
        Class<? extends Activity> toActivity;
        switch (view.getId()) {
            case R.id.to_bezier_circle:
                toActivity = BezierCircleActivity.class;
                break;
            case R.id.to_bezier_round:
                toActivity = BezierRoundActivity.class;
                break;
            case R.id.to_bezier_score:
                toActivity = ScoreIndicatorActivity.class;
                break;
            default:
                toActivity = this.getClass();
                break;
        }

        startActivity(new Intent(view.getContext(), toActivity));
    }
}

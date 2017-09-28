package com.example.custom.bezier;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.custom.R;
import com.example.custom.bezier.indicator.BezierCircleActivity;
import com.example.custom.bezier.round.BezierRoundActivity;
import com.example.custom.bezier.score.ScoreIndicatorActivity;


public class BezierMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bezier_main);
    }

    public void onViewClick(View view) {
        Class<? extends Activity> toActivity;
        int i = view.getId();
        if (i == R.id.to_bezier_circle) {
            toActivity = BezierCircleActivity.class;

        } else if (i == R.id.to_bezier_round) {
            toActivity = BezierRoundActivity.class;

        } else if (i == R.id.to_bezier_score) {
            toActivity = ScoreIndicatorActivity.class;

        } else {
            toActivity = this.getClass();

        }

        startActivity(new Intent(view.getContext(), toActivity));
    }
}

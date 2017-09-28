package com.example.custom.bezier.score;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.example.custom.R;
import com.example.custom.bezier.score.view.ScoreIndicatorView;


public class ScoreIndicatorActivity extends AppCompatActivity {
    private ScoreIndicatorView indicatorView;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_indicator);
        indicatorView = (ScoreIndicatorView) findViewById(R.id.score_indicator_view);
        editText = (EditText) findViewById(R.id.edit_text);
    }

    public void onViewClick(View view) {
        float value = Float.parseFloat(editText.getText().toString());
        indicatorView.setCurrentValue(value, true, true);
    }

}

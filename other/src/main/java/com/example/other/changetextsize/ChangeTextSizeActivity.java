package com.example.other.changetextsize;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.other.R;


public class ChangeTextSizeActivity extends AppCompatActivity {

    TextView changeTextText;

    public float textSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_text_size);
        changeTextText = (TextView) findViewById(R.id.change_text_text);
        changeTextText.setTextSize(1);
        changeTextText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 1);
        textSize = getResources().getDisplayMetrics().scaledDensity;
    }


    public void onViewClicked(View view) {
        int i = view.getId();
        if (i == R.id.change_text_button) {
            textSize += 0.5;
            initScaleDensity(textSize);
            changeTextText.invalidate();

        }
    }

    private void initScaleDensity(float textSize) {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        displayMetrics.scaledDensity = textSize;
    }


}

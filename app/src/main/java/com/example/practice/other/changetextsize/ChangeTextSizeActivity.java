package com.example.practice.other.changetextsize;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.practice.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChangeTextSizeActivity extends AppCompatActivity {

    @BindView(R.id.change_text_text)
    TextView changeTextText;
    @BindView(R.id.change_text_button)
    Button changeTextButton;
    public float textSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_text_size);
        ButterKnife.bind(this);
        changeTextText.setTextSize(1);
        changeTextText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 1);
        textSize = getResources().getDisplayMetrics().scaledDensity;
    }


    @OnClick({R.id.change_text_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.change_text_button:
                textSize += 0.5;
                initScaleDensity(textSize);
                changeTextText.invalidate();
                break;
        }
    }

    private void initScaleDensity(float textSize) {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        displayMetrics.scaledDensity = textSize;
    }


}

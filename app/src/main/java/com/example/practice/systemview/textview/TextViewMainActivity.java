package com.example.practice.systemview.textview;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.practice.MainActivity;
import com.example.practice.R;
import com.example.practice.systemview.textview.spannablestring.SpannableStringMainActivity;

public class TextViewMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_view_main);
        findViewById(R.id.to_spannable_string).setOnClickListener(onClickListener);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Class<? extends Activity> toActivity;
            switch (v.getId()) {
                case R.id.to_spannable_string:
                    toActivity = SpannableStringMainActivity.class;
                    break;

                default:
                    toActivity = MainActivity.class;
                    break;
            }
            startActivity(new Intent(v.getContext(), toActivity));
        }
    };

}

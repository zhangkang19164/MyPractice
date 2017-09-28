package com.example.system.textview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.system.R;
import com.example.system.textview.marquee.MarqueeMainActivity;
import com.example.system.textview.spannablestring.SpannableStringMainActivity;


public class TextViewMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_view_main);
    }

    public void onClick(View v) {
        Class<? extends Activity> toActivity = null;
        int i = v.getId();
        if (i == R.id.to_spannable_string) {
            toActivity = SpannableStringMainActivity.class;

        } else if (i == R.id.to_marquee) {
            toActivity = MarqueeMainActivity.class;

        } else {


        }
        startActivity(new Intent(v.getContext(), toActivity));
    }

}

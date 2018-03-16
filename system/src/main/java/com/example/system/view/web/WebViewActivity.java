package com.example.system.view.web;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;

import com.example.system.R;

public class WebViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
    }

    public class Presenter {
        public void afterTextChanged(Editable s) {

        }
    }
}

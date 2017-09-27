package com.example.practice.other.setting;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.practice.R;
import com.example.practice.other.setting.language.SettingLanguageActivity;

public class SettingActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
    }

    public void onViewClick(View view) {
        Class<? extends Activity> toActivity = null;
        switch (view.getId()) {
            case R.id.setting_language:
                toActivity = SettingLanguageActivity.class;
                break;
        }

        startActivity(new Intent(view.getContext(), toActivity));
    }
}

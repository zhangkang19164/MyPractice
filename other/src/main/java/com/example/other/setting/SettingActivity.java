package com.example.other.setting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.other.R;
import com.example.other.setting.language.SettingLanguageActivity;


public class SettingActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
    }

    public void onViewClick(View view) {
        Class<? extends Activity> toActivity = null;
        int i = view.getId();
        if (i == R.id.setting_language) {
            toActivity = SettingLanguageActivity.class;

        }

        startActivity(new Intent(view.getContext(), toActivity));
    }
}

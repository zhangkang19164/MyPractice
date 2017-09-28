package com.example.other;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.other.changetextsize.ChangeTextSizeActivity;
import com.example.other.databinding.DataBindingMainActivity;
import com.example.other.loadconfig.LoadConfigMainActivity;
import com.example.other.readfile.ReadFileMainActivity;
import com.example.other.setting.SettingActivity;


public class OtherMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_main);
    }


    public void onViewClicked(View view) {
        Class<? extends Activity> toActivity;
        int i = view.getId();
        if (i == R.id.to_load_config) {
            toActivity = LoadConfigMainActivity.class;

        } else if (i == R.id.to_change_text_size) {
            toActivity = ChangeTextSizeActivity.class;

        } else if (i == R.id.to_read_file) {
            toActivity = ReadFileMainActivity.class;

        } else if (i == R.id.to_mvvm) {
            toActivity = DataBindingMainActivity.class;

        } else if (i == R.id.to_setting) {
            toActivity = SettingActivity.class;

        } else {
            toActivity = OtherMainActivity.class;

        }
        startActivity(new Intent(view.getContext(), toActivity));
    }
}

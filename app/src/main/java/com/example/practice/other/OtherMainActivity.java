package com.example.practice.other;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.practice.MainActivity;
import com.example.practice.R;
import com.example.practice.other.changetextsize.ChangeTextSizeActivity;
import com.example.practice.other.databinding.DataBindingMainActivity;
import com.example.practice.other.loadconfig.LoadConfigMainActivity;
import com.example.practice.other.readfile.ReadFileMainActivity;

public class OtherMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_main);
    }


    public void onViewClicked(View view) {
        Class<? extends Activity> toActivity;
        switch (view.getId()) {
            case R.id.to_load_config:
                toActivity = LoadConfigMainActivity.class;
                break;
            case R.id.to_change_text_size:
                toActivity = ChangeTextSizeActivity.class;
                break;
            case R.id.to_read_file:
                toActivity = ReadFileMainActivity.class;
                break;
            case R.id.to_mvvm:
                toActivity = DataBindingMainActivity.class;
                break;
            default:
                toActivity = MainActivity.class;
                break;
        }
        startActivity(new Intent(view.getContext(), toActivity));
    }
}

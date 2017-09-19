package com.example.practice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.practice.customview.CustomMainActivity;
import com.example.practice.dependencies.DependenciesMainActivity;
import com.example.practice.other.OtherMainActivity;
import com.example.practice.systemview.SystemViewMainActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onViewClicked(View view) {
        Class<? extends Activity> toActivity;
        switch (view.getId()) {
            case R.id.to_system_view:
                toActivity = SystemViewMainActivity.class;
                break;
            case R.id.to_dependencies:
                toActivity = DependenciesMainActivity.class;
                break;
            case R.id.to_custom_view:
                toActivity = CustomMainActivity.class;
                break;
            case R.id.to_other:
                toActivity = OtherMainActivity.class;
                break;
            default:
                toActivity = MainActivity.class;
                break;
        }
        startActivity(new Intent(view.getContext(), toActivity));
    }

}

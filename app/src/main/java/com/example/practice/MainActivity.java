package com.example.practice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.custom.CustomMainActivity;
import com.example.dependencies.DependenciesMainActivity;
import com.example.other.OtherMainActivity;
import com.example.other.readfile.RuntimeConfig;
import com.example.other.setting.language.UserConfig;
import com.example.system.SystemViewMainActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UserConfig.settingLanguage(this);
        RuntimeConfig.getInstance().loadConfig(this);
        setContentView(R.layout.activity_main);
    }

    public void onViewClicked(View view) {
        Class<? extends Activity> toActivity = null;
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

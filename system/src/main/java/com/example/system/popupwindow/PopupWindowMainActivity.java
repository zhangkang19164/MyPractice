package com.example.system.popupwindow;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.system.R;
import com.example.system.popupwindow.system.SystemPopupWindowActivity;


public class PopupWindowMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_window_main);
    }


    public void onViewClicked(View view) {
        Class<? extends Activity> toActivity = null;
        int i = view.getId();
        if (i == R.id.to_popup_system) {
            toActivity = SystemPopupWindowActivity.class;

        }
        startActivity(new Intent(view.getContext(), toActivity));
    }
}

package com.example.system.dialog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.system.R;
import com.example.system.dialog.custom.CustomDialogActivity;
import com.example.system.dialog.system.SystemDialogActivity;


public class DialogMainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_main);
    }


    public void onViewClicked(View view) {
        Class<? extends Activity> toActivity = null;
        int i = view.getId();
        if (i == R.id.to_system_dialog) {
            toActivity = SystemDialogActivity.class;

        } else if (i == R.id.to_custom_dialog) {
            toActivity = CustomDialogActivity.class;

        }
        startActivity(new Intent(view.getContext(), toActivity));
    }
}

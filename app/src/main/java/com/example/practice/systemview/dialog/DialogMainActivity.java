package com.example.practice.systemview.dialog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.practice.MainActivity;
import com.example.practice.R;
import com.example.practice.systemview.dialog.custom.CustomDialogActivity;
import com.example.practice.systemview.dialog.system.SystemDialogActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DialogMainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_main);
    }


    public void onViewClicked(View view) {
        Class<? extends Activity> toActivity;
        switch (view.getId()) {
            case R.id.to_system_dialog:
                toActivity = SystemDialogActivity.class;
                break;
            case R.id.to_custom_dialog:
                toActivity = CustomDialogActivity.class;
                break;
            default:
                toActivity = MainActivity.class;
                break;
        }
        startActivity(new Intent(view.getContext(), toActivity));
    }
}

package com.example.practice.systemview.popupwindow;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.practice.MainActivity;
import com.example.practice.R;
import com.example.practice.systemview.popupwindow.system.SystemPopupWindowActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PopupWindowMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_window_main);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.to_popup_system,})
    public void onViewClicked(View view) {
        Class<? extends Activity> toActivity;
        switch (view.getId()) {
            case R.id.to_popup_system:
                toActivity = SystemPopupWindowActivity.class;
                break;

            default:
                toActivity = MainActivity.class;
                break;
        }
        startActivity(new Intent(view.getContext(), toActivity));
    }
}

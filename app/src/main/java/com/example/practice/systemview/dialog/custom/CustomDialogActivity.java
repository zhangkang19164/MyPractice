package com.example.practice.systemview.dialog.custom;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.practice.R;

/**
 * 自定义的Dialog
 */
public class CustomDialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_dialog);
        findViewById(R.id.custom_dialog_mian_button_01).setOnClickListener(onClickListener);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.custom_dialog_mian_button_01:
                    button_01();
                    break;

            }
        }
    };

    private void button_01() {
        CustomDialog.Builder builder = new CustomDialog.Builder(this);
        builder.setContext("测试内容")
                .create()
                .show();
    }
}

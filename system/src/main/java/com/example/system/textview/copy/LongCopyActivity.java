package com.example.system.textview.copy;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.system.R;

public class LongCopyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_long_copy);
    }

    public void copyTxt(View view) {
        if (view instanceof TextView) {
            if (copyTxt(((TextView) view).getText().toString())) {
                Toast.makeText(this, "复制成功", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "复制失败", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public boolean copyTxt(String needCopyTxt) {
        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        if (null != clipboardManager) {
            clipboardManager.setPrimaryClip(ClipData.newPlainText("标题", needCopyTxt));
            return true;
        } else {
            return false;
        }
    }
}

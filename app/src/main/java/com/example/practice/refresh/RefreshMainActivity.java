package com.example.practice.refresh;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.practice.MainActivity;
import com.example.practice.R;
import com.example.practice.customview.CustomMainActivity;
import com.example.practice.expandablelistview.ExpandableListViewMainActivity;
import com.example.practice.listview.ListViewMainActivity;
import com.example.practice.recyclerview.RecyclerViewMainActivity;
import com.example.practice.refresh.frame.RefresFrameActivity;
import com.example.practice.textview.TextViewMainActivity;

public class RefreshMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh_main);
        findViewById(R.id.refresh_frame).setOnClickListener(onClickListener);
    }
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Class<? extends Activity> toActivity;
            switch (v.getId()) {
                case R.id.refresh_frame:
                    toActivity = RefresFrameActivity.class;
                    break;
                default:
                    toActivity = MainActivity.class;
                    break;
            }
            startActivity(new Intent(v.getContext(), toActivity));
        }
    };
}

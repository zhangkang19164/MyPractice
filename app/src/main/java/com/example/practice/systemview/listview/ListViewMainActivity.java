package com.example.practice.systemview.listview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.practice.R;
import com.example.practice.systemview.listview.divider.ListViewDividerActivity;
import com.example.practice.systemview.listview.empty.EmptyMainActivity;

public class ListViewMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_main);
        findViewById(R.id.divider).setOnClickListener(onClickListener);
        findViewById(R.id.empty).setOnClickListener(onClickListener);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.divider:
                    startActivity(new Intent(v.getContext(), ListViewDividerActivity.class));
                    break;
                case R.id.empty:
                    startActivity(new Intent(v.getContext(), EmptyMainActivity.class));
                    break;
                default:
                    break;
            }
        }
    };
}

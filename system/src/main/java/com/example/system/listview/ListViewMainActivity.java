package com.example.system.listview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.system.R;
import com.example.system.listview.divider.ListViewDividerActivity;
import com.example.system.listview.empty.EmptyMainActivity;


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
            int i = v.getId();
            if (i == R.id.divider) {
                startActivity(new Intent(v.getContext(), ListViewDividerActivity.class));

            } else if (i == R.id.empty) {
                startActivity(new Intent(v.getContext(), EmptyMainActivity.class));

            } else {
            }
        }
    };
}

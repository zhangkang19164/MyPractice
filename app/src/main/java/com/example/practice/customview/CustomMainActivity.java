package com.example.practice.customview;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.practice.MainActivity;
import com.example.practice.R;
import com.example.practice.customview.linearlayoutcompat.LinearLayoutCompatMainActivity;
import com.example.practice.expandablelistview.ExpandableListViewMainActivity;
import com.example.practice.listview.ListViewMainActivity;
import com.example.practice.recyclerview.RecyclerViewMainActivity;
import com.example.practice.textview.TextViewMainActivity;

public class CustomMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_main);
        findViewById(R.id.to_LinearLayoutCompat).setOnClickListener(onClickListener);
    }


    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Class<? extends Activity> toActivity;
            switch (v.getId()) {
                case R.id.to_LinearLayoutCompat:
                    toActivity = LinearLayoutCompatMainActivity.class;
                    break;

                default:
                    toActivity = MainActivity.class;
                    break;
            }
            startActivity(new Intent(v.getContext(), toActivity));
        }
    };

}

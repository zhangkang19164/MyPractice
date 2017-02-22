package com.example.practice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.practice.listview.ListViewMainActivity;
import com.example.practice.recyclerview.RecyclerViewMainActivity;
import com.example.practice.textview.TextViewMainActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.to_list_view).setOnClickListener(onClickListener);
        findViewById(R.id.to_recyclerview).setOnClickListener(onClickListener);
        findViewById(R.id.to_text_view).setOnClickListener(onClickListener);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Class<? extends Activity> toActivity;
            switch (v.getId()) {
                case R.id.to_list_view:
                    toActivity = ListViewMainActivity.class;
                    break;
                case R.id.to_recyclerview:
                    toActivity = RecyclerViewMainActivity.class;
                    break;
                case R.id.to_text_view:
                    toActivity = TextViewMainActivity.class;
                    break;
                default:
                    toActivity = MainActivity.class;
                    break;
            }
            startActivity(new Intent(v.getContext(), toActivity));
        }
    };

}

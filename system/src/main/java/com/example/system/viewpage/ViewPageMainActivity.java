package com.example.system.viewpage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.system.R;
import com.example.system.viewpage.tablayout.TabLayoutMainActivity;


public class ViewPageMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_page_main);
        findViewById(R.id.to_tablayout).setOnClickListener(onClickListener);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Class<? extends Activity> toActivity = null;
            int i = v.getId();
            if (i == R.id.to_tablayout) {
                toActivity = TabLayoutMainActivity.class;

            } else {

            }
            startActivity(new Intent(v.getContext(), toActivity));
        }
    };
}

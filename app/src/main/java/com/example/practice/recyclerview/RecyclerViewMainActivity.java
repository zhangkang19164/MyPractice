package com.example.practice.recyclerview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.practice.MainActivity;
import com.example.practice.R;
import com.example.practice.recyclerview.decoration.other.DecorationActivity;
import com.example.practice.recyclerview.grouping.RecyclerViewGroupingActivity;
import com.example.practice.recyclerview.tradecommon.activity.TradeCommonActivity;

public class RecyclerViewMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_main);
        findViewById(R.id.to_decoration).setOnClickListener(onClickListener);
        findViewById(R.id.to_grouping).setOnClickListener(onClickListener);
        findViewById(R.id.to_tradecommon).setOnClickListener(onClickListener);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Class<? extends Activity> toActivity;
            switch (v.getId()) {
                case R.id.to_grouping:
                    toActivity = RecyclerViewGroupingActivity.class;
                    break;
                case R.id.to_tradecommon:
                    toActivity = TradeCommonActivity.class;
                    break;
                case R.id.to_decoration:
                    toActivity = DecorationActivity.class;
                    break;
                default:
                    toActivity = MainActivity.class;
                    break;
            }
            startActivity(new Intent(v.getContext(), toActivity));
        }
    };
}

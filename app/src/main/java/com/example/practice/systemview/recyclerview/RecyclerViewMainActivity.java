package com.example.practice.systemview.recyclerview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.practice.MainActivity;
import com.example.practice.R;
import com.example.practice.systemview.recyclerview.decoration.other.DecorationActivity;
import com.example.practice.systemview.recyclerview.drag.DragRecyclerViewActivity;
import com.example.practice.systemview.recyclerview.edit.RecyclerEditActivity;
import com.example.practice.systemview.recyclerview.grouping.RecyclerViewGroupingActivity;
import com.example.practice.systemview.recyclerview.tradecommon.activity.TradeCommonActivity;
import com.example.practice.systemview.recyclerview.tradelist.TradeListMainActivity;

public class RecyclerViewMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_main);
    }

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
            case R.id.to_trade_list:
                toActivity = TradeListMainActivity.class;
                break;
            case R.id.to_edit:
                toActivity = RecyclerEditActivity.class;
                break;
            case R.id.to_drag:
                toActivity = DragRecyclerViewActivity.class;
                break;
            default:
                toActivity = MainActivity.class;
                break;
        }
        startActivity(new Intent(v.getContext(), toActivity));
    }
}
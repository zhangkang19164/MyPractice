package com.example.system.recyclerview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.system.R;
import com.example.system.recyclerview.decoration.other.DecorationActivity;
import com.example.system.recyclerview.drag.DragRecyclerViewActivity;
import com.example.system.recyclerview.edit.RecyclerEditActivity;
import com.example.system.recyclerview.grouping.RecyclerViewGroupingActivity;
import com.example.system.recyclerview.tradecommon.activity.TradeCommonActivity;
import com.example.system.recyclerview.tradelist.TradeListMainActivity;


public class RecyclerViewMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_main);
    }

    public void onClick(View v) {
        Class<? extends Activity> toActivity = null;
        int i = v.getId();
        if (i == R.id.to_grouping) {
            toActivity = RecyclerViewGroupingActivity.class;

        } else if (i == R.id.to_tradecommon) {
            toActivity = TradeCommonActivity.class;

        } else if (i == R.id.to_decoration) {
            toActivity = DecorationActivity.class;

        } else if (i == R.id.to_trade_list) {
            toActivity = TradeListMainActivity.class;

        } else if (i == R.id.to_edit) {
            toActivity = RecyclerEditActivity.class;

        } else if (i == R.id.to_drag) {
            toActivity = DragRecyclerViewActivity.class;

        }
        startActivity(new Intent(v.getContext(), toActivity));
    }
}

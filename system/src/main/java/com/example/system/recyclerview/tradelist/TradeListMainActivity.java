package com.example.system.recyclerview.tradelist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.common.tools.ViewTools;
import com.example.system.R;
import com.example.system.recyclerview.tradecommon.itemdecoration.TradeCommonItemDecoration;


public class TradeListMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trade_list_main);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        int[] types = {0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(new Adapter(types));
    }

    private static class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        int[] types;

        public Adapter(int[] types) {
            this.types = types;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType == 0) {
                TextView textView = new TextView(parent.getContext());
                textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                textView.setGravity(Gravity.CENTER_VERTICAL);
                int i = ViewTools.dp2Px(10);
                textView.setPadding(i, i, i, i);
                textView.setText("标题");
                textView.setBackgroundColor(parent.getContext().getResources().getColor(R.color._9C9C9C));
                return new RecyclerView.ViewHolder(textView) {

                };
            } else {
                TextView textView = new TextView(parent.getContext());
                textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                textView.setGravity(Gravity.CENTER_VERTICAL);
                int i = ViewTools.dp2Px(15);
                textView.setPadding(i, i, i, i);
                textView.setText("内容");
                textView.setBackgroundColor(parent.getContext().getResources().getColor(R.color._F8F8F8));
                return new RecyclerView.ViewHolder(textView) {

                };
            }

        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return null == types ? 0 : types.length;
        }

        @Override
        public int getItemViewType(int position) {
            return types[position];
        }
    }
}

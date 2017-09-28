package com.example.system.recyclerview.pinedview;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import com.example.system.R;
import com.example.system.recyclerview.pinedview.view.PinedHeaderCallBack;
import com.example.system.recyclerview.pinedview.view.PinnedHeaderProxy;


public class PinnedHeaderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pinned_header);
        initView();
    }

    private void initView() {
        PinedHeaderCallBack pinedHeaderCallBack = new PinedHeaderCallBack() {
            @Override
            public View getHeaderView() {
                return LayoutInflater.from(PinnedHeaderActivity.this).inflate(R.layout.item_group_two, null);
            }

            @Override
            public void updateHeaderView(View headerView, int groupIndex) {

            }
        };
        PinnedHeaderProxy pinnedHeaderProxy = new PinnedHeaderProxy(pinedHeaderCallBack);
//        binding.pinedHeaderRecyclerView.setPinedListener(pinnedHeaderProxy);
    }
}

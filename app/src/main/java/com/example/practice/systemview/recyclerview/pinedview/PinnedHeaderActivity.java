package com.example.practice.systemview.recyclerview.pinedview;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.example.practice.R;
import com.example.practice.databinding.ActivityPinnedHeaderBinding;
import com.example.practice.systemview.recyclerview.pinedview.view.PinedHeaderCallBack;
import com.example.practice.systemview.recyclerview.pinedview.view.PinnedHeaderProxy;

public class PinnedHeaderActivity extends AppCompatActivity {
    private ActivityPinnedHeaderBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_pinned_header);
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
        binding.pinedHeaderRecyclerView.setPinedListener(pinnedHeaderProxy);
    }
}

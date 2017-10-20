package com.example.other.databinding.recyclerview;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;


import com.example.other.R;
import com.example.other.databinding.ActivityMvvmofRecyclerViewBinding;
import com.example.other.databinding.recyclerview.adapter.MVVMAdapter;

import java.util.ArrayList;

public class MVVMOfRecyclerViewActivity extends AppCompatActivity {
    private ActivityMvvmofRecyclerViewBinding binding;
    private MVVMAdapter mvvmAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_mvvmof_recycler_view);
        binding.setPresenter(new Presenter());
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        ArrayList<String> stringList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            stringList.add("我是第 " + i + " 个");
        }
        mvvmAdapter = new MVVMAdapter(stringList);
        binding.recyclerView.setAdapter(mvvmAdapter);
    }

    public class Presenter {
        public void addItem() {
            mvvmAdapter.add("我是新加的");
        }

        public void deleteItem() {
            mvvmAdapter.delete();
        }
    }
}

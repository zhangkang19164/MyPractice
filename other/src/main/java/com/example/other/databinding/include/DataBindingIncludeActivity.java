package com.example.other.databinding.include;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.other.R;
import com.example.other.databinding.ActivityDataBindingIncludeBinding;

public class DataBindingIncludeActivity extends AppCompatActivity {
    ActivityDataBindingIncludeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_data_binding_include);
        binding.setIncludeMode(new IncludeMode("我是名字","我是密码"));
    }
}

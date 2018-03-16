package com.example.other.encode;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.other.R;
import com.example.other.databinding.ActivityEncodeMainBinding;

public class EncodeMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityEncodeMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_encode_main);
        EncodeModel encodeModel = new EncodeModel();
        binding.setModel(encodeModel);
    }
}

package com.example.dependencies.qrcode;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.dependencies.R;
import com.example.dependencies.databinding.ActivityQrcodeBinding;

public class QRCodeActivity extends AppCompatActivity {
    private ActivityQrcodeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_qrcode);
        initView();
    }

    private void initView(){
        binding.zXingView.startCamera();
    }
}

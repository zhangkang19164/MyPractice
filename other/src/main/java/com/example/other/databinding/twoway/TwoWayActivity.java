package com.example.other.databinding.twoway;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.other.R;
import com.example.other.databinding.ActivityTwoWayBinding;


public class TwoWayActivity extends AppCompatActivity {
    ActivityTwoWayBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_two_way);
        binding.setTwoWayModel(new TwoWayModel("zhangkang", "123456"));
    }
}

package com.example.practice.other.databinding.customattributes;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.practice.R;
import com.example.practice.databinding.ActivityCustomAttributesBinding;

public class CustomAttributesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCustomAttributesBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_custom_attributes);
        binding.setUrl("http://imgsrc.baidu.com/imgad/pic/item/267f9e2f07082838b5168c32b299a9014c08f1f9.jpg");
    }
}

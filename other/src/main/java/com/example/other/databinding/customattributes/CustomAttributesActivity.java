package com.example.other.databinding.customattributes;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.other.R;
import com.example.other.databinding.ActivityCustomAttributesBinding;


public class CustomAttributesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCustomAttributesBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_custom_attributes);
        binding.setUrl("http://imgsrc.baidu.com/imgad/pic/item/267f9e2f07082838b5168c32b299a9014c08f1f9.jpg");
    }
}

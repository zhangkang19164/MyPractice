package com.example.practice.dependencies.gson;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.practice.R;
import com.google.gson.Gson;

public class GsonMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gson_main);
        new Gson().fromJson("",GsonBean.class);
        GsonBean gsonBean=new GsonBean();
        new Gson().toJson(gsonBean);
    }
}

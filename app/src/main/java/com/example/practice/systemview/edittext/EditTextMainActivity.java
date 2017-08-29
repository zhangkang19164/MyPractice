package com.example.practice.systemview.edittext;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.practice.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EditTextMainActivity extends AppCompatActivity {


    @BindView(R.id.edit_text)
    MyEditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);
    }

}

package com.example.custom.picker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


import com.example.custom.R;
import com.example.custom.picker.view.PickerScrollView;
import com.example.custom.picker.view.Pickers;

import java.util.ArrayList;
import java.util.List;


public class PickerMainActivity extends AppCompatActivity {

    PickerScrollView pickerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picker_main);
        pickerView= (PickerScrollView) findViewById(R.id.picker_view);
        initViews();
    }

    private void initViews() {
        List<Pickers> pickersList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            pickersList.add(new Pickers("我是第 " + i + " 个"));
        }
        pickerView.setData(pickersList);
        pickerView.setSelected(0);
    }
}

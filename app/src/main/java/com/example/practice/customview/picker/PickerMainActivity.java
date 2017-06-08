package com.example.practice.customview.picker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.practice.R;
import com.example.practice.customview.picker.view.PickerScrollView;
import com.example.practice.customview.picker.view.Pickers;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PickerMainActivity extends AppCompatActivity {

    @BindView(R.id.picker_view)
    PickerScrollView pickerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picker_main);
        ButterKnife.bind(this);
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

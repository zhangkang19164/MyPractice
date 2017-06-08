package com.example.practice.customview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.practice.MainActivity;
import com.example.practice.R;
import com.example.practice.customview.attribute.CustomAttributeMainActivity;
import com.example.practice.customview.detail.DetailMainActivity;
import com.example.practice.customview.linearlayoutcompat.LinearLayoutCompatMainActivity;
import com.example.practice.customview.picker.PickerMainActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class CustomMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_main);
        ButterKnife.bind(this);

    }


    @OnClick({R.id.to_LinearLayoutCompat, R.id.to_detail,
            R.id.to_attribute, R.id.to_picker})
    public void onViewClicked(View view) {
        Class<? extends Activity> toActivity;
        switch (view.getId()) {
            case R.id.to_LinearLayoutCompat:
                toActivity = LinearLayoutCompatMainActivity.class;
                break;
            case R.id.to_detail:
                toActivity = DetailMainActivity.class;
                break;
            case R.id.to_attribute:
                toActivity = CustomAttributeMainActivity.class;
                break;
            case R.id.to_picker:
                toActivity = PickerMainActivity.class;
                break;
            default:
                toActivity = MainActivity.class;
                break;
        }
        startActivity(new Intent(view.getContext(), toActivity));
    }
}

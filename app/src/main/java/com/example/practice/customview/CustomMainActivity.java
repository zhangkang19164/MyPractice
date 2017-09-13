package com.example.practice.customview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.practice.MainActivity;
import com.example.practice.R;
import com.example.practice.customview.bezier.BezierMainActivity;
import com.example.practice.customview.calendar.CalendarActivity;
import com.example.practice.customview.linearlayoutcompat.LinearLayoutCompatMainActivity;
import com.example.practice.customview.picker.PickerMainActivity;
import com.example.practice.customview.slidingmenu.SlidingMenuActivity;
import com.example.practice.customview.xiaomiweather.XiaoMiWeatherActivity;

public class CustomMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_main);
    }


    public void onViewClicked(View view) {
        Class<? extends Activity> toActivity;
        switch (view.getId()) {
            case R.id.to_LinearLayoutCompat:
                toActivity = LinearLayoutCompatMainActivity.class;
                break;

            case R.id.to_picker:
                toActivity = PickerMainActivity.class;
                break;
            case R.id.to_custom:
                toActivity = PickerMainActivity.class;
                break;
            case R.id.to_custom_calendar:
                toActivity = CalendarActivity.class;
                break;
            case R.id.to_sliding_menu_recycler:
                toActivity = SlidingMenuActivity.class;
                break;
            case R.id.to_xiaomi_weather:
                toActivity = XiaoMiWeatherActivity.class;
                break;
            case R.id.to_bezier:
                toActivity = BezierMainActivity.class;
                break;
            default:
                toActivity = MainActivity.class;
                break;
        }
        startActivity(new Intent(view.getContext(), toActivity));
    }
}

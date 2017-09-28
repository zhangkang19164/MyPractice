package com.example.custom;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.custom.bezier.BezierMainActivity;
import com.example.custom.calendar.CalendarActivity;
import com.example.custom.linearlayoutcompat.LinearLayoutCompatMainActivity;
import com.example.custom.picker.PickerMainActivity;
import com.example.custom.slidingmenu.SlidingMenuActivity;
import com.example.custom.xiaomiweather.XiaoMiWeatherActivity;


public class CustomMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_main);
    }


    public void onViewClicked(View view) {
        Class<? extends Activity> toActivity;
        int i = view.getId();
        if (i == R.id.to_LinearLayoutCompat) {
            toActivity = LinearLayoutCompatMainActivity.class;

        } else if (i == R.id.to_picker) {
            toActivity = PickerMainActivity.class;

        } else if (i == R.id.to_custom) {
            toActivity = PickerMainActivity.class;

        } else if (i == R.id.to_custom_calendar) {
            toActivity = CalendarActivity.class;

        } else if (i == R.id.to_sliding_menu_recycler) {
            toActivity = SlidingMenuActivity.class;

        } else if (i == R.id.to_xiaomi_weather) {
            toActivity = XiaoMiWeatherActivity.class;

        } else if (i == R.id.to_bezier) {
            toActivity = BezierMainActivity.class;

        } else {
            toActivity = CustomMainActivity.class;

        }
        startActivity(new Intent(view.getContext(), toActivity));
    }
}

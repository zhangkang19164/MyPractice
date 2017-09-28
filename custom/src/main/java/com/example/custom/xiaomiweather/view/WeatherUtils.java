package com.example.custom.xiaomiweather.view;


import com.example.custom.R;

/**
 * create time : 2017/08/30
 * desc        :
 */

public class WeatherUtils {
    public static final String[] times = new String[]{
            "00:00", "01:00", "02:00", "03:00", "04:00",
            "05:00", "06:00", "07:00", "08:00", "09:00",
            "10:00", "11:00", "12:00", "13:00", "14:00",
            "15:00", "16:00", "17:00", "18:00", "19:00",
            "20:00", "21:00", "22:00", "23:00",
    };

    public static int selectWeatherIcon(String weatherType) {
        switch (weatherType) {
            case "1":
                return R.drawable.sun;
            case "2":
                return R.drawable.sun_cloud;
            case "3":
                return R.drawable.cloudy;
            case "4":
                return R.drawable.thunder;
            case "5":
                return R.drawable.rain;
            case "6":
                return R.drawable.snow;
            default:
                return R.drawable.sun;
        }
    }
}

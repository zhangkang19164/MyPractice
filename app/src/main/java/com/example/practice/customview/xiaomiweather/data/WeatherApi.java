package com.example.practice.customview.xiaomiweather.data;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * create time : 2017/08/30
 * desc        :
 */

public interface WeatherApi {
    @GET("/weather/now.json")
    Call<RequestBody> getWeather(String key, String location, String language, String unit);
}

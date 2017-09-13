package com.example.practice.customview.xiaomiweather.data;

import org.json.JSONObject;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * create time : 2017/08/30
 * desc        :
 */

public class Api {
    //https://api.seniverse.com/v3/weather/now.json?key=4tgpmfwxnwfqyci8&location=beijing&language=zh-Hans&unit=c
    private String TIANQI_DAILY_WEATHER_URL = "https://api.seniverse.com/v3/";

    private String TIANQI_API_SECRET_KEY = "4tgpmfwxnwfqyci8"; //

    private String TIANQI_API_USER_ID = "UC0BE43B3F"; //

    public void getWeather() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(TIANQI_DAILY_WEATHER_URL)
                .build();
        WeatherApi weatherApi = retrofit.create(WeatherApi.class);
        Call<RequestBody> weather = weatherApi.getWeather(TIANQI_API_SECRET_KEY, "hangzhou", "zh-Hans&unit", "c");
        weather.enqueue(new Callback<RequestBody>() {
            @Override
            public void onResponse(Call<RequestBody> call, Response<RequestBody> response) {
                RequestBody body = response.body();

            }

            @Override
            public void onFailure(Call<RequestBody> call, Throwable t) {

            }
        });

    }

}

package com.example.practice.customview.xiaomiweather.view;

/**
 * create time : 2017/08/30
 * desc        :
 */

public class WeatherSBean {
    //时间
    private String time;
    //温度
    private int temperature;
    //日出或者日落
    private boolean sunriseOrSunset;
    //天气状态
    private String stateOfWeather;

    public WeatherSBean(int temperature) {
        this.temperature = temperature;
    }

    public WeatherSBean(String time, int temperature) {
        this.time = time;
        this.temperature = temperature;
    }

    public WeatherSBean(String time, int temperature, boolean sunriseOrSunset) {
        this.time = time;
        this.temperature = temperature;
        this.sunriseOrSunset = sunriseOrSunset;
    }
    public WeatherSBean(int temperature, String stateOfWeather) {
        this.temperature = temperature;
        this.stateOfWeather = stateOfWeather;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public boolean getSunriseOrSunset() {
        return sunriseOrSunset;
    }

    public void setSunriseOrSunset(boolean sunriseOrSunset) {
        this.sunriseOrSunset = sunriseOrSunset;
    }

    public String getStateOfWeather() {
        return stateOfWeather;
    }

    public void setStateOfWeather(String stateOfWeather) {
        this.stateOfWeather = stateOfWeather;
    }
}

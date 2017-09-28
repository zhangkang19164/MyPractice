package com.example.custom.xiaomiweather;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


import com.example.custom.R;
import com.example.custom.databinding.ActivityXiaoMiWeatherBinding;
import com.example.custom.xiaomiweather.test.WeatherBean;
import com.example.custom.xiaomiweather.view.WeatherSBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class XiaoMiWeatherActivity extends AppCompatActivity {
    private ActivityXiaoMiWeatherBinding viewDataBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_xiao_mi_weather);
        intView();
    }
    List<WeatherBean> data;
    private void intView() {

        data = new ArrayList<>();
        Random random = new Random();
        String[] weathers = WeatherBean.getAllWeathers();
        int count = 0;
        for (int i = 0; i < 8; i++) {
            int a = random.nextInt(6);//随机天气
            int b = 1 + random.nextInt(4);//随机连续天气数
            for (int j = 0; j < b; j++) {
                count++;
                int c = random.nextInt(5); //随机温度
                WeatherBean bean = new WeatherBean(weathers[a], 20 + c, count + ":00");
                data.add(bean);
            }
        }
        data.get(2).temperatureStr = "日出";
        data.get(2).time = "测试中文";
        data.get(4).temperatureStr = "日落";
        data.get(4).time = "陈朝勇";

        WeatherBean b1 = new WeatherBean(weathers[0], 20, "05:00");
        WeatherBean b2 = new WeatherBean(weathers[1], 22, "日出", "05:30");
        WeatherBean b3 = new WeatherBean(weathers[2], 21, "06:00");
        WeatherBean b4 = new WeatherBean(weathers[2], 22, "07:00");
        WeatherBean b5 = new WeatherBean(weathers[2], 23, "08:00");
        WeatherBean b6 = new WeatherBean(weathers[3], 20, "09:00");
        data.add(b1);
        data.add(b2);
        data.add(b3);
        data.add(b4);
        data.add(b5);
        data.add(b6);
        viewDataBinding.weather.setData(data);

    }

    private void intData() {
        List<WeatherSBean> list = new ArrayList<>();
        list.add(new WeatherSBean(27, "1"));
        list.add(new WeatherSBean(28, "1"));
        list.add(new WeatherSBean(28, "1"));
        list.add(new WeatherSBean(26, "1"));
        list.add(new WeatherSBean(26, "2"));
        list.add(new WeatherSBean(27, "2"));
//        list.add(new WeatherSBean("5:30", 28, true));
        list.add(new WeatherSBean(28, "2"));
        list.add(new WeatherSBean(29, "3"));
        list.add(new WeatherSBean(30, "3"));
        list.add(new WeatherSBean(31, "3"));
        list.add(new WeatherSBean(32, "4"));
        list.add(new WeatherSBean(33, "4"));
        list.add(new WeatherSBean(34, "5"));
        list.add(new WeatherSBean(38, "5"));
        list.add(new WeatherSBean(36, "6"));
        list.add(new WeatherSBean(33, "1"));
        list.add(new WeatherSBean(30, "1"));
        list.add(new WeatherSBean(28, "1"));
//        list.add(new WeatherSBean("18:30", 28, false));
        list.add(new WeatherSBean(26, "1"));
        list.add(new WeatherSBean(26, "1"));
        list.add(new WeatherSBean(26, "1"));
        list.add(new WeatherSBean(26, "1"));
        list.add(new WeatherSBean(26, "1"));
        list.add(new WeatherSBean(26, "2"));
        viewDataBinding.weatherView.setWeatherBeanList(list);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){
            intData();
        }
    }
}

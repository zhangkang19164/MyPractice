package com.zhangkang;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MyClass {
    public static final String BASE_URL = "https://api.douban.com/v2/movie/";

    public static void main(String args[]) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .build();
        AppURL appURL = retrofit.create(AppURL.class);
        Call<ResponseBody> call = appURL.getInTheaters("0b2bdeda43b5688921839c8ecb20399b","杭州",0,10,"","");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    System.out.println(response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                System.out.println();
            }
        });
    }
}

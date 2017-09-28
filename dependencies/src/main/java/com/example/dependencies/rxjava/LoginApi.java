package com.example.dependencies.rxjava;


import com.example.dependencies.rxjava.entity.LoginRequest;
import com.example.dependencies.rxjava.entity.LoginResponse;
import com.example.dependencies.rxjava.entity.RegisterRequest;
import com.example.dependencies.rxjava.entity.RegisterResponse;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;

/**
 * create time : 2017/08/16
 * desc        :
 */

public interface LoginApi {
    @GET
    Observable<LoginResponse> login(@Body LoginRequest request);

    @GET
    Observable<RegisterResponse> register(@Body RegisterRequest registerRequset);
}

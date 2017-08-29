package com.example.practice.dependencies.rxjava;

import com.example.practice.dependencies.rxjava.entity.LoginRequest;
import com.example.practice.dependencies.rxjava.entity.LoginResponse;
import com.example.practice.dependencies.rxjava.entity.RegisterRequset;
import com.example.practice.dependencies.rxjava.entity.RegisterResponse;

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
    Observable<RegisterResponse> register(@Body RegisterRequset registerRequset);
}

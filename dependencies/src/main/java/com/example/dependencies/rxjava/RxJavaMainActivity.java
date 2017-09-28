package com.example.dependencies.rxjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import com.example.dependencies.BuildConfig;
import com.example.dependencies.R;
import com.example.dependencies.rxjava.entity.LoginRequest;
import com.example.dependencies.rxjava.entity.LoginResponse;
import com.example.dependencies.rxjava.entity.RegisterRequest;
import com.example.dependencies.rxjava.entity.RegisterResponse;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RxJavaMainActivity extends AppCompatActivity {
    private static final String TAG = "RxJavaMainActivity";
    private Retrofit retrofit;
    private LoginApi loginApi;
    private FlowableTest flowableTest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java_main);
//        retrofit = create();
//        loginApi = retrofit.create(LoginApi.class);
        flowableTest=new FlowableTest();
        flowableTest.basicUse();
    }


    public void requestOne(View view) {
        flowableTest.requestOne();
    }

    private void login() {
        loginApi.login(new LoginRequest())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginResponse value) {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(RxJavaMainActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {
                        Toast.makeText(RxJavaMainActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void register() {
        loginApi.register(new RegisterRequest())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RegisterResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RegisterResponse value) {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(RxJavaMainActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {
                        Toast.makeText(RxJavaMainActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private static Retrofit create() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.readTimeout(10, TimeUnit.SECONDS);
        builder.connectTimeout(9, TimeUnit.SECONDS);
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(loggingInterceptor);
        }
        return new Retrofit.Builder().baseUrl("")
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

    }

    private void registerAndLogin() {
        loginApi.register(new RegisterRequest())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<RegisterResponse>() {
                    @Override
                    public void accept(RegisterResponse registerResponse) throws Exception {

                    }
                })
                .observeOn(Schedulers.io())
                .flatMap(new Function<RegisterResponse, ObservableSource<LoginResponse>>() {
                    @Override
                    public ObservableSource<LoginResponse> apply(RegisterResponse registerResponse) throws Exception {
                        return loginApi.login(new LoginRequest());
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<LoginResponse>() {
                    @Override
                    public void accept(LoginResponse loginResponse) throws Exception {

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }

    //基本使用
    private static void basicUse() {
        //1.创建Observable
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                System.out.println("输出值: " + 1);
                e.onNext(2);
                System.out.println("输出值: " + 2);
                e.onNext(3);
                System.out.println("输出值: " + 3);
                e.onComplete();
                e.onNext(4);
                System.out.println("输出值: " + 4);
            }

        });
        //2.创建Observer
        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("onSubscribe");

            }

            @Override
            public void onNext(Integer value) {
                System.out.println("onNext 返回值: " + value);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError:");
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        };
        //3.建立联系 建立联系后将立即发送消息
        observable.subscribe(observer);
    }

    //链式调用
    private static void callChaining() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                System.out.println("输出值: " + 1);
                e.onNext(2);
                System.out.println("输出值: " + 2);
                e.onNext(3);
                System.out.println("输出值: " + 3);
                e.onError(new Throwable("竟然出错了"));
                e.onNext(4);
                System.out.println("输出值: " + 4);
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("onSubscribe");
            }

            @Override
            public void onNext(Integer value) {
                System.out.println("onNext 返回值: " + value);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError: " + e.toString());
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        });
    }

    //指定线程
    private void specifiedThread() {
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                Log.i(TAG, "subscribe: " + Thread.currentThread().getName());
                e.onNext(1);
            }
        });

        Consumer<Integer> consumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.i(TAG, "accept: " + Thread.currentThread().getName());
            }
        };
        //设置发送的线程 多次指定第一次有效
        observable.subscribeOn(Schedulers.newThread());
        //设置处理的线程 多次指定最后一次有效
        observable.observeOn(AndroidSchedulers.mainThread());
        observable.sample(2, TimeUnit.SECONDS);
        //设置关联
        observable.subscribe(consumer);
    }

    //将两个被观察者事件进行组合
    private void zip() {
        Observable<Integer> observable1 = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                for (int i = 0; ; i++) {
                    e.onNext(i);
                    Thread.sleep(1000);
                }
            }
        }).subscribeOn(Schedulers.io());

        Observable<String> observable2 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("随便发的");
            }
        }).subscribeOn(Schedulers.io());

        Observable.zip(observable1, observable2, new BiFunction<Integer, String, String>() {
            @Override
            public String apply(Integer integer, String s) throws Exception {
                return integer + s;
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String value) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}

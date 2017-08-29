package com.example.rxjava;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class RxJavaMainClass {

    public static void main(String args[]) {
        zip();
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
        }).map(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) throws Exception {

                return "处理后的值 " + integer;
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("onSubscribe");
            }

            @Override
            public void onNext(String string) {
                System.out.println("onNext 返回值: " + string);
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

    private static void flatMap() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onComplete();
            }
        }).flatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) throws Exception {
                ArrayList<String> list = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    list.add("我是处理后的数据: " + i + " 第" + integer);
                }
                return Observable.fromIterable(list).delay(5, TimeUnit.MILLISECONDS);
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String value) {
                System.out.println(value);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }


    private static void zip() {
        Observable<Integer> observable1 = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                System.out.println("observable1:  1");
                e.onNext(1);
                System.out.println("observable1:  2");
                e.onNext(2);
                System.out.println("observable1:  3");
                e.onNext(3);
                System.out.println("observable1:  onComplete");
                e.onComplete();
            }
        }).subscribeOn(Schedulers.io());
        Observable<String> observable2 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                System.out.println("observable2:  一");
                e.onNext("一");
                System.out.println("observable2:  二");
                e.onNext("二");
                System.out.println("observable2:  三");
                e.onNext("三");
                System.out.println("observable2:  四");
                e.onNext("四");
                System.out.println("observable2:  onComplete");
                e.onComplete();
            }
        }).subscribeOn(Schedulers.io());
        Observable.zip(observable1, observable2, new BiFunction<Integer, String, String>() {
            @Override
            public String apply(Integer integer, String s) throws Exception {
                return integer + "    " + s;
            }
        }).
                subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("onSubscribe");
            }

            @Override
            public void onNext(String value) {
                System.out.println(value);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError");
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        });
    }
}

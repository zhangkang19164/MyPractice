package com.example;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MyClass {

    public static void main(String args[]) {
        test2();
    }

    private static void test() {
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
        observable.subscribe(observer);
    }

    private static void test2() {
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
}

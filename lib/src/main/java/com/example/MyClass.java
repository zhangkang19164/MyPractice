package com.example;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MyClass {
    public DiaoSI diaoSI;
    static DiaoSI DIAOSI = new DiaoSI();

    public MyClass() {
        diaoSI = new DiaoSI();
    }

    public long printString() {
        long currentTimeMillis = System.currentTimeMillis();
        for (int i = 0; i < 100000000; i++) {
            diaoSI.setName("测试样例" + i);
        }
        return System.currentTimeMillis() - currentTimeMillis;

    }

    public long printString2() {
        DiaoSI diaoSI = this.diaoSI;
        long currentTimeMillis = System.currentTimeMillis();
        for (int i = 0; i < 100000000; i++) {
            diaoSI.setName("测试样例" + i);
        }
        return System.currentTimeMillis() - currentTimeMillis;
    }

    public long printString3() {
        DiaoSI diaoSI = new DiaoSI();
        long currentTimeMillis = System.currentTimeMillis();
        for (int i = 0; i < 100000000; i++) {
            diaoSI.setName("测试样例" + i);
        }
        return System.currentTimeMillis() - currentTimeMillis;
    }

    public long printString4() {
        long currentTimeMillis = System.currentTimeMillis();
        for (int i = 0; i < 100000000; i++) {
            DIAOSI.setName("测试样例" + i);
        }
        return System.currentTimeMillis() - currentTimeMillis;
    }

    public static void main(String args[]) {
//        DecimalFormat decimalFormat=new DecimalFormat("#.0");
//        System.out.println(decimalFormat.format(3.144));
        int number=100;
        MyClass myClass = new MyClass();
        long time = 0;
        for (int i = 0; i < number; i++) {
            time += myClass.printString();
        }
        System.out.println("成员变量用时：" + (time / number));
        time = 0;
        for (int i = 0; i < number; i++) {
            time += myClass.printString2();
        }
        System.out.println("成员变量赋值给局部变量用时：" + (time / number));
        time = 0;
        for (int i = 0; i < number; i++) {
            time += myClass.printString3();
        }
        System.out.println("局部变量用时：" + (time / number));
        time = 0;
        for (int i = 0; i < number; i++) {
            time += myClass.printString4();
        }
        System.out.println("类变量用时：" + (time / number));

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

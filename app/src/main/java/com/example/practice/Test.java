package com.example.practice;

import android.os.Handler;
import android.os.Looper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * create time : 2017/08/02
 * desc        :
 */

public class Test {

    private void test() {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.submit(new MyRunnable(), Test.class);
        Set<Callable<String>> set = new HashSet<>();

        try {
            String s = executorService.invokeAny(set);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        List list = Collections.synchronizedList(new ArrayList<>());
        ThreadLocal<Boolean> booleanThreadLocal=new ThreadLocal<>();
        booleanThreadLocal.set(true);

        ThreadLocal<List> listThreadLocal=new ThreadLocal<>();
        listThreadLocal.remove();
        Looper.prepare();
        Looper.loop();
    }


    private class MyRunnable implements Runnable {

        @Override
        public void run() {

        }
    }

    private class MyCallable implements Callable<String> {

        @Override
        public String call() throws Exception {
            return null;
        }
    }
}

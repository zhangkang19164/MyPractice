package com.example.common.tools;

import android.app.Activity;
import android.support.annotation.IdRes;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by 92118 on 2017/12/7.
 */

public class FragmentTools {

    public static void replace(FragmentActivity activity, @IdRes int containerViewId, Fragment fragment) {
        FragmentManager supportFragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.replace(containerViewId, fragment);
        fragmentTransaction.commit();
    }

    public static void add(FragmentActivity activity, @IdRes int containerViewId, Fragment fragment){
        FragmentManager supportFragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.add(containerViewId, fragment);
        fragmentTransaction.commit();
    }



}

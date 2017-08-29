package com.example.practice.other.databinding.expressionchain;

import android.databinding.ObservableBoolean;

/**
 * create time : 2017/08/08
 * desc        :
 */

public class ExpressionChainBean {
    public ObservableBoolean observableBoolean;

    public ExpressionChainBean() {
        observableBoolean=new ObservableBoolean(true);
    }
}

package com.example.other.databinding.include;

import android.databinding.BaseObservable;

/**
 * create time : 2017/10/17
 * desc        :
 */

public class IncludeMode extends BaseObservable{
    private String name;
    private String password;


    public IncludeMode(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

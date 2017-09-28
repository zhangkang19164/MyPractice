package com.example.other.databinding.twoway;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.other.BR;


/**
 * create time : 2017/08/08
 * desc        :
 */

public class TwoWayModel extends BaseObservable {

    private String userName;
    private String passWord;

    public TwoWayModel(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }

    @Bindable
    public String getUserName() {
        return userName;

    }

    public void setUserName(String userName) {
        this.userName = userName;
        notifyPropertyChanged(BR.userName);
    }

    @Bindable
    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
        notifyPropertyChanged(BR.passWord);
    }
}

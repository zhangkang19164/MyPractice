package com.example.practice.other.databinding.lambda;

/**
 * create time : 2017/08/08
 * desc        :
 */

public class LambdaBean {

    private String name;
    private String passWord;

    public LambdaBean(String name, String passWord) {
        this.name = name;
        this.passWord = passWord;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}

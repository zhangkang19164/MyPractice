package com.example;

import com.google.gson.Gson;

/**
 * create time : 2017/08/01
 * desc        :
 */

public class GsonTest {

    public static void main(String[] args) {
        DiaoSI diaoSI = new DiaoSI();
        diaoSI.setName("张康");
        diaoSI.setAge(25);
        diaoSI.setBirthday("1992-07-16");
        diaoSI.setMajor(new String[]{"厨师", "挖掘机", "美容"});
        diaoSI.setHas_Car(false);
        diaoSI.setFangzi(null);
        Gson gson = new Gson();
        System.out.println(gson.toJson(diaoSI));
    }
}

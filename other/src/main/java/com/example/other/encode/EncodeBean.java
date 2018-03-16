package com.example.other.encode;

import android.databinding.BaseObservable;

/**
 * create time : 2017/10/21
 * desc        :
 */

public class EncodeBean extends BaseObservable {

    public static final String secretKey1 = "YQM3JMH6UDUNIYQ53U6BOZBOGYS6EVD7";
    public static final String secretKey2 = "ARZVR3XYIWA6U7YYL2S7WGTBJT3HRMV3O";
    private String resultForDES;
    private String resultForAES;

    public String getResultForDES() {
        return resultForDES;
    }

    public void setResultForDES(String resultForDES) {
        this.resultForDES = resultForDES;
    }

    public String getResultForAES() {
        return resultForAES;
    }

    public void setResultForAES(String resultForAES) {
        this.resultForAES = resultForAES;
    }
}

package com.example.other.encode;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.text.Editable;

import com.example.other.BR;

/**
 * create time : 2017/10/21
 * desc        :
 */

public class EncodeModel extends BaseObservable {
    private String initialValue = "";
    public static final String secretKey1 = "YQM3JMH6UDUNIYQ53U6BOZBOGYS6EVD7";
    public static final String secretKey2 = "ARZVR3XYIWA6U7YYL2S7WGTBJ3HRMV3O";
    private String resultForDES;
    private String resultForAES;

    /**
     * DES加密
     */
    public void DESEncode() {
        setResultForDES(DESUtil.encode(secretKey1, initialValue));
    }

    /**
     * DES解密
     */
    public void DESDecode() {
        setResultForDES(DESUtil.decode(secretKey1, getResultForDES()));
    }

    /**
     * AES 加密
     */
    public void AESEncode() {
        try {
//            setResultForAES(AESUtil.encrypt(secretKey2, getResultForDES()));
            setResultForAES(AESUtil.encrypt(secretKey2, "HOTFA|恒生001|HS0000000001|VYGTMHXVUUO4L3G23SCGRSTRUZ53J6RL"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * AES 解密
     */
    public void AESDecode() {
        try {
            setResultForAES(AESUtil.decrypt(secretKey2, getResultForAES()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void afterTextChanged(Editable s) {
        initialValue = s.toString();
    }

    public String getInitialValue() {
        return initialValue;
    }


    @Bindable
    public String getResultForDES() {
        return null == resultForDES ? initialValue : resultForDES;
    }

    public void setResultForDES(String resultForDES) {
        this.resultForDES = resultForDES;
        notifyPropertyChanged(BR.resultForDES);
    }

    @Bindable
    public String getResultForAES() {
        return null == resultForAES ? initialValue : resultForAES;
    }

    public void setResultForAES(String resultForAES) {
        this.resultForAES = resultForAES;
        notifyPropertyChanged(BR.resultForAES);
    }
}

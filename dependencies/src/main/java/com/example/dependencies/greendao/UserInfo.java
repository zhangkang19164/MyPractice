package com.example.dependencies.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * create time : 2017/10/16
 * desc        : 用户信息
 */
@Entity
public class UserInfo {
    @Id(autoincrement = true)
    private Long _id;
    //别名
    private String alias;
    //序列号
    private String serial_number;
    //秘钥
    private String secret_key;
    //密保密码
    private String security_password;
    //错误次数
    private int errors_number;

    @Generated(hash = 1605879342)
    public UserInfo(Long _id, String alias, String serial_number, String secret_key,
            String security_password, int errors_number) {
        this._id = _id;
        this.alias = alias;
        this.serial_number = serial_number;
        this.secret_key = secret_key;
        this.security_password = security_password;
        this.errors_number = errors_number;
    }
    public UserInfo( String alias, String serial_number, String secret_key,
                    String security_password, int errors_number) {
        this.alias = alias;
        this.serial_number = serial_number;
        this.secret_key = secret_key;
        this.security_password = security_password;
        this.errors_number = errors_number;
    }

    @Generated(hash = 1279772520)
    public UserInfo() {
    }
   

    @Override
    public String toString() {
        return "UserInfo{" +
                "_id=" + _id +
                ", alias='" + alias + '\'' +
                ", serial_number='" + serial_number + '\'' +
                ", secret_key='" + secret_key + '\'' +
                ", security_password='" + security_password + '\'' +
                ", errors_number=" + errors_number +
                '}';
    }


    public Long get_id() {
        return this._id;
    }


    public void set_id(Long _id) {
        this._id = _id;
    }


    public String getAlias() {
        return this.alias;
    }


    public void setAlias(String alias) {
        this.alias = alias;
    }


    public String getSerial_number() {
        return this.serial_number;
    }


    public void setSerial_number(String serial_number) {
        this.serial_number = serial_number;
    }


    public String getSecret_key() {
        return this.secret_key;
    }


    public void setSecret_key(String secret_key) {
        this.secret_key = secret_key;
    }


    public String getSecurity_password() {
        return this.security_password;
    }


    public void setSecurity_password(String security_password) {
        this.security_password = security_password;
    }


    public int getErrors_number() {
        return this.errors_number;
    }


    public void setErrors_number(int errors_number) {
        this.errors_number = errors_number;
    }
}

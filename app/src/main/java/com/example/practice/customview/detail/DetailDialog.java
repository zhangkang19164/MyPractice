package com.example.practice.customview.detail;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;

/**
 * author      : Android_张康
 * e-mail      : zhangkang19164@hundsun.com
 * create time : 2017/04/10
 * last time   : 2017/04/10
 * desc        : 详情页Dialog弹框
 * version     :   1.0
 */

public class DetailDialog extends Dialog {
    protected DetailDialog(@NonNull Context context) {
        super(context);
    }

    protected DetailDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    protected DetailDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public static class Builder {
        private Context mContext;
        private DateModel dateModel;

        public Builder(Context context) {
            this.mContext = context;
        }

        public Builder setDateMode(DateModel dateModel) {
            this.dateModel = dateModel;
            return this;
        }
    }


    public static class DateModel {

    }
}

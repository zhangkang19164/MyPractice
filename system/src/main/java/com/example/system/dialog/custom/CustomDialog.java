package com.example.system.dialog.custom;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialog;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.ViewGroup;

import com.example.system.R;

/**
 * 模仿AlertDialog完成自己的Dialog
 */

public class CustomDialog extends Dialog implements DialogInterface {
    private CustomAlertController mAlert;

    protected CustomDialog(@NonNull Context context) {
        this(context, 0);

    }

    protected CustomDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        mAlert = new CustomAlertController(getContext(), this, getWindow());

    }

    protected CustomDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        this(context, 0);
        setCancelable(cancelable);
        setOnCancelListener(cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAlert.installContent();
    }

    @Override
    public boolean onKeyDown(int keyCode, @NonNull KeyEvent event) {
        return mAlert.onKeyDown(keyCode, event) || super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, @NonNull KeyEvent event) {
        return mAlert.onKeyUp(keyCode, event) || super.onKeyUp(keyCode, event);

    }

    @Override
    public void setTitle(CharSequence title) {
        mAlert.setTitle(title);
    }

    public void setContent(CharSequence content) {
        mAlert.setContent(content);
    }

    public void setButton(CharSequence buttonText, DialogInterface.OnClickListener onClickListener) {
        mAlert.setButton(buttonText, onClickListener);
    }

    public static class Builder {
        private final CustomAlertController.CustomAlertParams p;

        public Builder(@NonNull Context context) {
            p = new CustomAlertController.CustomAlertParams(context);
        }

        public Builder setTitle(String mTitle) {
            p.mTitle = mTitle;
            return this;
        }

        public Builder setContent(CharSequence content) {
            p.mContent = content;
            return this;
        }

        public Builder setButton(String mButton, DialogInterface.OnClickListener onClickListener) {
            p.mButton = mButton;
            p.onClickListener = onClickListener;
            return this;
        }

        public CustomDialog create() {
            final CustomDialog customDialog = new CustomDialog(p.context);
            p.apply(customDialog.mAlert);
            return customDialog;
        }

        public CustomDialog show() {
            final CustomDialog customDialog = create();
            customDialog.show();
            return customDialog;
        }
    }

}

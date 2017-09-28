package com.example.system.dialog.custom;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.v7.app.AppCompatDialog;
import android.view.KeyEvent;

/**
 * 模仿AlertDialog完成自己的Dialog
 */

public class CustomDialog extends AppCompatDialog implements DialogInterface {
    private CustomAlertController mAlert;

    protected CustomDialog(@NonNull Context context) {
        this(context,0);

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

    public static class Builder {
        private final CustomAlertController.CustomAlertParams p;

        public Builder(@NonNull Context context) {
            p = new CustomAlertController.CustomAlertParams(context);
        }

        public Builder setContext(String mTitle) {
            p.mTitle = mTitle;
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

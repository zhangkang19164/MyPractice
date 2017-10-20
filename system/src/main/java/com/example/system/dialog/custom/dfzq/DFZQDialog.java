package com.example.system.dialog.custom.dfzq;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatDialog;

/**
 * create time : 2017/10/19
 * desc        : 东方证券中使用的dialog
 */

public class DFZQDialog extends AppCompatDialog implements DialogInterface{
    public DFZQDialog(Context context) {
        super(context);
    }

    public DFZQDialog(Context context, int theme) {
        super(context, theme);
    }

    protected DFZQDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }


}

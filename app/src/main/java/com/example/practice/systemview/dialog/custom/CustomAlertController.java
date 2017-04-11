package com.example.practice.systemview.dialog.custom;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatDialog;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.example.practice.R;

import java.lang.ref.WeakReference;

/**
 * 自定义Dialog管理者
 */

public class CustomAlertController {
    private final Context mContext;
    final AppCompatDialog mDialog;
    private final Window mWindow;
    Handler mHandler;

    private CharSequence mTitle;
    private TextView mTitleView;

    private static final class ButtonHandler extends Handler {
        // Button clicks have Message.what as the BUTTON{1,2,3} constant
        private static final int MSG_DISMISS_DIALOG = 1;

        private WeakReference<DialogInterface> mDialog;

        public ButtonHandler(DialogInterface dialog) {
            mDialog = new WeakReference<>(dialog);
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {

                case DialogInterface.BUTTON_POSITIVE:
                case DialogInterface.BUTTON_NEGATIVE:
                case DialogInterface.BUTTON_NEUTRAL:
                    ((DialogInterface.OnClickListener) msg.obj).onClick(mDialog.get(), msg.what);
                    break;

                case MSG_DISMISS_DIALOG:
                    ((DialogInterface) msg.obj).dismiss();
            }
        }
    }


    public CustomAlertController(Context context, AppCompatDialog dialog, Window window) {
        mContext = context;
        mDialog = dialog;
        mWindow = window;
        mHandler = new CustomAlertController.ButtonHandler(dialog);



         /* We use a custom title so never request a window title */
        dialog.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
    }

    public void setTitle(CharSequence title) {
        mTitle = title;
        if (mTitleView != null) {
            mTitleView.setText(title);
        }
    }

    public void installContent() {
        final int contentView = R.layout.custom_alert_dialog;
        mWindow.setContentView(contentView);
        setupView();
    }


    public boolean onKeyDown(int keyCode, @NonNull KeyEvent event) {
        return false;
    }

    public boolean onKeyUp(int keyCode, @NonNull KeyEvent event) {
        return false;
    }

    private void setupView() {
        //顶部View
        ViewGroup topPanel= (ViewGroup) mWindow.findViewById(R.id.custom_dialog_title_layout);
        setupTitle(topPanel);

        //中间View
        ViewGroup contentPanel= (ViewGroup) mWindow.findViewById(R.id.custom_dialog_content_layout);
        setupContent(contentPanel);

        //底部按钮
        ViewGroup buttonPanel= (ViewGroup) mWindow.findViewById(R.id.custom_dialog_buttons_layout);
        setupButtons(buttonPanel);

    }

    private void setupTitle(ViewGroup topPanel){
        mTitleView = (TextView) topPanel.findViewById(R.id.custom_dialog_title);
        final boolean hasTextTitle = !TextUtils.isEmpty(mTitle);
        if(hasTextTitle){
            mTitleView.setText(mTitle);
        }
    }

    private void setupContent(ViewGroup contentPanel){

    }

    private void setupButtons(ViewGroup buttonPanel){

    }
    /**
     * 自定义Dialo参数类
     */
    public static class CustomAlertParams {
        public Context context;
        public String mTitle;

        public CustomAlertParams(Context context) {
            this.context = context;
        }

        public void apply(CustomAlertController controller) {
            if (null != mTitle) {
                controller.setTitle(mTitle);
            }
        }
    }
}

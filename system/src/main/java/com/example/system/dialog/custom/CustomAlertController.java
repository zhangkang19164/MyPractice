package com.example.system.dialog.custom;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.example.system.R;

import java.lang.ref.WeakReference;

/**
 * 自定义Dialog管理者
 */

public class CustomAlertController {
    private final Context mContext;
    final Dialog mDialog;
    private final Window mWindow;

    private CharSequence mTitle;
    private CharSequence mContentText;

    private TextView mTitleView;
    private TextView mContentView;
    private TextView mButtonView;
    private CharSequence mButtonText;
    private Message mButtonMessage;
    private static final int DIALOG_BUTTON=2;
    private Handler mHandler;
    private final View.OnClickListener mButtonHandler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final Message m;
            if (v == mButtonView && mButtonMessage != null) {
                m = Message.obtain(mButtonMessage);
            } else  {
                m = null;
            }

            if (m != null) {
                m.sendToTarget();
            }

            mHandler.obtainMessage(ButtonHandler.MSG_DISMISS_DIALOG, mDialog)
                    .sendToTarget();
        }
    };


    public CustomAlertController(Context context, Dialog dialog, Window window) {
        mContext = context;
        mDialog = dialog;
        mWindow = window;
        mHandler=new ButtonHandler(mDialog);
    }

    public void setTitle(CharSequence title) {
        mTitle = title;
        if (mTitleView != null) {
            mTitleView.setText(title);
        }
    }

    public void setContent(CharSequence content) {
        mContentText = content;
        if (null != mButtonView) {
            mContentView.setText(mContentText);
        }
    }

    public void setButton(CharSequence buttonText,DialogInterface.OnClickListener onClickListener) {
        mButtonText = buttonText;
        if (TextUtils.isEmpty(buttonText)) {
            buttonText = "确定";
        }
        if (null != mButtonView) {
            mButtonView.setText(buttonText);
        }
        if(null!=onClickListener){
            mButtonMessage=mHandler.obtainMessage(DIALOG_BUTTON,onClickListener);
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
        ViewGroup topPanel = mWindow.findViewById(R.id.custom_dialog_title_layout);
        setupTitle(topPanel);

        //中间View
        ViewGroup contentPanel = mWindow.findViewById(R.id.custom_dialog_content_layout);
        setupContent(contentPanel);

        //底部按钮
        ViewGroup buttonPanel = mWindow.findViewById(R.id.custom_dialog_buttons_layout);
        setupButtons(buttonPanel);

    }

    private void setupTitle(ViewGroup topPanel) {
        mTitleView = topPanel.findViewById(R.id.custom_dialog_title);
        final boolean hasTextTitle = !TextUtils.isEmpty(mTitle);
        if (hasTextTitle) {
            mTitleView.setText(mTitle);
        } else {
            mTitleView.setVisibility(View.GONE);
        }
    }

    private void setupContent(ViewGroup contentPanel) {
        mContentView = contentPanel.findViewById(R.id.custom_dialog_content);
        final boolean hasContentText = !TextUtils.isEmpty(mContentText);
        if (hasContentText) {
            mContentView.setText(mContentText);
        }
    }

    private void setupButtons(ViewGroup buttonPanel) {
        mButtonView = buttonPanel.findViewById(R.id.custom_dialog_button);
        mButtonView.setOnClickListener(mButtonHandler);
        final boolean hasButtonText = !TextUtils.isEmpty(mButtonText);
        if (hasButtonText) {
            mButtonView.setText(mButtonText);
        }

    }

    private static final class ButtonHandler extends Handler {
        private static final int MSG_DISMISS_DIALOG = 1;

        private WeakReference<DialogInterface> mDialog;

        public ButtonHandler(DialogInterface dialog) {
            mDialog = new WeakReference<>(dialog);
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case DIALOG_BUTTON:
                    ((DialogInterface.OnClickListener) msg.obj).onClick(mDialog.get(), msg.what);
                    break;

                case MSG_DISMISS_DIALOG:
                    ((DialogInterface) msg.obj).dismiss();
            }
        }
    }

    /**
     * 自定义Dialo参数类
     */
    public static class CustomAlertParams {
        public Context context;
        public CharSequence mTitle;
        public CharSequence mContent;
        public CharSequence mButton;
        public DialogInterface.OnClickListener onClickListener;

        public CustomAlertParams(Context context) {
            this.context = context;
        }

        public void apply(CustomAlertController controller) {
            if (!TextUtils.isEmpty(mTitle)) {
                controller.setTitle(mTitle);
            }
            if (!TextUtils.isEmpty(mContent)) {
                controller.setContent(mContent);
            }

            if (!TextUtils.isEmpty(mButton)) {
                controller.setButton(mButton,onClickListener);
            }
        }
    }
}

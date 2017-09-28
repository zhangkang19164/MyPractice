package com.example.system.edittext;

import android.content.Context;
import android.util.AttributeSet;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;

/**
 * create time : 2017/06/08
 * desc        : EditText 多行且下方显示发送
 * http://blog.sina.com.cn/s/blog_8984d3f30101grbh.html
 */

public class MyEditText extends android.support.v7.widget.AppCompatEditText {


    public MyEditText(Context context) {
        super(context);
    }

    public MyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public InputConnection onCreateInputConnection(EditorInfo outAttrs) {
        //这里调用父类方法来初始化必要部分
        InputConnection connection = super.onCreateInputConnection(outAttrs);
        if (connection == null) {
            return null;
        }
        //移除EditorInfo.IME_FLAG_NO_ENTER_ACTION标志位
        outAttrs.imeOptions &= ~EditorInfo.IME_FLAG_NO_ENTER_ACTION;
        return connection;
    }
}


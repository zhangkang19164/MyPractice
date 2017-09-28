package com.example.system.textview.marquee;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.util.AttributeSet;

/**
 * create time : 2017/07/06
 * desc        : 横向自动滚动的 TextView
 */

public class MarqueeTextView extends AppCompatTextView {
    public MarqueeTextView(Context context) {
        this(context, null);
    }

    public MarqueeTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MarqueeTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setAttribute();
    }

    @Override
    public boolean isFocused() {
        //默认获取焦点
        return true;
    }

    //设置属性
    private void setAttribute() {
        //设置为滚动模式
        setEllipsize(TextUtils.TruncateAt.MARQUEE);
        //设置无限循环
        setMarqueeRepeatLimit(-1);
        //设置最大一行
        setSingleLine(true);
        //设置最大一行
        setMaxLines(1);
    }
}

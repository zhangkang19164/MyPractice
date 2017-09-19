package com.example.practice.customview.colorselect;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.graphics.ColorUtils;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.practice.R;

/**
 * create time : 2017/09/19
 * desc        : 简单的颜色选择器
 */

public class SimpleColorSelectView extends LinearLayoutCompat {

    private int colorA = 0, colorR = 0, colorG = 0, colorB = 0;
    private OnColorSelectListener onColorSelectListener;

    public SimpleColorSelectView(Context context) {
        this(context, null);
    }

    public SimpleColorSelectView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SimpleColorSelectView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        setOrientation(VERTICAL);
        initView(context);
    }

    private void initView(Context context) {
        inflate(context, R.layout.simple_color_select_view, this);
        SeekBar seekBarA = (SeekBar) findViewById(R.id.seek_bar_a);
        SeekBar seekBarR = (SeekBar) findViewById(R.id.seek_bar_r);
        SeekBar seekBarG = (SeekBar) findViewById(R.id.seek_bar_g);
        SeekBar seekBarB = (SeekBar) findViewById(R.id.seek_bar_b);
        final TextView showColor = (TextView) findViewById(R.id.show_color);
        final TextView seekBarTextA = (TextView) findViewById(R.id.seek_bar_a_text);
        final TextView seekBarTextR = (TextView) findViewById(R.id.seek_bar_r_text);
        final TextView seekBarTextG = (TextView) findViewById(R.id.seek_bar_g_text);
        final TextView seekBarTextB = (TextView) findViewById(R.id.seek_bar_b_text);
        SimpleOnSeekBarChangeListener onSeekBarChangeListener = new SimpleOnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                switch (seekBar.getId()) {
                    case R.id.seek_bar_a:
                        colorA = progress;
                        seekBarTextA.setText("当前透明值为：" + progress);
                        break;
                    case R.id.seek_bar_r:
                        colorR = progress;
                        seekBarTextR.setText("当前红色值为：" + progress);
                        break;
                    case R.id.seek_bar_g:
                        colorG = progress;
                        seekBarTextG.setText("当前黄色值为：" + progress);
                        break;
                    case R.id.seek_bar_b:
                        colorB = progress;
                        seekBarTextB.setText("当前蓝色值为：" + progress);
                        break;
                }
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                showColor.setBackgroundColor(Color.argb(colorA, colorR, colorG, colorB));
            }
        };
        seekBarA.setOnSeekBarChangeListener(onSeekBarChangeListener);
        seekBarR.setOnSeekBarChangeListener(onSeekBarChangeListener);
        seekBarG.setOnSeekBarChangeListener(onSeekBarChangeListener);
        seekBarB.setOnSeekBarChangeListener(onSeekBarChangeListener);
        findViewById(R.id.ok).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != onColorSelectListener) {
                    onColorSelectListener.selectColor(Color.argb(colorA, colorR, colorG, colorB));
                }
            }
        });
    }

    public void setOnColorSelectListener(OnColorSelectListener onColorSelectListener) {
        this.onColorSelectListener = onColorSelectListener;
    }

    private static abstract class SimpleOnSeekBarChangeListener implements SeekBar.OnSeekBarChangeListener {

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    }

    public interface OnColorSelectListener {
        void selectColor(int color);
    }
}

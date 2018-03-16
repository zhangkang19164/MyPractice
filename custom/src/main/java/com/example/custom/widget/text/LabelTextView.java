package com.example.custom.widget.text;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.util.AttributeSet;

import com.example.custom.R;

/**
 * create time : 2017/11/03
 * desc        : 带标签的TextView
 */

public class LabelTextView extends AppCompatTextView {
    private String label;
    private float labelTextSize;
    private float labelWidth;

    public LabelTextView(Context context) {
        this(context, null);
    }

    public LabelTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LabelTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.LabelTextView);
        label = typedArray.getString(R.styleable.LabelTextView_label);
        if (!TextUtils.isEmpty(label)) {
            labelWidth = typedArray.getDimension(R.styleable.LabelTextView_label_width, 0);
            labelTextSize = typedArray.getDimension(R.styleable.LabelTextView_label_text_size, -1);
        }

        typedArray.recycle();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        if (!TextUtils.isEmpty(label)) {
            if (labelTextSize == -1) {
                labelTextSize = getTextSize();
            }
            Paint paint = getPaint();
            paint.setTextSize(labelTextSize);

            float measureText = paint.measureText(label);
            if (labelWidth < measureText) {
                labelWidth = measureText;
            }
            canvas.drawText(label, getHeight() / 2, labelWidth / 2, paint);
        }

        super.onDraw(canvas);
    }

    @Override
    public int getCompoundPaddingLeft() {
        return (int) (labelWidth + super.getCompoundPaddingLeft() + 0.5);
    }

    @Override
    public int getCompoundPaddingStart() {
        return (int) (labelWidth + super.getCompoundPaddingStart() + 0.5);
    }
}

package com.example.dependencies.gif;

import android.content.Context;
import android.util.AttributeSet;

import com.bumptech.glide.Glide;

import pl.droidsonroids.gif.GifImageView;

/**
 * create time : 2017/09/28
 * desc        :
 */

public class MyGIfImageView extends GifImageView {

    public MyGIfImageView(Context context) {
        this(context, null);
    }

    public MyGIfImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyGIfImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setGifUrl(String gifUrl) {
        Glide.with(this)
                .asGif()
                .load(gifUrl)
                .into(this);
    }
}

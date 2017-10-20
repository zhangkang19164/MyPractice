package com.example.dependencies.gif;

import android.graphics.Bitmap;

import pl.droidsonroids.gif.GifDrawable;

/**
 * create time : 2017/09/28
 * desc        :
 */

public interface OnImageLoaded {
    //gif图片回调这个
    void onGifSuccess(GifDrawable gifDrawable);

    //普通图片回调这个
    void onSuccess(Bitmap bitmap);

    //图片加载失败
    void onFailed();
}

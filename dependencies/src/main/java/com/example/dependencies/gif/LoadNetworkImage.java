package com.example.dependencies.gif;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutionException;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifIOException;

/**
 * create time : 2017/09/28
 * desc        : 加载网络图片
 */

public class LoadNetworkImage {
    private Context appContext;
    private String url;
    private OnImageLoaded onImageLoaded;

    public LoadNetworkImage(Context appContext, String url, OnImageLoaded onImageLoaded) {
        this.appContext = appContext;
        this.url = url;
        this.onImageLoaded = onImageLoaded;
        if (!TextUtils.isEmpty(url)) {
            loadImage();
        }
    }

    private void loadImage() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                GifDrawable gifDrawable = null;
                File file = null;
                InputStream is = null;
                try {
                    //使用Glide获取图片文件
                    file = Glide.with(appContext)
                            .asFile()
                            .load(url)
                            .submit(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                            .get();
                    is = new BufferedInputStream(new FileInputStream(file));
                    //输入流需要支持mark,在java.io包中，“仅有两个类始终支持标记（BufferedInputStream和ByteArrayInputStream）
                    //假设图片为gif，如果不是，抛出GifIOException
                    gifDrawable = new GifDrawable(is);
                } catch (InterruptedException | ExecutionException | FileNotFoundException e) {
                    e.printStackTrace();
                } catch (GifIOException e) {
                    //捕获GifIOException，将该图片资源当成普通图片处理
                    Bitmap bitmap = BitmapFactory.decodeFile(file.getPath());
                    //回调函数
                    onImageLoaded.onSuccess(bitmap);
                    return;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (gifDrawable != null) {
                    onImageLoaded.onGifSuccess(gifDrawable);
                    return;
                }
                onImageLoaded.onFailed();
            }
        }).start();
    }


    public void setOnImageLoaded(OnImageLoaded onImageLoaded) {
        this.onImageLoaded = onImageLoaded;
    }
}

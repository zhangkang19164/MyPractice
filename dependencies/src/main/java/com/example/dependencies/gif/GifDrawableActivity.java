package com.example.dependencies.gif;

import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.example.dependencies.R;
import com.example.dependencies.databinding.ActivityGifDrawableBinding;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutionException;

import pl.droidsonroids.gif.AnimationListener;
import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifIOException;

public class GifDrawableActivity extends AppCompatActivity {
    private ActivityGifDrawableBinding binding;
    private String url="http://d.hiphotos.baidu.com/zhidao/wh%3D450%2C600/sign=60146df6570fd9f9a0425d6d101df81c/f703738da977391238f7c7ebfe198618377ae263.jpg";
    private String url1="http://img1.imgtn.bdimg.com/it/u=3529555063,706666158&fm=27&gp=0.jpg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_gif_drawable);
        initView();
    }

    private void initView() {
        Glide.with(this)
                .asGif()
                .load(url)
                .into(binding.imageView);
//        new LoadNetworkImage(this, url1, new OnImageLoaded() {
//            @Override
//            public void onGifSuccess(final GifDrawable gifDrawable) {
//               runOnUiThread(new Runnable() {
//                   @Override
//                   public void run() {
////                       gifDrawable.setLoopCount(1);
////                       gifDrawable.addAnimationListener(new AnimationListener() {
////                           @Override
////                           public void onAnimationCompleted(int loopNumber) {
////                               gifDrawable.seekTo(0);
////                           }
////                       });
//                       binding.gifImage01.setImageDrawable(gifDrawable);
//                   }
//               });
//            }
//
//            @Override
//            public void onSuccess(final Bitmap bitmap) {
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        binding.gifImage01.setImageBitmap(bitmap);
//                    }
//                });
//            }
//
//            @Override
//            public void onFailed() {
//
//            }
//        });
////        binding.gifImage02.setImageResource(R.drawable.png1);
////        binding.gifImage03.setImageURI(Uri.parse("http://d.hiphotos.baidu.com/zhidao/wh%3D450%2C600/sign=60146df6570fd9f9a0425d6d101df81c/f703738da977391238f7c7ebfe198618377ae263.jpg"));
    }


}

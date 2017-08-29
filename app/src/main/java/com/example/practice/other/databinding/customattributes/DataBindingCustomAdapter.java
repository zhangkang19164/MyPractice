package com.example.practice.other.databinding.customattributes;

import android.databinding.BindingAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * create time : 2017/08/08
 * desc        : DataBinding 自定义属性
 */

public class DataBindingCustomAdapter {
    @BindingAdapter({"app:imageUri"})
    public static void loadImageFromUrl(ImageView imageView, String url) {
        Glide.with(imageView).load(url).into(imageView);
    }

    @BindingAdapter({"app:changeTextColor"})
    public static void changeButtonTextColor(TextView textView, int color) {
        changeTextViewTextColor(textView,color,textView.getText().toString());
    }
    @BindingAdapter({"app:changeTextColor","app:changeTextContent"})
    public static void changeTextViewTextColor(TextView textView, int color,CharSequence string) {
        textView.setTextColor(color);
        textView.setText(string);
    }
}

package com.example.practice.textview.spannablestring;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.annotation.IdRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.practice.MainActivity;
import com.example.practice.R;

/**
 * 为字体设置效果
 * spannableString.setSpan(Object what, int start, int end, int flags)
 * what 	哪种效果
 * start	起始坐标
 * end		终点坐标
 * flags	共有四种属性,显示范围
 * Spanned.SPAN_INCLUSIVE_EXCLUSIVE 从起始下标到终了下标，包括起始下标
 * Spanned.SPAN_INCLUSIVE_INCLUSIVE 从起始下标到终了下标，同时包括起始下标和终了下标
 * Spanned.SPAN_EXCLUSIVE_EXCLUSIVE 从起始下标到终了下标，但都不包括起始下标和终了下标
 * Spanned.SPAN_EXCLUSIVE_INCLUSIVE 从起始下标到终了下标，包括终了下标
 */
public class SpannableStringMainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spannable_string_main);
        foregroundColorSpan();
        backgroundColorSpan();
        underlineSpan();
        strikethroughSpan();
        relativeSizeSpan();
        superscriptSpan();
        subscriptSpan();
        styleSpan();
        imageSpan();
        clickable();
        urlSpan();
    }

    /**
     * 设置文字的前景色
     */
    private void foregroundColorSpan() {
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(ContextCompat.getColor(this, R.color._0099EE));
        String text = "设置文字的前景色为淡蓝色";
        setSpan(R.id.foreground_color, text, foregroundColorSpan, 9, text.length());
    }

    /**
     * 设置文字的背景色
     */
    private void backgroundColorSpan() {
        BackgroundColorSpan backgroundColorSpan = new BackgroundColorSpan(ContextCompat.getColor(this, R.color._0099EE));
        String text = "设置文字的背景色为淡蓝色";
        setSpan(R.id.background_color, text, backgroundColorSpan, 9, text.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
    }

    /**
     * 添加下划线
     */
    private void underlineSpan() {
        UnderlineSpan underlineSpan = new UnderlineSpan();
        String text = "为文字添加下划线";
        setSpan(R.id.underline, text, underlineSpan, 5, text.length());
    }

    /**
     * 添加删除线
     */
    private void strikethroughSpan() {
        StrikethroughSpan strikethroughSpan = new StrikethroughSpan();
        String text = "为文字添加删除线";
        setSpan(R.id.strikethrough, text, strikethroughSpan, 5, text.length());

    }

    /**
     * 设置不同的大小
     */
    private void relativeSizeSpan() {
        RelativeSizeSpan relativeSizeSpan0 = new RelativeSizeSpan(1.2F);
        RelativeSizeSpan relativeSizeSpan1 = new RelativeSizeSpan(1.4F);
        RelativeSizeSpan relativeSizeSpan2 = new RelativeSizeSpan(1.6F);
        RelativeSizeSpan relativeSizeSpan3 = new RelativeSizeSpan(1.8F);
        RelativeSizeSpan relativeSizeSpan4 = new RelativeSizeSpan(2.0F);
        String text = "设置不同的大小";


        TextView textView = findTextViewById(R.id.relativesize);
        SpannableString spannableString = new SpannableString(text);
        spannableString.setSpan(relativeSizeSpan0, 1, 2, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(relativeSizeSpan1, 2, 3, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(relativeSizeSpan2, 3, 4, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(relativeSizeSpan3, 4, 5, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(relativeSizeSpan4, 5, 6, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        textView.setText(spannableString);
    }

    /**
     * 设置文字上标
     * flags没有效果 好像默认包括start 到 end
     */
    private void superscriptSpan() {
        SuperscriptSpan superscriptSpan = new SuperscriptSpan();
        String text = "为文字设置上标";
        setSpan(R.id.superscript, text, superscriptSpan, 5, text.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
    }

    /**
     * 设置文字下标
     */
    private void subscriptSpan() {
        SubscriptSpan subscriptSpan = new SubscriptSpan();
        String text = "为文字设置下标";
        setSpan(R.id.subscript, text, subscriptSpan, 5, text.length());

    }

    /**
     * 设置字体类型
     */
    private void styleSpan() {
        StyleSpan styleSpanBold = new StyleSpan(Typeface.BOLD);
        StyleSpan styleSpanItalic = new StyleSpan(Typeface.ITALIC);
        StyleSpan styleSpanBoldItalic = new StyleSpan(Typeface.BOLD_ITALIC);
        String text = "为字体设置粗体,斜体,粗斜体";

        TextView textView = findTextViewById(R.id.style);
        SpannableString spannableString = new SpannableString(text);
        spannableString.setSpan(styleSpanBold, 5, 7, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        spannableString.setSpan(styleSpanItalic, 8, 10, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        spannableString.setSpan(styleSpanBoldItalic, 10, 14, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        textView.setText(spannableString);
    }

    /**
     * 设置图片
     */
    private void imageSpan() {
        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.emoji_1f61c);
        drawable.setBounds(0, 0, 42, 42);
        ImageSpan imageSpan = new ImageSpan(drawable);
        String text = "我的后面是图片  ,真的";
        setSpan(R.id.image, text, imageSpan, 7, 8);
    }

    /**
     * 为文字添加点击效果
     * <p>
     * textView.setMovementMethod(LinkMovementMethod.getInstance()); 该方法是必须的否则无效
     */
    private void clickable() {
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Toast.makeText(widget.getContext(), "点击事件!", Toast.LENGTH_SHORT).show();
            }
        };
        String text = "为文字添加点击事件";

        SpannableString spannableString = new SpannableString(text);
        spannableString.setSpan(clickableSpan, 5, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        TextView textView = findTextViewById(R.id.clickble);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        textView.setText(spannableString);
    }

    private void urlSpan(){
        URLSpan urlSpan=new URLSpan("http://www.jianshu.com/users/dbae9ac95c78");
        String text="点击位置跳转超链接";
        SpannableString spannableString = new SpannableString(text);
        spannableString.setSpan(urlSpan, 6, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        TextView textView = findTextViewById(R.id.urlspan);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        textView.setText(spannableString);
    }
    /**
     * 设置文字效果
     *
     * @param textViewId textView id
     * @param text       文字
     * @param what       效果
     * @param start      开始
     * @param end        结束
     * @param flags      显示范围
     */
    private void setSpan(int textViewId, String text, Object what, int start, int end, int flags) {
        TextView textView = findTextViewById(textViewId);
        SpannableString spannableString = new SpannableString(text);
        spannableString.setSpan(what, start, end, flags);
        textView.setText(spannableString);
    }

    /**
     * 设置文字效果
     *
     * @param textViewId textView id
     * @param text       文字
     * @param what       效果
     * @param start      开始
     * @param end        结束
     */
    private void setSpan(int textViewId, String text, Object what, int start, int end) {
        setSpan(textViewId, text, what, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    }

    private TextView findTextViewById(@IdRes int id) {
        return (TextView) findViewById(id);
    }

}

package com.example.dependencies.lottie.encapsulation;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.airbnb.lottie.LottieAnimationView;
import com.example.dependencies.R;

public class AnimationSplashView extends RelativeLayout {
    //默认时间
    private static final long DEFAULT_DURATION = 3000;
    //显示动画控件
    private LottieAnimationView lottieAnimationView;

    private OnSplashRunListener onSplashRunListener;

    //持续时间
    private long duration = DEFAULT_DURATION;

    //动画文件名字
    private String animationName;


    public AnimationSplashView(Context context) {
        this(context, null);
    }

    public AnimationSplashView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AnimationSplashView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews();
    }


    private void initViews() {
        LayoutInflater.from(getContext()).inflate(R.layout.splash_view, this);
        lottieAnimationView = (LottieAnimationView) findViewById(R.id.splash_view_lottie);
    }

    //设置路径
    public AnimationSplashView setPathStr(String animationName) {
        this.animationName = animationName;
        return this;
    }

    //设置动画时间
    public AnimationSplashView setDuration(long duration) {
        if (duration > DEFAULT_DURATION) {
            this.duration = duration;
        }
        return this;
    }

    public AnimationSplashView setOnAnimationListener(OnSplashRunListener onSplashRunListener) {
        this.onSplashRunListener = onSplashRunListener;
        return this;
    }


    public void start() {
        lottieAnimationView.setAnimation(animationName);
        ValueAnimator countDownValueAnimator = ValueAnimator.ofFloat(0F, 1F).setDuration(duration);
        countDownValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                lottieAnimationView.setProgress(animation.getAnimatedFraction());
            }
        });
        countDownValueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (null != onSplashRunListener) {
                    onSplashRunListener.onSplashEnd();
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        countDownValueAnimator.start();
    }


    public interface OnSplashRunListener {

        void onSplashEnd();
    }


}

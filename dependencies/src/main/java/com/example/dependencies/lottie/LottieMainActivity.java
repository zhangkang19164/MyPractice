package com.example.dependencies.lottie;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.dependencies.R;

public class LottieMainActivity extends AppCompatActivity {

    LottieAnimationView lottieAnimationView;
    TextView lottieText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lottie_main);
        lottieAnimationView= (LottieAnimationView) findViewById(R.id.lottie_view);
        lottieText= (TextView) findViewById(R.id.lottie_text);
        initViews();
    }

    private void initViews() {
        lottieAnimationView.setAnimation("lottieTest.json");
        lottieAnimationView.loop(false);
        lottieAnimationView.playAnimation();
        lottieAnimationView.addAnimatorUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                //用来监听进度
                lottieText.setText(" 动画进度" + (int) (animation.getAnimatedFraction() * 100) + "%");
            }
        });
        lottieAnimationView.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                //动画开始或重新开始的时候调用
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                //动画暂停或者取消的时候调用
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                //取消动画的时候调用
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                //每次重复播放的时候调用
            }
        });
        //动态加载
//        JSONObject jsonObject=new JSONObject();
//        LottieComposition.Factory.fromJson(getResources(), jsonObject, new OnCompositionLoadedListener() {
//            @Override
//            public void onCompositionLoaded(LottieComposition composition) {
//                lottieAnimationView.setComposition(composition);
//                lottieAnimationView.playAnimation();
//            }
//        });
    }


    public void onViewClicked(View view) {
        int i = view.getId();
        if (i == R.id.lottie_start) {//开始播放
            lottieAnimationView.playAnimation();

        } else if (i == R.id.lottie_pause) {//暂停
            lottieAnimationView.pauseAnimation();

        } else if (i == R.id.lottie_resume) {//重新播放
            lottieAnimationView.resumeAnimation();

        } else if (i == R.id.lottie_cancel) {//停止 暂未发现和pauseAnimation区别
            lottieAnimationView.cancelAnimation();

        } else if (i == R.id.lottie_custom) {//停止 暂未发现和pauseAnimation区别
            ValueAnimator valueAnimator = ValueAnimator.ofFloat(0F, 1F).setDuration(5000);
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    lottieAnimationView.setProgress(animation.getAnimatedFraction());
                }
            });
            valueAnimator.start();

        }
    }

}

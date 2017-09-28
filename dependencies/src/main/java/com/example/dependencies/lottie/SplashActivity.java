package com.example.dependencies.lottie;

import android.animation.Animator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.dependencies.R;

public class SplashActivity extends AppCompatActivity {


    LottieAnimationView lottieAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        lottieAnimationView= (LottieAnimationView) findViewById(R.id.lottie_animation_view);
        initViews();
    }

    private void initViews() {
        lottieAnimationView.setImageAssetsFolder("screen");
        lottieAnimationView.setAnimation("splash_animation.json");
        lottieAnimationView.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Toast.makeText(SplashActivity.this, "结束啦!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        lottieAnimationView.playAnimation();
    }
}

package com.example.practice.dependencies.lottie;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.practice.MainActivity;
import com.example.practice.R;
import com.example.practice.dependencies.lottie.encapsulation.AnimationSplashView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity {

    @BindView(R.id.splash_splash)
    AnimationSplashView splashSplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        initViews();
    }

    private void initViews() {
        splashSplash.setPathStr("lottieTest.json")
                .setDuration(5000)
                .setOnAnimationListener(new AnimationSplashView.OnSplashRunListener() {

                    @Override
                    public void onSplashEnd() {
                        startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    }
                })
                .start();
    }
}

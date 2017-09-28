package com.example.other.databinding.animation;

import android.databinding.DataBindingUtil;
import android.databinding.OnRebindCallback;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.transition.TransitionManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.example.other.R;
import com.example.other.databinding.ActivityAnimationBinding;


public class AnimationActivity extends AppCompatActivity {
    private ActivityAnimationBinding animationBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        animationBinding = DataBindingUtil.setContentView(this, R.layout.activity_animation);
        animationBinding.addOnRebindCallback(new OnRebindCallback() {
            @Override
            public boolean onPreBind(ViewDataBinding binding) {
                View view=binding.getRoot();
                TransitionManager.beginDelayedTransition((ViewGroup) view);
                return true;
            }
        });
        animationBinding.setPresenter(new Presenter());

    }

    public class Presenter {
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            animationBinding.setIsVisibility(isChecked);
        }
    }
}

package com.example.custom.bezier.round;

import android.databinding.DataBindingUtil;
import android.databinding.OnRebindCallback;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.example.custom.R;
import com.example.custom.databinding.ActivityBezierRoundBinding;


public class BezierRoundActivity extends AppCompatActivity {
    private ActivityBezierRoundBinding binding;
    private boolean isNeedAnimator, isStartAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_bezier_round);
        initView();
    }

    private void initView() {
        binding.setPresenter(new Presenter());
        binding.addOnRebindCallback(new OnRebindCallback() {
            @Override
            public boolean onPreBind(ViewDataBinding binding) {
                View view=binding.getRoot();
                TransitionManager.beginDelayedTransition((ViewGroup) view);
                return true;
            }
        });
    }



    public class Presenter {
        public void onCheckedChanged1(CompoundButton buttonView, boolean isChecked) {
            isNeedAnimator = isChecked;
        }

        public void onCheckedChanged2(CompoundButton buttonView, boolean isChecked) {
            isStartAnimator = isChecked;
            binding.setIsVisibility(isChecked);
        }
        public void onViewClick(View view) {
            float value = Float.parseFloat(binding.currentValue.getText().toString());
            binding.bezierRoundView.setBottomText(binding.bottomText.getText().toString());
            binding.bezierRoundView.setBottomTextSize(Float.parseFloat(binding.bottomTextSize.getText().toString()));
            binding.bezierRoundView.setMinValue(Float.parseFloat(binding.minValue.getText().toString()));
            binding.bezierRoundView.setMaxValue(Float.parseFloat(binding.maxValue.getText().toString()));
            binding.bezierRoundView.setMiddleUnit(binding.middleUnit.getText().toString());
            binding.bezierRoundView.setMiddleUnitSize(Float.parseFloat(binding.middleUnitSize.getText().toString()));

            binding.bezierRoundView.setCurrentValue(value, isNeedAnimator, isStartAnimator);
        }
        public void onViewClick1(View view) {
            binding.bezierRoundView.startAnimator();

        }
    }
}

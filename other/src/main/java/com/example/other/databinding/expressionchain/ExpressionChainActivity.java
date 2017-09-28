package com.example.other.databinding.expressionchain;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


import com.example.other.R;
import com.example.other.databinding.ActivityExpressionChainBinding;

import java.util.Random;

public class ExpressionChainActivity extends AppCompatActivity {
    private Random random = new Random();
    private ExpressionChainBean bean=new ExpressionChainBean();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityExpressionChainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_expression_chain);
        binding.setPresenter(new Presenter());
        binding.setExpressionChainBean(bean);

    }

    public class Presenter {
        public void onClick(View view) {
            bean.observableBoolean.set(random.nextBoolean());
        }
    }

}

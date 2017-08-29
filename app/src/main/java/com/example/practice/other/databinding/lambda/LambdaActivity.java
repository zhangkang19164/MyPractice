package com.example.practice.other.databinding.lambda;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.practice.R;
import com.example.practice.databinding.ActivityLambdaBinding;

public class LambdaActivity extends AppCompatActivity {
    private ActivityLambdaBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_lambda);
        binding.setLambdaBean(new LambdaBean("张康", "123456"));
        binding.setPresenter(new Presenter());
    }

    public class Presenter {
        public void onClick(LambdaBean lambdaBean) {
            Toast.makeText(LambdaActivity.this, "onClick " + lambdaBean.getName(), Toast.LENGTH_SHORT).show();
        }

        public void onLongClick(LambdaBean lambdaBean) {
            Toast.makeText(LambdaActivity.this, "onLongClick " + lambdaBean.getPassWord(), Toast.LENGTH_SHORT).show();
        }

        public void onFocusChange(LambdaBean lambdaBean, Context context) {
            Toast.makeText(context, "onLongClick " + lambdaBean.getName() + lambdaBean.getPassWord(), Toast.LENGTH_SHORT).show();
        }
    }
}

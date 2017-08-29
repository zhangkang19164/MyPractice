package com.example.practice.other.databinding;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.practice.R;
import com.example.practice.databinding.ActivityDataBindingBinding;
import com.example.practice.other.databinding.animation.AnimationActivity;
import com.example.practice.other.databinding.customattributes.CustomAttributesActivity;
import com.example.practice.other.databinding.expressionchain.ExpressionChainActivity;
import com.example.practice.other.databinding.lambda.LambdaActivity;
import com.example.practice.other.databinding.recyclerview.MVVMOfRecyclerViewActivity;
import com.example.practice.other.databinding.twoway.TwoWayActivity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DataBindingMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDataBindingBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_data_binding);
        binding.setPresenter(new Presenter());
        List<Object> list=new ArrayList<>();
        Set<Object> set=new HashSet<>(list);


    }

    public class Presenter {
        public void toRecyclerView(View view) {
            startActivity(new Intent(view.getContext(), MVVMOfRecyclerViewActivity.class));
        }

        public void toTwoWay(View view) {
            startActivity(new Intent(view.getContext(), TwoWayActivity.class));
        }

        public void toExpressionChain(View view) {
            startActivity(new Intent(view.getContext(), ExpressionChainActivity.class));
        }

        public void toLambda(View view) {
            startActivity(new Intent(view.getContext(), LambdaActivity.class));
        }

        public void toAnimation(View view) {
            startActivity(new Intent(view.getContext(), AnimationActivity.class));
        }

        public void toCustomAttributes(View view) {
            startActivity(new Intent(view.getContext(), CustomAttributesActivity.class));
        }
    }


}

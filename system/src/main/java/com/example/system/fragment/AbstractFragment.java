package com.example.system.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.system.R;
import com.example.system.databinding.FragmentTestBinding;


/**
 * fragment，测试
 */

public class AbstractFragment extends android.support.v4.app.Fragment {
    private static final String TAG = "AbstractFragment";
    public static final String TAG_1 = "tag1";
    public static final String TAG_2 = "tag2";
    public static final String TAG_3 = "tag3";

    private String tag;
    private boolean isQuest;
    private FragmentTestBinding binding;

    public static AbstractFragment newInstance(String tag, boolean isQuest) {
        Bundle bundle = new Bundle();
        bundle.putString("tag", tag);
        bundle.putBoolean("isQuest", isQuest);
        AbstractFragment abstractFragment = new AbstractFragment();
        abstractFragment.setArguments(bundle);
        return abstractFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        tag = getArguments().getString("tag");
        isQuest = getArguments().getBoolean("isQuest", false);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_test, container, false);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (isQuest) {
            requestData();
        }
        Log.i(TAG, "onViewCreated: " + tag);
    }


    public void requestData() {
        Log.i(TAG, "requestData: "+tag);
        if (TAG_1.equals(tag)) {
            binding.text.setText("我是第一个页面");
        } else if (TAG_2.equals(tag)) {
            binding.text.setText("我是第二个页面");
        } else if (TAG_3.equals(tag)) {
            binding.text.setText("我是第三个页面");
        } else {
            binding.text.setText("发生错误了");
        }
        if (TAG_1.equals(tag)) {
            binding.text2.setText("我是第一个页面,正常加载数据");
        } else if (TAG_2.equals(tag)) {
            binding.text2.setText("我是第二个页面，正常加载数据");
        } else if (TAG_3.equals(tag)) {
            binding.text2.setText("我是第三个页面，正常加载数据");
        } else {
            binding.text2.setText("发生错误了，正常加载数据");
        }
    }

    public void reset(){
        binding.text.setText("");
        binding.text2.setText("");
    }

}

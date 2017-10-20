package com.example.dependencies.greendao;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.dependencies.R;
import com.example.dependencies.databinding.ActivityGreenDaoBinding;

import java.util.ArrayList;
import java.util.List;

public class GreenDaoActivity extends AppCompatActivity {
    private static final String TAG = "GreenDaoActivity";
    private ActivityGreenDaoBinding binding;
    private UserInfoDaoUtils userInfoDaoUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_green_dao);
        userInfoDaoUtils = new UserInfoDaoUtils(this);
        initView();
    }

    private void initView() {
        binding.setPresenter(new Presenter());
    }

    public class Presenter {
        public void insertOn() {
            UserInfo userInfo = new UserInfo("第一条", "123456789", "00127575", "123456", 0);
            userInfoDaoUtils.insertUserInfo(userInfo);
        }

        public void insertMult() {
            List<UserInfo> list = new ArrayList<>();
            for (long i = 1; i < 5; i++) {
                UserInfo userInfo = new UserInfo("第" + i + "条", "123456789", "00127575", "123456", 0);
                list.add(userInfo);
            }
            userInfoDaoUtils.insertMultUserInfo(list);
        }

        public void queryAll() {
            List<UserInfo> userInfos = userInfoDaoUtils.queryAllUserInfo();
            for (UserInfo userInfo : userInfos) {
                Log.i(TAG, "queryAll: " + userInfo.toString());
            }
        }

        public void deleteAll() {
            Log.i(TAG, "deleteAll: " + userInfoDaoUtils.queryUserInfo().toString());
        }

    }
}

package com.example.other.fragment;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.example.other.R;
import com.example.other.fragment.fragment.Switch1Fragment;
import com.example.other.fragment.fragment.SwitchBaseFragment;

public class FragmentSwitchActivity extends AppCompatActivity {
    private SwitchBaseFragment currentFragment;
    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_switch);
    }


    private void initView() {
        setDefaultFragment();

    }

    private void setDefaultFragment() {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack(Switch1Fragment.class.getName());
        fragmentTransaction.add(R.id.fragment_frame, new Switch1Fragment());
        fragmentTransaction.commit();
    }

    /**
     * 下一个页面
     */
    public void nextFragment(Bundle bundle) {

        changeTitle();
    }

    /**
     * 上一个页面
     */
    public void previousFragment(Bundle bundle) {


        changeTitle();
    }

    private void changeTitle() {
        if (null != currentFragment) {
            setTitle(currentFragment.getTitle());
        }
    }
}

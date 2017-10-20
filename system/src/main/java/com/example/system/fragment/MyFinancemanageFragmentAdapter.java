package com.example.system.fragment;


import android.app.Fragment;
import android.app.FragmentManager;

import java.util.List;

/**
 * Created by hspcadmin on 2017/10/18.
 */

public class MyFinancemanageFragmentAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragmentList;

    public MyFinancemanageFragmentAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList == null ? 0 : fragmentList.size();
    }

}

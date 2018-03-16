package com.example.common.tools;

import android.graphics.Typeface;
import android.support.design.widget.TabLayout;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * create time : 2017/11/08
 * desc        : TabLayout 工具类
 */

public class TabLayoutTools {

    /**
     * 改变TabLayout 选中项为加粗
     *
     * @param tabLayout 需要改变的tabLayout
     */
    public static void changeTabLayoutSelectBold(TabLayout tabLayout) {
        tabLayout.addOnTabSelectedListener(new SelectBoldOnTabSelectedListener(tabLayout));
    }


    private static class SelectBoldOnTabSelectedListener implements TabLayout.OnTabSelectedListener {
        private TabLayout tabLayout;

        private SelectBoldOnTabSelectedListener(TabLayout tabLayout) {
            this.tabLayout = tabLayout;
        }

        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            View view = tab.getCustomView();
            if (null == view) {
                TextView textView = new TextView(tabLayout.getContext());
                textView.setId(android.R.id.text1);
                ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                textView.setLayoutParams(layoutParams);
                textView.setGravity(Gravity.CENTER);
                tab.setCustomView(textView);
            }
            TextView textView = tab.getCustomView().findViewById(android.R.id.text1);
            textView.setTextColor(tabLayout.getTabTextColors());
            textView.setTypeface(Typeface.DEFAULT_BOLD);
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {
            View view = tab.getCustomView();
            if (null == view) {
                TextView textView = new TextView(tabLayout.getContext());
                textView.setId(android.R.id.text1);
                ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                textView.setLayoutParams(layoutParams);
                textView.setGravity(Gravity.CENTER);
                tab.setCustomView(textView);
            }
            TextView textView = tab.getCustomView().findViewById(android.R.id.text1);
            textView.setTypeface(Typeface.DEFAULT);
        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    }

}

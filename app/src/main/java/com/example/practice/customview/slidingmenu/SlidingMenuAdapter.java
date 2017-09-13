package com.example.practice.customview.slidingmenu;

import com.example.practice.R;
import com.example.practice.systemview.recyclerview.common.CommonRecyclerViewAdapter;
import com.example.practice.systemview.recyclerview.common.CommonViewHolder;

import java.util.List;

/**
 * create time : 2017/08/30
 * desc        :
 */

public class SlidingMenuAdapter extends CommonRecyclerViewAdapter<String> {
    private SlidingMenu slidingMenu;

    public SlidingMenuAdapter() {
    }

    public SlidingMenuAdapter(int layoutId) {
        super(layoutId);
    }

    public SlidingMenuAdapter(List<String> list, int layoutId) {
        super(list, layoutId);
    }

    @Override
    public void convert(CommonViewHolder holder, String s) {
        holder.setText(R.id.text, s);
    }

    public void holdOpenMenu(SlidingMenu slidingMenu) {
        this.slidingMenu = slidingMenu;
    }

    public void closeOpenMenu() {
        if (null != slidingMenu && slidingMenu.isOpen()) {
            slidingMenu.closeMenu();
        }
    }
}

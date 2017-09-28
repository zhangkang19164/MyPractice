package com.example.system.recyclerview.pinedview.view;

import android.view.View;

/**
 * create time : 2017/09/08
 * desc        :
 */

public interface PinedHeaderCallBack {
    View getHeaderView();

    void updateHeaderView(View headerView, int groupIndex);
}

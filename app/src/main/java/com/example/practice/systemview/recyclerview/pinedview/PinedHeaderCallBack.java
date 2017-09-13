package com.example.practice.systemview.recyclerview.pinedview;

import android.view.View;

/**
 * create time : 2017/09/08
 * desc        :
 */

public interface PinedHeaderCallBack {
    View getHeaderView();

    void updateHeaderView(View var1, int var2);
}

package com.example.other.databinding.twoway;

import android.databinding.BindingAdapter;
import android.databinding.InverseBindingAdapter;
import android.databinding.InverseBindingListener;
import android.databinding.InverseBindingMethod;
import android.databinding.InverseBindingMethods;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

/**
 * create time : 2017/08/09
 * desc        :
 */
@InverseBindingMethods({
        @InverseBindingMethod(type = SwipeRefreshLayout.class, attribute = "refreshing",
                event = "refreshingAttrChanged", method = "isRefreshing")
})
public class SwipeRefreshLayoutMethod {
    @BindingAdapter("refreshingAttrChanged")
    public static void setOnRefreshListener(SwipeRefreshLayout swipeRefreshLayout, final InverseBindingListener inverseBindingListener) {
        if (null == inverseBindingListener) {
            swipeRefreshLayout.setOnRefreshListener(null);
        } else {
            swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    inverseBindingListener.onChange();
                }
            });
        }
    }

    @BindingAdapter(value = "display")
    public static void setDisplay(CustomView customView, boolean isDisplay) {
        customView.setVisibility(isDisplay ? View.VISIBLE : View.GONE);
    }
    @InverseBindingAdapter(attribute = "display",event = "displayAttrChanged")
    public static boolean getDisplay(CustomView customView) {
        return customView.getVisibility() == View.VISIBLE;
    }

}

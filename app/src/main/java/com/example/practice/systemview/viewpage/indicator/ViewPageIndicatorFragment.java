package com.example.practice.systemview.viewpage.indicator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * <pre>
 *     author : Android_张康
 *     e-mail : zhangkang19164
 *     time   : 2017/03/26
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class ViewPageIndicatorFragment extends Fragment {


    public static ViewPageIndicatorFragment newIntance(String title) {
        Bundle bundle = new Bundle();
        bundle.putString("key", title);


        ViewPageIndicatorFragment fragment = new ViewPageIndicatorFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        String title;
        if (null != bundle) {
            title = bundle.getString("key");
        } else {
            title = "出错啦！";
        }
        TextView textView = new TextView(getActivity());
        textView.setText(title);
        textView.setGravity(Gravity.CENTER);
        return textView;
    }
}

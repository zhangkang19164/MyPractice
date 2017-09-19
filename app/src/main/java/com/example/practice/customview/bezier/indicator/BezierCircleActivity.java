package com.example.practice.customview.bezier.indicator;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.practice.R;
import com.example.practice.customview.bezier.indicator.view.CircleIndicatorView;
import com.example.practice.databinding.ActivityBezierCircleBinding;

import java.util.ArrayList;
import java.util.List;

public class BezierCircleActivity extends AppCompatActivity {
    private ActivityBezierCircleBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_bezier_circle);
        init();
    }

    private void init() {
        Adapter adapter = new Adapter(this);
        binding.viewPage.setAdapter(adapter);
        binding.indicatorViewPage.setOnSelectIndicatorColor(adapter);
        binding.indicatorViewPage.setupWithViewPager(binding.viewPage);
    }

    private static class Adapter extends PagerAdapter implements CircleIndicatorView.OnSelectIndicatorColor {
        private List<View> viewList;
        private String[] tabs = new String[]{"沪深", "板块", "沪港通", "港股通"};
        private int[] colors;

        public Adapter(Context context) {
            viewList = new ArrayList<>();
            for (String tab : tabs) {
                TextView t = new TextView(context);
                t.setGravity(Gravity.CENTER);
                t.setText(tab);
                t.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);
                t.setTextColor(ContextCompat.getColor(context, R.color._1E1E1E));
                viewList.add(t);
            }
            colors = new int[]{
                    ContextCompat.getColor(context, R.color._1F1F1F),
                    ContextCompat.getColor(context, R.color._3C9FFC),
                    ContextCompat.getColor(context, R.color.colorAccent),
                    ContextCompat.getColor(context, R.color._FF3E30)};
        }

        @Override
        public int getCount() {
            return viewList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(viewList.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(viewList.get(position));
            return viewList.get(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabs[position];
        }

        @Override
        public int getSelectIndicatorColor(int position) {
            return colors[position];
        }
    }
}

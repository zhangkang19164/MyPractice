package com.example.custom.imooc.pie.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.custom.R;
import com.example.custom.imooc.pie.PieChartBean;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * create time : 2017/09/26
 * desc        :
 */

public class PieChartFragment extends Fragment {
    private static final String KEY_DATE = "data";
    private PieChartBean pieChartBean;
    private PieChart pieChart;

    public static PieChartFragment newInstance(PieChartBean pieChartBean) {
        Bundle args = new Bundle();
        args.putParcelable(KEY_DATE, pieChartBean);
        PieChartFragment fragment = new PieChartFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null) {
            pieChartBean = arguments.getParcelable(KEY_DATE);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pie_chart, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        pieChart = ((PieChart) view.findViewById(R.id.pie_chart));
        initView();
    }

    private void initView() {
        setData();
        //设置是否允许旋转
        pieChart.setRotationEnabled(false);
        //设置颜色标记是否显示
        pieChart.getLegend().setEnabled(false);
        pieChart.setDescription(new Description());
    }

    private void setData() {
        List<PieEntry> pieEntries = new ArrayList<>();
        for (PieChartBean.ObjBean objBean : pieChartBean.getObj()) {
            pieEntries.add(new PieEntry(objBean.getValue(),objBean.getTitle()));
        }
        PieDataSet iPieDataSet = new PieDataSet(pieEntries, pieChartBean.getDate());
        iPieDataSet.setColors(new int[]{R.color._FF3E30,R.color.colorAccent,R.color._3C9FFC},getContext());
        PieData pieData = new PieData(iPieDataSet);
        pieChart.setData(pieData);
    }


}

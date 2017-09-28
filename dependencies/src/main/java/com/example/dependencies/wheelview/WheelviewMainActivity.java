package com.example.dependencies.wheelview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.dependencies.R;
import com.wx.wheelview.adapter.ArrayWheelAdapter;
import com.wx.wheelview.widget.WheelView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WheelviewMainActivity extends AppCompatActivity {


    private String[] businessTypeFZ = {"公司收购申报业务", "公司收购查询申报", "公司招股申报业务"
            , "公司招股查询申报", "供股行权申报业务", "供股行权查询业务"
            , "红利现金选择权申报", "红利现金选择权查询申报"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wheelview_main);
        initViews();
    }

    private void initViews() {
        WheelView wheelView = (WheelView) findViewById(R.id.wheelview);
        wheelView.setWheelAdapter(new ArrayWheelAdapter(this));
        List<String> stringList = new ArrayList<>();
        Collections.addAll(stringList, businessTypeFZ);
        wheelView.setWheelSize(7);
        wheelView.setWheelData(stringList);
//        WheelView.WheelViewStyle wheelViewStyle = new WheelView.WheelViewStyle();
//        wheelViewStyle.backgroundColor = ContextCompat.getColor(this, R.color._C4C4C4);
//
//
//
//
//
//        wheelView.setStyle(wheelViewStyle);
    }

}

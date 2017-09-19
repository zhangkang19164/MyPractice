package com.example.practice.other.loadconfig;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Xml;
import android.view.View;

import com.example.common.view.common.CommonRecyclerViewAdapter;
import com.example.common.view.common.CommonViewHolder;
import com.example.practice.R;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoadConfigMainActivity extends AppCompatActivity {


    @BindView(R.id.load_config_recycler_view)
    RecyclerView loadConfigRecyclerView;
    List<ConfigItem> configItemList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_config_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.load_config_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.load_config_button:
                new Thread() {
                    @Override
                    public void run() {
                        loadConfig();
                    }
                }.start();
                break;
        }
    }


    private void initViews() {
        loadConfigRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        loadConfigRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        loadConfigRecyclerView.setAdapter(new CommonRecyclerViewAdapter<ConfigItem>(configItemList, R.layout.item_trade_common_list) {

            @Override
            public void convert(CommonViewHolder holder, ConfigItem configItem) {
                String s = "根节点是 " + configItem.element + " name = " + configItem.name + " hint = "
                        + configItem.hint + " caption = " + configItem.caption;
                holder.setText(R.id.trade_common_list, s);
            }
        });
    }

    private void loadConfig() {

        InputStream inputStream;
        inputStream = getResources().openRawResource(R.raw.test);
        XmlPullParser xmlPullParser = Xml.newPullParser();

        ConfigItem configItem = null;
        try {
            xmlPullParser.setInput(inputStream, "UTF-8");
            int event = xmlPullParser.getEventType();
            String name;
            while (event != XmlPullParser.END_DOCUMENT) {
                switch (event) {
                    //开始解析事件
                    case XmlPullParser.START_DOCUMENT:

                        break;
                    //开始解析节点
                    case XmlPullParser.START_TAG:
                        configItem = new ConfigItem();
                        name = xmlPullParser.getName();
                        configItem.element = name;
                        for (int i = 0; i < xmlPullParser.getAttributeCount(); i++) {
                            String ss = xmlPullParser.getAttributeName(i);
                            switch (ss) {
                                case "name":
                                    configItem.name = xmlPullParser.getAttributeValue(i);
                                    break;
                                case "hint":
                                    configItem.hint = xmlPullParser.getAttributeValue(i);
                                    break;
                                case "caption":
                                    configItem.caption = xmlPullParser.getAttributeValue(i);
                                    break;
                            }
                        }

                        configItemList.add(configItem);
                        break;
                    //结束解析节点
                    case XmlPullParser.END_TAG:

                        break;
                }
                event = xmlPullParser.next();
            }
        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }
        handler.sendEmptyMessage(0);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            initViews();
        }
    };
}

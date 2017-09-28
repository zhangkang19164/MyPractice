package com.example.system.recyclerview.edit;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.system.R;
import com.example.system.dialog.system.SimpleAdapter;

import java.util.ArrayList;
import java.util.List;


public class RecyclerEditActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Button button01;
    Button button02;
    private RecyclerEditAdapter adapter;
    private List<RecyclerEditBean> recyclerEditBeanList;
    private SimpleAdapter simpleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_edit);
        initViews();
        initDatas();
    }

    private void initViews() {
        recyclerView= (RecyclerView) findViewById(R.id.recycler_view);
        button01= (Button) findViewById(R.id.button_01);
        button02= (Button) findViewById(R.id.button_02);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecyclerEditAdapter();
        simpleAdapter = new SimpleAdapter();
        button01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.notifyDataSetChanged();
            }
        });
        button02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String[]> list = new ArrayList<>();
                for (RecyclerEditBean recyclerEditBean : recyclerEditBeanList) {
                    list.add(new String[]{recyclerEditBean.getName(), recyclerEditBean.getEditTextStr()});
                }
                simpleAdapter.setList(list);
                button_05();
            }
        });
        recyclerView.setAdapter(adapter);
    }

    private void initDatas() {
        recyclerEditBeanList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            RecyclerEditBean bean = new RecyclerEditBean();
            bean.setName("名字 " + i);
            recyclerEditBeanList.add(bean);
        }
        adapter.setRecyclerEditBeanList(recyclerEditBeanList);
    }

    private void button_05() {
        //自定义ListView列表
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                //设置Adapter 监听	需要注意的是该方法点击后会关闭dialog
//				.setAdapter(new SimpleAdapter(), new DialogInterface.OnClickListener() {
//					@Override
//					public void onClick(DialogInterface dialog, int which) {
//						// which 点击的是第几个
//						Toast.makeText(MainActivity.this, "which = " + which, Toast.LENGTH_SHORT).show();
//					}
//				})
                //如果需要点击后不关闭 dialog 可以使用该方法
                .setSingleChoiceItems(simpleAdapter, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//						Toast.makeText(MainActivity.this, "which = " + which, Toast.LENGTH_SHORT).show();
                    }
                })
                .create();
        alertDialog.show();
    }
}

package com.example.practice.systemview.recyclerview.drag;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.common.view.common.CommonRecyclerViewAdapter;
import com.example.common.view.common.CommonViewHolder;
import com.example.common.view.common.OnItemClickListener;
import com.example.practice.R;
import com.example.practice.systemview.recyclerview.drag.view.DragItemTouchHelper;

import java.util.ArrayList;

public class DragRecyclerViewActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private CommonRecyclerViewAdapter<String> adapter;
    private DragItemTouchHelper dragItemTouchHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag_recycler_view);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            strings.add("" + i);
        }

        adapter = new CommonRecyclerViewAdapter<String>(strings, R.layout.item_child_two) {

            @Override
            public void convert(final CommonViewHolder holder, String s) {
                holder.setText(R.id.item_child_text, s);
                holder.getView(R.id.drag_view).setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if(event.getAction()==MotionEvent.ACTION_DOWN){
                            dragItemTouchHelper.startDrag(holder);
                            return true;
                        }
                        return false;
                    }
                });
            }
        };
        adapter.setOnItemClickListener(new OnItemClickListener<String>() {
            @Override
            public void onItemClickListener(View view, String s) {
                Toast.makeText(DragRecyclerViewActivity.this, s, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClickListener(View view, String s) {

            }
        });
        dragItemTouchHelper=new DragItemTouchHelper(new DragItemTouchHelper.DragCallback(new DragItemTouchHelper.DragOnItemTouchCallback(recyclerView, strings), false, false));
        dragItemTouchHelper.attachToRecyclerView(recyclerView);
//        DragItemTouchHelper.staticAttachToRecyclerView(strings, recyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    public void onViewClick(View view) {
        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            strings.add("新的" + i);
        }
        recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(),4));
        adapter.setList(strings);
        adapter.notifyDataSetChanged();
        DragItemTouchHelper.staticRefreshData(strings);
    }
}

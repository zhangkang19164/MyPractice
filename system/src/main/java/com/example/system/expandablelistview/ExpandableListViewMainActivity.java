package com.example.system.expandablelistview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.system.R;
import com.example.system.expandablelistview.type.ExpandableListViewTypeActivity;


public class ExpandableListViewMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable_list_view_main);
        findViewById(R.id.to_expandable_listView_type).setOnClickListener(onClickListener);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Class<? extends Activity> toActivity = null;
            int i = v.getId();
            if (i == R.id.to_expandable_listView_type) {
                toActivity = ExpandableListViewTypeActivity.class;

            }
            startActivity(new Intent(v.getContext(), toActivity));
        }
    };

}

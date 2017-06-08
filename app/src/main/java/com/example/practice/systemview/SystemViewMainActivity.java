package com.example.practice.systemview;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.practice.MainActivity;
import com.example.practice.R;
import com.example.practice.systemview.dialog.DialogMainActivity;
import com.example.practice.systemview.expandablelistview.ExpandableListViewMainActivity;
import com.example.practice.systemview.listview.ListViewMainActivity;
import com.example.practice.systemview.popupwindow.PopupWindowMainActivity;
import com.example.practice.systemview.recyclerview.RecyclerViewMainActivity;
import com.example.practice.systemview.spinner.SpinnerMainActivity;
import com.example.practice.systemview.textview.TextViewMainActivity;
import com.example.practice.systemview.viewpage.ViewPageMainActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SystemViewMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_view_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.to_list_view, R.id.to_recyclerview,
            R.id.to_text_view, R.id.to_expandable_listView,
            R.id.to_dialog, R.id.to_viewpage,
            R.id.to_popupwindow,R.id.to_spinner})
    public void onViewClicked(View view) {
        Class<? extends Activity> toActivity;
        switch (view.getId()) {
            case R.id.to_list_view:
                toActivity = ListViewMainActivity.class;
                break;
            case R.id.to_recyclerview:
                toActivity = RecyclerViewMainActivity.class;
                break;
            case R.id.to_text_view:
                toActivity = TextViewMainActivity.class;
                break;
            case R.id.to_expandable_listView:
                toActivity = ExpandableListViewMainActivity.class;
                break;
            case R.id.to_dialog:
                toActivity = DialogMainActivity.class;
                break;
            case R.id.to_viewpage:
                toActivity = ViewPageMainActivity.class;
                break;
            case R.id.to_popupwindow:
                toActivity = PopupWindowMainActivity.class;
                break;
            case R.id.to_spinner:
                toActivity = SpinnerMainActivity.class;
                break;
            default:
                toActivity = MainActivity.class;
                break;
        }
        startActivity(new Intent(view.getContext(), toActivity));
    }
}

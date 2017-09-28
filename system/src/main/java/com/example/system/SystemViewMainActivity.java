package com.example.system;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.system.dialog.DialogMainActivity;
import com.example.system.expandablelistview.ExpandableListViewMainActivity;
import com.example.system.listview.ListViewMainActivity;
import com.example.system.popupwindow.PopupWindowMainActivity;
import com.example.system.recyclerview.RecyclerViewMainActivity;
import com.example.system.spinner.SpinnerMainActivity;
import com.example.system.textview.TextViewMainActivity;
import com.example.system.viewpage.ViewPageMainActivity;


public class SystemViewMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_view_main);
    }


    public void onViewClicked(View view) {
        Class<? extends Activity> toActivity = null;
        int i = view.getId();
        if (i == R.id.to_list_view) {
            toActivity = ListViewMainActivity.class;

        } else if (i == R.id.to_recyclerview) {
            toActivity = RecyclerViewMainActivity.class;

        } else if (i == R.id.to_text_view) {
            toActivity = TextViewMainActivity.class;

        } else if (i == R.id.to_expandable_listView) {
            toActivity = ExpandableListViewMainActivity.class;

        } else if (i == R.id.to_dialog) {
            toActivity = DialogMainActivity.class;

        } else if (i == R.id.to_viewpage) {
            toActivity = ViewPageMainActivity.class;

        } else if (i == R.id.to_popupwindow) {
            toActivity = PopupWindowMainActivity.class;

        } else if (i == R.id.to_spinner) {
            toActivity = SpinnerMainActivity.class;

        }
        startActivity(new Intent(view.getContext(), toActivity));
    }

    public void showSnackBar(final View view) {
        Snackbar snackbar = Snackbar.make(view, "这是测试的!", Snackbar.LENGTH_SHORT)
                .addCallback(new Snackbar.Callback() {

                })
                .setActionTextColor(ContextCompat.getColor(view.getContext(), R.color._FF3E30))
                .setAction("点击消失", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (view instanceof Button) {
                            Button b = (Button) view;
                            b.setText("你看!我变了");
                        }
                    }
                });
        snackbar.addCallback(new Snackbar.Callback() {
            @Override
            public void onShown(Snackbar sb) {
                super.onShown(sb);
            }

            @Override
            public void onDismissed(Snackbar transientBottomBar, int event) {
                super.onDismissed(transientBottomBar, event);
            }
        });
        setBackground(snackbar, ContextCompat.getColor(view.getContext(), R.color._3C9FFC));
        setTextColor(snackbar, ContextCompat.getColor(view.getContext(), R.color.colorPrimary)).show();

    }

    private Snackbar setBackground(Snackbar snackbar, int color) {
        View view = snackbar.getView();
        view.setBackgroundColor(color);
        return snackbar;
    }

    private Snackbar setTextColor(Snackbar snackbar, int color) {
        View view = snackbar.getView();
        TextView textView = view.findViewById(R.id.snackbar_text);
        textView.setTextColor(color);
        return snackbar;
    }

}

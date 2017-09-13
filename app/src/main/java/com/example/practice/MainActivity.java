package com.example.practice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.practice.customview.CustomMainActivity;
import com.example.practice.dependencies.DependenciesMainActivity;
import com.example.practice.other.OtherMainActivity;
import com.example.practice.systemview.SystemViewMainActivity;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onViewClicked(View view) {
        Class<? extends Activity> toActivity;
        switch (view.getId()) {
            case R.id.to_system_view:
                toActivity = SystemViewMainActivity.class;
                break;
            case R.id.to_dependencies:
                toActivity = DependenciesMainActivity.class;
                break;
            case R.id.to_custom_view:
                toActivity = CustomMainActivity.class;
                break;
            case R.id.to_other:
                toActivity = OtherMainActivity.class;
                break;
            case R.id.to_setting:
                toActivity = SettingsActivity.class;
                break;
            default:
                toActivity = MainActivity.class;
                break;
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
        TextView textView = (TextView) view.findViewById(R.id.snackbar_text);
        textView.setTextColor(color);
        return snackbar;
    }


}

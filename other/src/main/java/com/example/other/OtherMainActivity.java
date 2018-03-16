package com.example.other;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.XML;
import com.example.common.tools.LogUtils;
import com.example.other.changetextsize.ChangeTextSizeActivity;
import com.example.other.databinding.DataBindingMainActivity;
import com.example.other.encode.EncodeMainActivity;
import com.example.other.fragment.FragmentSwitchActivity;
import com.example.other.loadconfig.LoadConfigMainActivity;
import com.example.other.outputlog.OutputLogMainActivity;
import com.example.other.readfile.ReadFileMainActivity;
import com.example.other.setting.SettingActivity;

import java.io.IOException;
import java.io.InputStream;


public class OtherMainActivity extends AppCompatActivity {
    private static final String TAG = "OtherMainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_main);
    }


    public void onViewClicked(View view) {
        Class<? extends Activity> toActivity = null;
        int i = view.getId();
        if (i == R.id.to_load_config) {
            toActivity = LoadConfigMainActivity.class;
        } else if (i == R.id.to_change_text_size) {
            toActivity = ChangeTextSizeActivity.class;
        } else if (i == R.id.to_read_file) {
            toActivity = ReadFileMainActivity.class;
        } else if (i == R.id.to_mvvm) {
            toActivity = DataBindingMainActivity.class;
        } else if (i == R.id.to_setting) {
            toActivity = SettingActivity.class;
        } else if (i == R.id.read_raw) {
            readRawXml();
        } else if (i == R.id.to_encode) {
            toActivity = EncodeMainActivity.class;
        } else if (i == R.id.to_output_log) {
            toActivity = OutputLogMainActivity.class;
        } else if (i == R.id.to_fragment_switch) {
            toActivity = FragmentSwitchActivity.class;
        } else {
            toActivity = OtherMainActivity.class;
        }
        if (null == toActivity) {
            return;
        }
        startActivity(new Intent(view.getContext(), toActivity));
    }

    public void readRawXml() {
        try {
            InputStream inputStream = getResources().openRawResource(R.raw.language);
            int available = inputStream.available();
            byte[] buff = new byte[available];
            int read = inputStream.read(buff);
            String outString;
            if (-1 != read) {
                outString = new String(buff, "UTF-8");
            } else {
                outString = "";
            }
            LogUtils.i(TAG, "readRawXml: " + outString);

            LogUtils.i(TAG, "readRawXml: " + XML.toJSONObject(outString).toString());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

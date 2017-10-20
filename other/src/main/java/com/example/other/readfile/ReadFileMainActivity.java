package com.example.other.readfile;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;


import com.example.other.R;
import com.example.other.databinding.ActivityReadFileMainBinding;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.Properties;

public class ReadFileMainActivity extends AppCompatActivity {
    private static final String TAG = "ReadFileMainActivity";
    private ActivityReadFileMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_read_file_main);
        binding.setPresenter(new Presenter());
//        readRaw();
//        readAssets();
        readProperties();
    }

    private void readRaw() {
        InputStream inputStream = getResources().openRawResource(R.raw.test);
        Log.i(TAG, "readRaw: " + readTxtWithLine(inputStream));
    }

    private void readAssets() {
        try {
            InputStream inputStream = getResources().getAssets().open("assets_test.txt");
            Log.i(TAG, "readAssets: " + readTxtWithLine(inputStream));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //向/Data/data目录写入文件
    private void writeDataData() {
        try {
            FileOutputStream outputStream = openFileOutput("test.txt", Context.MODE_PRIVATE);
            outputStream.write("hello word".getBytes());
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //读取/data/data/目录下的文件
    private void readDataData() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            File file = new File(getDataDir(), "");
        }


    }

    //读取properties文件
    private void readProperties() {
        InputStream inputSteam = getResources().openRawResource(R.raw.config);
        Properties properties = new Properties();
        try {
            //加载文件
            properties.load(inputSteam);
            Enumeration<Object> keys = properties.keys();
            while (keys.hasMoreElements()) {
                Object o = keys.nextElement();
                String property = properties.getProperty(o.toString());
                Log.i(TAG, "readProperties: " + o.toString() + ":" + property);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeProperties() {
        InputStream inputSteam = getResources().openRawResource(R.raw.config);
        Properties properties = new Properties();
        try {
            properties.load(inputSteam);
            properties.setProperty("age", "25");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readTxtWithLine(InputStream inputStream) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            String str;
            while ((str = bufferedReader.readLine()) != null) {
                stringBuilder.append(str);
                stringBuilder.append("\n");
            }
            inputStream.close();
            bufferedReader.close();
            return stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
            try {
                inputStream.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            return "";
        }
    }

    public class Presenter {
        public void readConfig() {
            String config = RuntimeConfig.getInstance().getStringConfig(RuntimeConfig.CONFIG_QR_CODE_PREFIX);
            Toast.makeText(ReadFileMainActivity.this, config, Toast.LENGTH_SHORT).show();
        }
    }
}

package com.example.practice;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spanned;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Main2Activity extends AppCompatActivity {


    @BindView(R.id.text_view)
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);
        readraw();
    }

    private void readraw() {
        // 从raw文件读取文件的字节流
        InputStream is = getResources().openRawResource(R.raw.test);
        try {
            byte[] buffer = readByteFromInputstream(is);
            String s = new String(buffer, 0, buffer.length, "UTF-8");
            s = s.replace("{name}", "张康");
            s = s.replace("{account}", "123456");
            Spanned result;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                result = Html.fromHtml(s,Html.FROM_HTML_MODE_LEGACY);
            } else {
                result = Html.fromHtml(s);
            }
            textView.setText(result);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private byte[] readByteFromInputstream(InputStream is) throws IOException {

        BufferedInputStream bis = new BufferedInputStream(is);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        int BYTE_SIZE = 2 * 1024;
        //创建字节数组作为缓存
        byte[] b = new byte[BYTE_SIZE];

        int c;
        //c = bis.read(b)将字节流读出后放入字节数组b中,baos.write(b, 0, c);读取多少就写入多少
        while ((c = bis.read(b)) != -1) {
            baos.write(b, 0, c);
            baos.flush();
        }
        //将字节数组流转换成字节数组
        byte[] date = baos.toByteArray();

        is.close();
        baos.close();

        return date;

    }

}

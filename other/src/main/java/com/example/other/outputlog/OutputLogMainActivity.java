package com.example.other.outputlog;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.other.R;
import com.example.other.databinding.ActivityOutputLogMainBinding;
import com.hundsun.log.LogConfigurator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OutputLogMainActivity extends AppCompatActivity {
    private static final Logger logger = LoggerFactory.getLogger("日志输出测试");
    private ActivityOutputLogMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_output_log_main);
        binding.setPresenter(new Presenter());
    }

    private void init() {

    }

    public class Presenter {
        public void startOutputLog() {
            LogConfigurator.confifure();
            logger.debug("测试内容，测试内容");
            logger.info("日志输出1", "日志输出2");
        }

        public void stopOutputLog() {

        }
    }
}

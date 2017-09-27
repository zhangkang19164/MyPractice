package com.example.practice.other.setting.language;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;

import com.example.practice.R;
import com.example.practice.databinding.ActivitySettingLanguageBinding;

public class SettingLanguageActivity extends AppCompatActivity {
    private ActivitySettingLanguageBinding binding;
    private int selectLocale = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_setting_language);
        binding.setPresenter(new Presenter());
        setUserSelectLanguage();
    }

    public void setUserSelectLanguage() {
        int userSettingLanguage = SharedPreferencesUtils.getUserSettingLanguage(this, UserConfig.SIMPLIFIED_CHINESE);
        switch (userSettingLanguage) {
            case UserConfig.SIMPLIFIED_CHINESE:
                binding.languageZhCheck.setChecked(true);
                break;
            case UserConfig.TRADITIONAL_CHINESE:
                binding.languageZhRTwCheck.setChecked(true);
                break;
            case UserConfig.ENGLISH:
                binding.languageEnglishCheck.setChecked(true);
                break;
        }
    }

    public class Presenter {
        public void onViewClick(View view) {
            if (selectLocale != -1) {
                UserConfig.settingLanguage(SettingLanguageActivity.this, selectLocale);
            }
            SharedPreferencesUtils.setUserSettingLanguage(SettingLanguageActivity.this, selectLocale);
        }

        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked) {
                if (buttonView == binding.languageEnglishCheck) {
                    selectLocale = UserConfig.ENGLISH;
                    binding.languageZhCheck.setChecked(false);
                    binding.languageZhRTwCheck.setChecked(false);
                } else if (buttonView == binding.languageZhCheck) {
                    selectLocale = UserConfig.SIMPLIFIED_CHINESE;
                    binding.languageEnglishCheck.setChecked(false);
                    binding.languageZhRTwCheck.setChecked(false);
                } else if (buttonView == binding.languageZhRTwCheck) {
                    selectLocale = UserConfig.TRADITIONAL_CHINESE;
                    binding.languageEnglishCheck.setChecked(false);
                    binding.languageZhCheck.setChecked(false);
                }
            }
        }

    }

}


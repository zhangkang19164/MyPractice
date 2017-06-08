package com.example.practice.systemview.spinner;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.practice.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SpinnerMainActivity extends AppCompatActivity {

    @BindView(R.id.spinner)
    Spinner spinner;
    @BindView(R.id.spinner_adapter)
    Spinner spinnerAdapter;
    @BindView(R.id.spinner_custom_adapter)
    Spinner spinnerCustomAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner_main);
        ButterKnife.bind(this);
//        initViews();
//        withArrayAdapter();
        withCustomAdapter();
    }

    private void initViews() {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] strings = getResources().getStringArray(R.array.sex_items);
                Toast.makeText(SpinnerMainActivity.this, "选择了 " + strings[position], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(SpinnerMainActivity.this, "你说你为什么不选择", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void withArrayAdapter() {
        String[] items = getResources().getStringArray(R.array.sex_items);
        ArrayAdapter arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, items);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAdapter.setAdapter(arrayAdapter);
        spinnerAdapter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] strings = getResources().getStringArray(R.array.sex_items);
                Toast.makeText(SpinnerMainActivity.this, "选择了 " + strings[position], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(SpinnerMainActivity.this, "你说你为什么不选择", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void withCustomAdapter() {
        String[] items = new String[]{"第一个", "第二个", "第三个", "第四个",};
        CustomAdapter customAdapter = new CustomAdapter(items);
        spinnerCustomAdapter.setAdapter(customAdapter);
        spinnerCustomAdapter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] strings = getResources().getStringArray(R.array.sex_items);
                Toast.makeText(SpinnerMainActivity.this, "选择了 " + strings[position], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(SpinnerMainActivity.this, "你说你为什么不选择", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private static class CustomAdapter extends BaseAdapter implements SpinnerAdapter {


        private String[] items;

        public CustomAdapter(String[] items) {
            this.items = items;
        }

        @Override
        public int getCount() {
            return null == items ? 0 : items.length;
        }

        @Override
        public Object getItem(int position) {
            return items[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (null == convertView) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.spinner_custom_item, parent, false);
            }
            TextView textView = (TextView) convertView.findViewById(R.id.text);
            textView.setText(items[position]);
            return convertView;
        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            return super.getDropDownView(position, convertView, parent);
        }
    }
}

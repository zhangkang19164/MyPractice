package com.example.system.dialog.system;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.example.system.R;


public class PickerActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_picker);
		initView();
	}

	private void initView() {
		final TextView txt = (TextView) findViewById(R.id.picker_txt);
		NumberPicker numberPicker = (NumberPicker) findViewById(R.id.number_picker);
		numberPicker.setMaxValue(100);
		numberPicker.setMinValue(0);
		numberPicker.setValue(50);
		numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
			@Override
			public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
				txt.setText("现在的值是: " + newVal);
			}
		});
	}
}

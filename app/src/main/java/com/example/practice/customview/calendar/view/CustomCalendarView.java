package com.example.practice.customview.calendar.view;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.common.view.common.CommonRecyclerViewAdapter;
import com.example.common.view.common.CommonViewHolder;
import com.example.practice.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * create time : 2017/08/21
 * desc        :
 */

public class CustomCalendarView extends LinearLayout {
    private TextView calendarText;
    private Calendar calendar = Calendar.getInstance();
    private CommonRecyclerViewAdapter<Date> dateAdapter;

    public CustomCalendarView(Context context) {
        this(context, null);
    }

    public CustomCalendarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomCalendarView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    private void initView() {
        setOrientation(VERTICAL);
        LayoutInflater.from(getContext()).inflate(R.layout.custom_calendar, this);
        findViewById(R.id.calendar_previous).setOnClickListener(onClickListener);
        findViewById(R.id.calendar_next).setOnClickListener(onClickListener);

        calendarText = (TextView) findViewById(R.id.calendar_text);
        RecyclerView calendarRecyclerView = (RecyclerView) findViewById(R.id.calendar_recycler_view);
        calendarRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 7));
        calendarRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        calendarRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.HORIZONTAL));
        dateAdapter = new CommonRecyclerViewAdapter<Date>(R.layout.custom_calendar_item) {

            @Override
            public void convert(CommonViewHolder holder, Date date) {
                CircleTextView textView = (CircleTextView) holder.getView(R.id.custom_calendar_item_text);
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                textView.setText(String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)));
                if (calendar.get(Calendar.DAY_OF_MONTH) == Calendar.getInstance().get(Calendar.DAY_OF_MONTH) &&
                        calendar.get(Calendar.MONTH) == Calendar.getInstance().get(Calendar.MONTH)&&
                        calendar.get(Calendar.YEAR) == Calendar.getInstance().get(Calendar.YEAR)) {

                    textView.setTextColor(Color.RED);
                    textView.setToday(true);
                } else {
                    textView.setToday(false);
                    if (calendar.get(Calendar.MONTH) == CustomCalendarView.this.calendar.get(Calendar.MONTH)) {
                        textView.setTextColor(Color.BLACK);
                    } else {
                        textView.setTextColor(Color.GRAY);
                    }
                }

            }
        };
        calendarRecyclerView.setAdapter(dateAdapter);
        renderCalendar();
    }

    private View.OnClickListener onClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.calendar_previous:
                    calendar.add(Calendar.MONTH, -1);
                    renderCalendar();
                    break;
                case R.id.calendar_next:
                    calendar.add(Calendar.MONTH, 1);
                    renderCalendar();
                    break;
                default:
                    break;
            }
        }
    };

    private void renderCalendar() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM yyyy");
        calendarText.setText(simpleDateFormat.format(calendar.getTime()));

        ArrayList<Date> arrayList = new ArrayList<>();
        Calendar calendar = (Calendar) this.calendar.clone();
        //先将日期切换到当月的第一天
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        //获取一号是周几,减一得到一号所在周之前还有几天 因为星期日是第一天,
        int prevDays = calendar.get(Calendar.DAY_OF_WEEK) - 1;

        //再将日期切换到显示页面的第一天
        calendar.add(Calendar.DAY_OF_MONTH, -prevDays);
        //先获取当月一共有多少天
        int monthDays = getCurrentMonthDay();
        //得到当前页面显示的当前月和上一个月显示的的天数和
        monthDays += prevDays;
        //得到当前页面显示的的当前月和下一个月显示的天数和
        monthDays += (7 - monthDays % 7);

        while (arrayList.size() < monthDays) {
            arrayList.add(calendar.getTime());
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        dateAdapter.setList(arrayList);
    }

    /**
     * 获取当月的 天数
     */
    public int getCurrentMonthDay() {


        calendar.set(Calendar.DATE, 1);
        calendar.roll(Calendar.DATE, -1);
        int maxDate = calendar.get(Calendar.DATE);
        return maxDate;
    }
}

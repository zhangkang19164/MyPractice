package com.example.common.tools;

import android.support.v4.util.Pools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * create time : 2017/09/13
 * desc        : 时间工具类
 */

public class TimeTools {
    private static final Pools.Pool<SimpleDateFormat> simpleDateFormatPools = new Pools.SynchronizedPool<>(10);

    /**
     * 修改日期格式
     *
     * @param sourceDate    当前格式日期
     * @param sourPattern   当前日期格式
     * @param targetPattern 目标日期格式
     * @return 转换后的格式，如果发生错误，返回 当前格式日期
     */
    public static String changeDateStyle(String sourceDate, String sourPattern, String targetPattern) {
        SimpleDateFormat simpleDateFormat = getSimpleDateFormat();
        try {
            simpleDateFormat.applyPattern(sourPattern);
            Date date = simpleDateFormat.parse(sourceDate);
            simpleDateFormat.applyPattern(targetPattern);
            String returnString = simpleDateFormat.format(date);
            simpleDateFormatPools.release(simpleDateFormat);
            return returnString;
        } catch (ParseException e) {
            return sourceDate;
        }
    }

    /**
     * 比较来源时间和当前时间的大小
     *
     * @param sourceDate        来源日期
     * @param sourceDatePattern 来源日期格式
     * @return
     */
    public static int compareToCurrentTime(String sourceDate, String sourceDatePattern) {
        try {
            SimpleDateFormat simpleDateFormat = getSimpleDateFormat();
            simpleDateFormat.applyPattern(sourceDatePattern);
            Date date = simpleDateFormat.parse(sourceDate);
            Date today = simpleDateFormat.parse(simpleDateFormat.format(new Date()));
            simpleDateFormatPools.release(simpleDateFormat);
            return date.compareTo(today);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return -1;
    }

    //转换时间
    public static String getDateFormat(String sourDate) {
        SimpleDateFormat simpleDateFormat = getSimpleDateFormat();
        Date date;
        try {
            simpleDateFormat.applyPattern("yyyy-MM-dd HH:mm:ss");
            date = simpleDateFormat.parse(sourDate);
            simpleDateFormat.applyPattern("yyyy.MM.dd");
            String returnString = simpleDateFormat.format(date);
            simpleDateFormatPools.release(simpleDateFormat);
            return returnString;
        } catch (Exception e) {
            return "";
        }
    }

    //根据日期获取周几 日期格式 yyyy.MM.dd
    public static String getWeekByDateStr(String sourDate) {
        int year = Integer.parseInt(sourDate.substring(0, 4));
        int month = Integer.parseInt(sourDate.substring(5, 7));
        int day = Integer.parseInt(sourDate.substring(8, 10));

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        String week = "";
        int weekint = calendar.get(Calendar.DAY_OF_WEEK);
        switch (weekint) {
            case Calendar.SUNDAY:
                week = "星期天";
                break;
            case Calendar.MONDAY:
                week = "周一";
                break;
            case Calendar.TUESDAY:
                week = "周二";
                break;
            case Calendar.WEDNESDAY:
                week = "周三";
                break;
            case Calendar.THURSDAY:
                week = "周四";
                break;
            case Calendar.FRIDAY:
                week = "周五";
                break;
            case Calendar.SATURDAY:
                week = "周六";
                break;
        }
        return week;
    }

    public static SimpleDateFormat getSimpleDateFormat() {
        SimpleDateFormat simpleDateFormat = simpleDateFormatPools.acquire();
        if (null == simpleDateFormat) {
            simpleDateFormat = new SimpleDateFormat("", Locale.CHINA);
        }
        return simpleDateFormat;
    }
}

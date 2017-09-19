package com.example.common.tools;

import android.text.TextUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * create time : 2017/09/13
 * desc        : 数字工具类
 */

public class NumberTools {
    private static final DecimalFormat decimalFormat0 = new DecimalFormat("#");
    private static final DecimalFormat decimalFormat1 = new DecimalFormat("#.0");
    private static final DecimalFormat decimalFormat2 = new DecimalFormat("#.00");
    private static final DecimalFormat decimalFormat3 = new DecimalFormat("#.000");

    //将小数 四舍五入 保留 digit位小数 以字符串返回
    public static String translatedNum(double d, int digit) {
        try {
            DecimalFormat decimalFormat = switchDecimalFormat(digit);

//			String endStr = ".";
//			for (int i = 0; i < digit; i++) {
//				endStr += "0";
//			}
//			if (s.endsWith(endStr)) {
//				s = s.substring(0, s.length() - 1 - digit);
//			}
            return decimalFormat.format(d);
        } catch (Exception e) {
            return "";
        }

    }

    //将数字转为万结尾的
    public static String translatedWan(double num) {
        double numS = num / 10000;
        if (numS >= 1) {
            String s = translatedNum(numS, 2);
            if (TextUtils.isEmpty(s)) {
                return "";
            } else {
                return s + "万";
            }
        } else {
            return translatedNum(num, 2);
        }

    }

    //将数字转为亿结尾的
    public static String translatedYi(double num) {
        num = num / 100000000;
        String s = translatedNum(num, 2);
        if (TextUtils.isEmpty(s)) {
            return "";
        } else {
            return s + "亿";
        }
    }

    //将小数 四舍五入 保留 digit位小数 以数字返回
    public static double translatedNumD(double d, int digit) {
        try {
            BigDecimal bigDecimal = new BigDecimal(d);
            bigDecimal = bigDecimal.setScale(digit, BigDecimal.ROUND_HALF_UP);
            return bigDecimal.doubleValue();
        } catch (Exception e) {
            return 0;
        }

    }

    //将数字转为万或亿结尾的
    public static String translatedWanOrYi(double num) {
        double numS = num / 100000000;
        if (numS >= 1) {
            return translatedYi(num);
        } else {
            return translatedWan(num);
        }
    }

    /**
     * 判断是否为数值
     * @param string
     * @return
     */
    public static boolean isNumber(String string) {
        try {
            double number = Double.parseDouble(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static DecimalFormat switchDecimalFormat(int digit) {
        switch (digit) {
            case 0:
                return decimalFormat0;
            case 1:
                return decimalFormat1;
            case 2:
                return decimalFormat2;
            case 3:
                return decimalFormat3;
            default:
                return decimalFormat2;
        }
    }
}

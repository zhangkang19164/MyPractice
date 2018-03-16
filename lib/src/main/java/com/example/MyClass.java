package com.example;

import java.util.Arrays;

public class MyClass {

    public static void main(String args[]) {
        test();
    }


    private static void test() {
//        String[] strings1 = {"十", "九", "八", "七", "六", "五", "四", "三", "二", "一",};
        String[] strings1 = { "一",};
        String[] strings2 = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10",};
        String[] strings3 = new String[10];
        int size = strings1.length - 1;
        for (int i = 0; i < 10; i++) {
            String level2BaseBean = strings2[i];
            int index = size - i;
            if (index < 0) {
                strings3[i] = level2BaseBean + " = " + "--";
            } else {
                strings3[i] = level2BaseBean + " = " + strings1[index];
            }
        }
        System.out.print(Arrays.toString(strings3));
    }

    private static String prodpreRatioFaultTolerant(String prodpreRatio) {
        int indexOf = prodpreRatio.indexOf(".");
        String substring1 = prodpreRatio.substring(0, indexOf + 2);
        String substring2 = prodpreRatio.substring(indexOf + 2);
        String replace = substring1.replace(".", "");
        return replace + "." + substring2 + "%";
    }

}

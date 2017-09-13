package com.example.common.tools;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * create time : 2017/08/30
 * desc        : View使用的工具类
 */

public class ViewTools {
    private static int screenWidth = -1;
    private static int screenHeight = -1;

    /**
     * 将dp转为px
     */
    public static int dp2Px(float dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    /**
     * 将sp转为px
     */
    public static int sp2Px(float sp) {
        return (int) (sp * Resources.getSystem().getDisplayMetrics().scaledDensity);
    }

    /**
     * 为 画笔设置字体的大小，以sp为单位
     *
     * @param paint    画笔
     * @param textSize 字体大小，单位sp
     */
    public static void paintSetTextSizeBySp(Paint paint, float textSize) {
        float textSizeF = sp2Px(textSize);
        paint.setTextSize(textSizeF);
    }

    /**
     * 获取屏幕的宽度
     *
     * @return 屏幕的宽度
     */
    public static int getScreenWidth() {
        if (screenWidth == -1) {
            screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
        }
        return screenWidth;
    }

    /**
     * 获取屏幕的高度
     *
     * @return 屏幕的高度
     */
    public static int getScreenHeight() {
        if (screenHeight == -1) {
            screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
        }
        return screenHeight;
    }

    /**
     * 获取状态栏的高度
     *
     * @return 高度
     */
    public static int getStatusBarHeight(Activity activity) {
        Rect rect = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        return rect.top;
    }

    /**
     * 获取app的画面高度 屏幕高度除掉状态栏的高度
     *
     * @return 高度
     */
    public static int getAppScreenHeight(Activity activity) {
        Rect rect = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        return rect.height();
    }


}

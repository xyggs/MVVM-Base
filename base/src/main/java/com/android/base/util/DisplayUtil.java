package com.android.base.util;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author gyw
 * @version 1.0
 * @time: 2015-6-5 上午11:44:21
 * @fun: 屏幕工具类
 */
public class DisplayUtil {

    public static final String TAG = "DisplayUtil";

    /**
     * get screen height of this cellphone
     *
     * @param context
     * @return
     */
    public static int getMobileHeight(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
        int height = dm.heightPixels; // 得到高度
        return height;
    }

    /**
     * get screen width of this cellphone
     *
     * @param context
     * @return
     */
    public static int getMobileWidth(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels; // 得到宽度
        return width;

    }

    /**
     * 根据手机的分辨率dp 转成px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率px(像素) 转成dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }


    public static float sp2px(Context context, float sp) {
        final float scale = context.getResources().getDisplayMetrics().scaledDensity;
        return sp * scale;
    }

    /**
     * 得到屏幕宽度
     *
     * @param context
     * @return
     */
    public static int getWindowWidth(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        return width;
    }

    /**
     * 得到屏幕高度
     *
     * @param context
     * @return
     */
    public static int getWindowHeight(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int height = wm.getDefaultDisplay().getHeight();
        return height;
    }


    /**
     * 返回屏幕比例
     *
     * @param activity
     * @return
     */
    public static int SCALE_16_9 = 0, SCALE_18_9 = 1;

    public static int getDimenScale(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);

        float density = dm.density;

        int screenWidth = (int) (dm.widthPixels * density + 0.5f);
        int screenHeight = (int) (dm.heightPixels * density + 0.5f);
        if (divide(screenHeight, screenWidth, 4).doubleValue() <= 1.8)
            return SCALE_16_9;
        else
            return SCALE_18_9;
    }

    /**
     * obj1 / obj2
     * @param obj1
     * @param obj2
     * @param  limt 小数位数
     * @return 相除保留4位 4舍5入
     */
    public static BigDecimal divide(Object obj1, Object obj2, int limt) {
        if (String.valueOf(obj1).isEmpty()) {
            obj1 = "0";
        }
        if (String.valueOf(obj2).isEmpty()) {
            obj2 = "0";
        }

        BigDecimal bd1 = null;
        BigDecimal bd2 = null;
        if(obj1 instanceof BigDecimal){
            bd1 = (BigDecimal)obj1;
        }else{
            bd1 = new BigDecimal(obj1.toString());
        }
        if(obj2 instanceof BigDecimal){
            bd2 = (BigDecimal)obj2;
        }else{
            bd2 = new BigDecimal(obj2.toString());
        }
        return bd1.divide(bd2, limt, RoundingMode.HALF_EVEN);
    }

}

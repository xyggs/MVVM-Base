package com.android.base.util;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.ColorInt;

import com.android.base.Constant;
import com.jaeger.library.StatusBarUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author LOPER7
 * @date 2018/3/14 11:34
 * @Description: 状态栏帮助类
 */

public class StatusBarHelper {

    @ColorInt
    public static int Translucent = -101;

    public static void setStatusBar(Activity activity, @ColorInt int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (color == Translucent) {
                StatusBarUtil.setTranslucentForImageView(activity, 0, null);
                setDarkStatusIcon(activity, false);
            } else {
                StatusBarUtil.setColor(activity, color, 0);
                setLightStatusBar(activity,true,color);
            }

        } else {
            if (color == Translucent) {
                StatusBarUtil.setTranslucentForImageView(activity, 55, null);
            } else {
                StatusBarUtil.setColor(activity, color, 55);
            }

        }


    }

    /**
     *  修改状态栏文字颜色，这里小米，魅族区别对待。
     */
    public static void setLightStatusBar(final Activity activity, final boolean dark,int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            switch (RomUtils.getLightStatusBarAvailableRomType()) {
                case RomUtils.AvailableRomType.MIUI:
                    MIUISetStatusBarLightMode(activity, dark);
                    break;
                case RomUtils.AvailableRomType.FLYME:
                    setFlymeLightStatusBar(activity, dark);
                    break;
                case RomUtils.AvailableRomType.ANDROID_NATIVE:
                    setDarkStatusIcon(activity,  !isDarkColor(color));
                    break;

            }
        }
    }

    public static boolean MIUISetStatusBarLightMode(Activity activity, boolean dark) {
        boolean result = false;
        Window window = activity.getWindow();
        if (window != null) {
            Class clazz = window.getClass();
            try {
                int darkModeFlag = 0;
                Class layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
                Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
                darkModeFlag = field.getInt(layoutParams);
                Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
                if (dark) {
                    extraFlagField.invoke(window, darkModeFlag, darkModeFlag);//状态栏透明且黑色字体
                } else {
                    extraFlagField.invoke(window, 0, darkModeFlag);//清除黑色字体
                }
                result = true;
            } catch (Exception e) {

            }
        }
        return result;
    }

    private static boolean setFlymeLightStatusBar(Activity activity, boolean dark) {
        boolean result = false;
        if (activity != null) {
            try {
                WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
                Field darkFlag = WindowManager.LayoutParams.class
                        .getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
                Field meizuFlags = WindowManager.LayoutParams.class
                        .getDeclaredField("meizuFlags");
                darkFlag.setAccessible(true);
                meizuFlags.setAccessible(true);
                int bit = darkFlag.getInt(null);
                int value = meizuFlags.getInt(lp);
                if (dark) {
                    value |= bit;
                } else {
                    value &= ~bit;
                }
                meizuFlags.setInt(lp, value);
                activity.getWindow().setAttributes(lp);
                result = true;
            } catch (Exception e) {
            }
        }
        return result;
    }


    /**
     * 是否使用深色状态栏
     *
     * @param bDark
     */
    public static void setDarkStatusIcon(Activity activity, boolean bDark) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View decorView = activity.getWindow().getDecorView();
            if (decorView != null) {
                int vis = decorView.getSystemUiVisibility();
                if (bDark) {
                    vis |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
                } else {
                    vis &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
                }
                decorView.setSystemUiVisibility(vis);
            }
        }
    }


    public static void setStatusBarViewHeight(Context context, View view) {
        setStatusBarViewHeight(context,view,getStatusBarHeight(context));
    }

    public static void setStatusBarViewHeight(Context context, View view, int height) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = height;
        view.setLayoutParams(layoutParams);
    }


    /**
     * 获取状态栏和标题栏高度
     *
     * @param context context
     * @return 状态栏和标题栏高度
     */
    public static int getStatusTitleBarHeight(Context context) {
        return getStatusBarHeight(context) + DisplayUtil.dip2px(context, Constant.TITLE_HEIGHT);
    }

    /**
     * 获取状态栏高度
     *
     * @param context context
     * @return 状态栏高度
     */
    public static int getStatusBarHeight(Context context) {
        // 获得状态栏高度
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        return context.getResources().getDimensionPixelSize(resourceId);
    }

    /**
     * 是否是深色
     *
     * @param color
     * @return
     */
    public static boolean isDarkColor(int color) {
        if (color == StatusBarHelper.Translucent)
            return true;

        int red = (color & 0xff0000) >> 16;
        int green = (color & 0x00ff00) >> 8;
        int blue = (color & 0x0000ff);

        int grayLevel = (int) (red * 0.299 + green * 0.587 + blue * 0.114);

        if (grayLevel >= 192) {
            return false;
        }

        return true;
    }


}

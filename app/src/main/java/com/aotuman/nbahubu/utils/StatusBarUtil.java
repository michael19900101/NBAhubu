package com.aotuman.nbahubu.utils;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.content.res.Resources;
import android.os.Build;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.aotuman.nbahubu.AppHelper;
import com.aotuman.nbahubu.utils.DensityUtilKt;
import com.aotuman.nbahubu.utils.SystemBarTintManager;

import java.lang.reflect.Field;
import java.lang.reflect.Method;


public class StatusBarUtil {

    /**
     * 设置activity 沉浸模式 只支持4.4 以及小米 魅族机型
     * 需要在activity的根布局添加
     * android:fitsSystemWindows="true"
     * android:clipToPadding="true"
     *
     * @param activity
     */
    public static void setImmersionMode(Activity activity) {
        setImmersionMode(activity, false);
    }

    public static void setImmersionMode(Activity activity, boolean darkTheme) {
        int result = StatusBarUtil.StatusBarLightMode(activity, true);
        if (result == 1 || result == 2) {
            ViewGroup parentLayout = (ViewGroup) activity.findViewById(android.R.id.content);
            if (null != parentLayout) {

                SystemBarTintManager tintManager = new SystemBarTintManager(activity);
                ActionBar actionBar = activity.getActionBar();
                if (actionBar == null) {
                    ViewGroup contentLayout = (ViewGroup) parentLayout.getChildAt(0);
                    if (null != contentLayout) {
                        contentLayout.setFitsSystemWindows(true);
                    }
                } else {
                    parentLayout.setFitsSystemWindows(false);
                    parentLayout.setPadding(0, DensityUtilKt.dp2px(42) + tintManager.getConfig().getStatusBarHeight(), tintManager.getConfig().getPixelInsetRight(), tintManager.getConfig().getPixelInsetBottom());
                }
                parentLayout.setClipToPadding(true);
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                SystemBarTintManager tintManager = null;
                setTranslucentStatus(activity, true);
                tintManager = new SystemBarTintManager(activity);
                tintManager.setStatusBarTintEnabled(true);
                tintManager.setStatusBarTintResource(darkTheme ? android.R.color.black : android.R.color.white);
                StatusBarLightMode(activity, !darkTheme); // 黑色主题，字体用白色
            }
        }
    }

    public static void revertImmersionModeNone(Activity activity) {
        int result = StatusBarUtil.StatusBarLightMode(activity, false);
        if (result == 1 || result == 2) {
            ViewGroup parentLayout = (ViewGroup) activity.findViewById(android.R.id.content);
            if (null != parentLayout) {
                SystemBarTintManager tintManager = new SystemBarTintManager(activity);
                ActionBar actionBar = activity.getActionBar();
                if (actionBar == null) {
                    ViewGroup contentLayout = (ViewGroup) parentLayout.getChildAt(0);
                    if (null != contentLayout) {
                        contentLayout.setFitsSystemWindows(true);
                    }
                } else {
                    parentLayout.setFitsSystemWindows(false);
                    parentLayout.setPadding(0, DensityUtilKt.dp2px(42) + tintManager.getConfig().getStatusBarHeight(), tintManager.getConfig().getPixelInsetRight(), tintManager.getConfig().getPixelInsetBottom());
                }
                parentLayout.setClipToPadding(true);
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                setTranslucentStatus(activity, false);
                SystemBarTintManager tintManager = new SystemBarTintManager(activity);
                tintManager.clearOldStatusBarTint(activity);
            }
        }
    }

    @TargetApi(19)
    public static void setTranslucentStatus(Activity activity, boolean on) {
        Window win = activity.getWindow();
        if (on) {
            win.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        } else {
            win.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    /**
     * 设置状态栏黑色字体图标，
     * 适配4.4以上版本MIUIV、Flyme和6.0以上版本其他Android
     *
     * @param activity
     * @param dark     是否把状态栏字体及图标颜色设置为深色
     * @return 1:MIUUI 2:Flyme 3:android6.0
     */
    public static int StatusBarLightMode(Activity activity, boolean dark) {
        int result = 0;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (MIUISetStatusBarLightMode(activity.getWindow(), dark)) {
                result = 1;
            }
//            else if (FlymeSetStatusBarLightMode(activity.getWindow(), dark)) {
//                result = 2;
//            }
            /*else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                result = 3;
            }*/
        }
        return result;
    }

    /**
     * 设置状态栏图标为深色和魅族特定的文字风格
     * 可以用来判断是否为Flyme用户
     *
     * @param window 需要设置的窗口
     * @param dark   是否把状态栏字体及图标颜色设置为深色
     * @return boolean 成功执行返回true
     */
    public static boolean FlymeSetStatusBarLightMode(Window window, boolean dark) {
        boolean result = false;
        if (window != null) {
            try {
                WindowManager.LayoutParams lp = window.getAttributes();
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
                window.setAttributes(lp);
                result = true;
            } catch (Exception e) {

            }
        }
        return result;
    }

    /**
     * 设置状态栏字体图标为深色，需要MIUIV6以上
     *
     * @param window 需要设置的窗口
     * @param dark   是否把状态栏字体及图标颜色设置为深色
     * @return boolean 成功执行返回true
     */
    public static boolean MIUISetStatusBarLightMode(Window window, boolean dark) {
        boolean result = false;
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

    public static int getStatusBarHeight() {
        Resources res = AppHelper.mContext.getResources();
        int resourceId = res.getIdentifier("status_bar_height", "dimen", "android");
        return (resourceId > 0 ? res.getDimensionPixelSize(resourceId) : -1);
    }
}
package com.aotuman.nbahubu.utils;

import android.content.res.Configuration;
import android.text.TextUtils;

import androidx.appcompat.app.AppCompatDelegate;

import com.aotuman.nbahubu.AppHelper;
import com.aotuman.nbahubu.ext.ContextExtKt;


public class DarkModeHelper {

    private static final String PNG_SUFFIX = ".png";
    private static final String JPG_SUFFIX = ".jpg";
    private static final String NORMAL_MODE_SUFFIX = "_normal";
    private static final String DARK_MODE_SUFFIX = "_dark";

    private static final long SWITCHING_DURATION = 10000;
    private static long mLastSwitch = 0;

    /**
     * 系统的暗黑模式 + 手动切换的暗黑模式
     * @return
     */
    public static boolean isDarkModeOn() {
        switch (AppCompatDelegate.getDefaultNightMode()){
            case AppCompatDelegate.MODE_NIGHT_NO:
                return false;
            case AppCompatDelegate.MODE_NIGHT_YES:
                return true;
        }
        return (AppHelper.mContext.getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK)
                == Configuration.UI_MODE_NIGHT_YES;
    }

    public static boolean isSwitchingMode() {
        return System.currentTimeMillis() - mLastSwitch < SWITCHING_DURATION;
    }

    public static void onSwitchingMode() {
        mLastSwitch = System.currentTimeMillis();
    }

    /**
     * 获取模式相关的 url 链接，支持 png， jpg 后缀
     * 若当前为普通模式，则在链接最后拼接 _normal
     * 若当前为暗黑模式，则在链接最后拼接 _dark
     *
     * @param url 链接
     * @return 模式相关链接
     */
    public static String getModeUrl(String url) {
        return getDarkModeUrl(url, isDarkModeOn());
    }

    public static String getDarkModeUrl(String url, boolean darkMode) {
        if (TextUtils.isEmpty(url)) {
            return url;
        }
        String suffix;
        if (url.endsWith(PNG_SUFFIX)) {
            suffix = PNG_SUFFIX;
        } else if (url.endsWith(JPG_SUFFIX)) {
            suffix = JPG_SUFFIX;
        } else {
            return url;
        }
        url = url.substring(0, url.length() - suffix.length());
        String modeSuffix = darkMode ? DARK_MODE_SUFFIX : NORMAL_MODE_SUFFIX;
        return url + modeSuffix + suffix;
    }

}

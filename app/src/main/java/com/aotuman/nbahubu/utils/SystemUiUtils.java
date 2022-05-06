package com.aotuman.nbahubu.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.aotuman.nbahubu.AppHelper;

/**
 * 系统UI相关帮助类，禁止依赖其他模块
 *
 */
public class SystemUiUtils {

    private static int sDisplayWidth = 0;
    private static int sDisplayHeight = 0;


    private static void getDisplay() {
        if (sDisplayWidth <= 0 || sDisplayHeight <= 0) {
            WindowManager wm = (WindowManager) AppHelper.mContext.getSystemService(Context.WINDOW_SERVICE);
            if (wm == null) {
                return;
            }
            DisplayMetrics dm = new DisplayMetrics();
            if (wm.getDefaultDisplay() == null) {
                return;
            }
            wm.getDefaultDisplay().getMetrics(dm);

            sDisplayWidth = dm.widthPixels;
            sDisplayHeight = dm.heightPixels;
        }

    }

    public static int getDisplayWidth() {
        getDisplay();
        return sDisplayWidth < sDisplayHeight ? sDisplayWidth : sDisplayHeight;
    }

    public static int getDisplayHeight() {
        getDisplay();
        return sDisplayWidth < sDisplayHeight ? sDisplayHeight : sDisplayWidth;
    }

}

package com.aotuman.nbahubu.ui.base

import android.graphics.Color
import android.os.Build
import android.os.Build.VERSION_CODES.LOLLIPOP
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.aotuman.nbahubu.AppHelper
import com.aotuman.nbahubu.R
import com.aotuman.nbahubu.utils.DarkModeHelper
import com.aotuman.nbahubu.utils.StatusBarUtil

open class BaseActivity : AppCompatActivity(){

    private var mHideSystemUI = false
    protected var mHasStateSaved = false

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus && mHideSystemUI) {
            hideSystemUI()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
         //底部导航栏（虚拟按键）适应暗黑模式
        if (Build.VERSION.SDK_INT >= LOLLIPOP) {
            getWindow().setNavigationBarColor(getResources().getColor(R.color.page_bg_white_color))
        }
    }

    override fun onStart() {
        super.onStart()
        tryInitialImmersion()
    }

    private fun tryInitialImmersion() {
        if (useImmersionMode()) {
//            val dark = useDarkStatusBarMode() || DarkModeHelper.isDarkModeOn()
    // 先默认黑暗模式
            val dark = true
            val result = StatusBarUtil.StatusBarLightMode(this, dark)
            if (result == 1) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    // 小米7.0以上
                    updateStatusBarColorForM(dark)
                } else {
                    updateStatusBarColorForM(dark)
                    StatusBarUtil.setImmersionMode(this, dark)
                }
            } else if (result == 2) {
                // 魅族
                StatusBarUtil.setImmersionMode(this, dark)
            } else {
                updateStatusBarColorForM(dark)
            }
        }
    }

    private fun updateStatusBarColorForM(isDark: Boolean) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val window = window
            //取消设置透明状态栏,使 ContentView 内容不再覆盖状态栏
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            //状态栏颜色，如果使用useDarkStatusBarMode()要求了黑色状态栏使用黑色，否则跟随暗黑/白天模式时的状态栏颜色
            window.statusBarColor = if(isDark){AppHelper.mContext.getColor(R.color.page_bg_white_color_night)}
            else {AppHelper.mContext.getColor(R.color.page_bg_white_color_day)}
            var ui = window.decorView.systemUiVisibility
            ui = if (isDark) {
                ui and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
            } else {
                ui or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            }
            window.decorView.systemUiVisibility = ui
        }
    }

    /**
     * 默认设置为跟随深浅主题状态栏 若需要使用透明沉浸模式 重写返回false
     *
     * @return
     */
    open fun useImmersionMode(): Boolean {
        return true
    }

    /**
     * 固定使用暗黑模式状态栏需要重写返回true
     */
    open fun useDarkStatusBarMode(): Boolean {
        return false
    }

    fun setTranslucentStatus(paddingTop:Boolean=true, setFitsSystemWindows:Boolean=false) {
        try {
            val window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.TRANSPARENT

            var ui = window.decorView.systemUiVisibility
            ui = if (DarkModeHelper.isDarkModeOn()) {
                ui and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
            } else {
                ui or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            }
            val option = ui or (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
            window.decorView.systemUiVisibility = option

            val view = findViewById<ViewGroup>(android.R.id.content).getChildAt(0) as ViewGroup
            if(paddingTop){
                view.clipToPadding = true
            }
            if(setFitsSystemWindows){
                view.fitsSystemWindows = true
            }
        } catch (e: Exception) {
            Log.e("BaseActivity", "setTranslucentStatus failed, ", e)
        }
    }


    fun hideSystemUI() {
        try {
            Log.i("BaseActivity", "hideSystemUI")
            // Enables regular immersive mode.
            // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
            // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    // Set the content to appear under the system bars so that the
                    // content doesn't resize when the system bars hide and show.
                    or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    // Hide the nav bar and status bar
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_FULLSCREEN)
            mHideSystemUI = true
        } catch (e: Exception) {
            Log.e("BaseActivity", "hideSystemUI error ", e)
        }
    }

    // Shows the system bars by removing all the flags
    // except for the ones that make the content appear under the system bars.
    fun showSystemUI() {
        try {
            Log.i("BaseActivity", "showSystemUI")
            setTranslucentStatus()
            mHideSystemUI = false
        } catch (e: java.lang.Exception) {
            Log.e("BaseActivity", "showSystemUI error ", e)
        }


    }

    override fun onResume() {
        super.onResume()
        mHasStateSaved = false
    }

    override fun onSaveInstanceState(outState: Bundle) {
        mHasStateSaved = true
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(
        savedInstanceState: Bundle?,
        persistentState: PersistableBundle?
    ) {
        mHasStateSaved = false
        super.onRestoreInstanceState(savedInstanceState,persistentState)
    }

    override fun onBackPressed() {
        if (!mHasStateSaved) {
            super.onBackPressed()
        } else {
            finish()
        }
    }

}
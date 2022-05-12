package com.aotuman.nbahubu.utils

import android.graphics.Typeface
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.android.material.bottomnavigation.BottomNavigationView

/**
 * 修改BottomNavigationView字体
 * navView.changeNavTypeface(
 * Typeface.createFromAsset(
 * assets,
 * "fonts/IRANSansMobile.ttf"
 * )
 * )
 */
fun BottomNavigationView.changeNavTypeface(typeface: Typeface) {
    val view: View = this
    checker(view, typeface)

}
private fun checker(view: View, typeface: Typeface) {
    if (view is ViewGroup) {
        for (i in 0 until view.childCount) {
            val child = view.getChildAt(i)
            checker(child, typeface)
        }
    } else if (view is TextView) {
        view.typeface = typeface
    }
}
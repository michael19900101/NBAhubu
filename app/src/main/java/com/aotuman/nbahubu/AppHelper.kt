package com.aotuman.nbahubu

import android.content.Context

/**
 * <pre>
 *     author: aotuman
 *     date  : 2021/05/11
 *     desc  :
 * </pre>
 */
object AppHelper {
    lateinit var mContext: Context

    fun init(context: Context) {
        this.mContext = context
    }
}
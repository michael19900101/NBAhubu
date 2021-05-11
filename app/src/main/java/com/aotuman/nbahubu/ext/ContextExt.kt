package com.aotuman.nbahubu.ext

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

/**
 * <pre>
 *     author: aotuman
 *     date  : 2021/05/11
 *     desc  :
 * </pre>
 */

@Suppress("DEPRECATION")
@SuppressLint("MissingPermission")
fun Context.isConnectedNetwork(): Boolean = run {
    val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
    activeNetwork?.isConnectedOrConnecting == true
}

inline fun <reified T> String.getEmptyOrDefault(default: () -> T): T {
    return if (isNullOrEmpty() || this == "null") {
        default()
    } else {
        this as T
    }
}
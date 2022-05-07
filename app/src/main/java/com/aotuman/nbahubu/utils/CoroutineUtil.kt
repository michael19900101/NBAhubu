package com.aotuman.nbahubu.utils

import android.content.Context
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

fun Context?.lifecycleScopeLaunch(
    context: CoroutineContext,
    block: suspend CoroutineScope.() -> Unit
): Job {
    val lifecycle = (this as? ComponentActivity)?.lifecycle
    val lifecycleScope = (this as? ComponentActivity)?.lifecycleScope
    return lifecycleScope?.launchWhenStarted {
        try {
            withContext(context, block)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            if (lifecycle?.currentState == Lifecycle.State.DESTROYED) {
                context.cancel()
                Log.i("CoroutineUtil", "页面关闭 [${Thread.currentThread().name}]")
            }
        }
    }?: CoroutineScope(context).launch {
        block
    }
}

fun Context?.isDestroy(): Boolean {
    val lifecycle = (this as? ComponentActivity)?.lifecycle
    if (lifecycle == null) {
        return true
    } else {
        if (lifecycle.currentState == Lifecycle.State.DESTROYED) {
            return true
        }
        return false
    }
}
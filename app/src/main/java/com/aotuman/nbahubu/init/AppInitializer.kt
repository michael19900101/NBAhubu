package com.aotuman.nbahubu.init

import android.content.Context
import androidx.startup.Initializer
import timber.log.Timber
import timber.log.Timber.DebugTree

/**
 * <pre>
 *     author: aotuman
 *     date  : 2020/06/02
 *     desc  :
 * </pre>
 */
class AppInitializer : Initializer<Unit> {

    override fun create(context: Context) {
        Timber.plant(DebugTree())
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> = mutableListOf()
}
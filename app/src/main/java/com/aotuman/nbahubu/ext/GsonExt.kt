package com.aotuman.nbahubu.ext

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * <pre>
 *     author: aotuman
 *     date  : 2021/05/11
 *     desc  :
 * </pre>
 */

fun <T> Gson.typedToJson(src: T): String = toJson(src)

inline fun <reified T : Any> Gson.fromJson(json: String): T =
    fromJson(json, object : TypeToken<T>() {}.type)

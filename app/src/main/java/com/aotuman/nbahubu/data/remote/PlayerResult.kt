package com.aotuman.nbahubu.data.remote

/**
 * <pre>
 *     author: aotuman
 *     date  : 2021/05/11
 *     desc  : 处理成功或者失败的情况
 * </pre>
 */

sealed class PlayerResult<out T> {
    data class Success<out T>(val value: T) : PlayerResult<T>()

    data class Failure(val throwable: Throwable?) : PlayerResult<Nothing>()
}

inline fun <reified T> PlayerResult<T>.doSuccess(success: (T) -> Unit) {
    if (this is PlayerResult.Success) {
        success(value)
    }
}

inline fun <reified T> PlayerResult<T>.doFailure(failure: (Throwable?) -> Unit) {
    if (this is PlayerResult.Failure) {
        failure(throwable)
    }
}

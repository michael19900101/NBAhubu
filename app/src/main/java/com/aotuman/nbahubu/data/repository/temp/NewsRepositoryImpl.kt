package com.aotuman.nbahubu.data.repository.temp

import android.util.Log
import com.aotuman.nbahubu.data.remote.temp.NewsServiceTemp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

/**
 * <pre>
 *     author: aotuman
 *     date  : 2021/05/31
 *     desc  :
 * </pre>
 */

class NewsRepositoryImplTemp(
    val api: NewsServiceTemp
) : NewsRepositoryTemp {

    override fun fetchNews(): Flow<Int> {
        return flow{
            val response = api.fetchNews()
            Log.e("jbjb","respnes:${response}")
            // 发射转换后的数据
            emit(2)
        }.flowOn(Dispatchers.IO)
    }
}
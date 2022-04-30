package com.aotuman.nbahubu.data.repository.headline

import android.util.Log
import com.aotuman.nbahubu.data.remote.headline.HeadLineService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

/**
 * <pre>
 *     author: aotuman
 *     date  : 2022/04/30
 *     desc  :
 * </pre>
 */
class HeadLineRepositoryImpl (
    val api: HeadLineService
) : HeadLineRepository {

    override fun fetchNews(): Flow<Int> {
        return flow{
            val response = api.fetchNews()
            Log.e("jbjb","respnes:${response}")
            // 发射转换后的数据
            emit(2)
        }.flowOn(Dispatchers.IO)
    }
}
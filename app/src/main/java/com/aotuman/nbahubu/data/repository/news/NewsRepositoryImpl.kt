package com.aotuman.nbahubu.data.repository.news

import com.aotuman.nbahubu.data.entity.NewsID
import com.aotuman.nbahubu.data.local.AppDataBase
import com.aotuman.nbahubu.data.remote.news.NewsService
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

class NewsRepositoryImpl(
    val api: NewsService,
    val db: AppDataBase
) : NewsRepository {

    override fun fetchNewsID(): Flow<List<NewsID>> {
        return flow {
            val response = api.fetchNewsID()
            var newsIDs: List<NewsID> = mutableListOf();
            response.data?.let {
                newsIDs = it
            }
            // 发射转换后的数据
            emit(newsIDs)
        }.flowOn(Dispatchers.IO)
    }

    companion object {
        private val TAG = "NewsRepositoryImpl"
    }

}
package com.aotuman.nbahubu.data.repository.news

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.aotuman.nbahubu.data.entity.news.NewsEntity
import com.aotuman.nbahubu.data.entity.news.NewsID
import com.aotuman.nbahubu.data.local.AppDataBase
import com.aotuman.nbahubu.data.mapper.Mapper
import com.aotuman.nbahubu.data.remote.news.NewsService
import com.aotuman.nbahubu.model.news.NewsItemModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

/**
 * <pre>
 *     author: aotuman
 *     date  : 2021/05/31
 *     desc  :
 * </pre>
 */

class NewsRepositoryImpl(
    val api: NewsService,
    val db: AppDataBase,
    val pageConfig: PagingConfig,
    val mapper2ItemMolde: Mapper<NewsEntity, NewsItemModel>
) : NewsRepository {

    override fun fetchNewsID(): Flow<List<NewsID>> {
        return flow {
            try {
                val response = api.fetchNewsID()
                var newsIDs: List<NewsID> = mutableListOf()
                response.data?.let {
                    newsIDs = it
                }
                // 发射转换后的数据
                emit(newsIDs)
            } catch (e:Exception) {
                e.printStackTrace()
            }
        }.flowOn(Dispatchers.IO)
    }

    override fun fetchNews(): Flow<PagingData<NewsItemModel>> {
        return Pager(
            config = pageConfig,
            remoteMediator = NewsRemoteMediator(api, db)
        ) {
            db.newsDao().getNews()
        }.flow.map{ pagingData ->
            pagingData.map { mapper2ItemMolde.map(it) }
        }
    }

    companion object {
        private val TAG = "NewsRepositoryImpl"
    }

}
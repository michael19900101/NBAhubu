package com.aotuman.nbahubu.data.repository.news

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.aotuman.nbahubu.AppHelper
import com.aotuman.nbahubu.data.entity.news.NewsEntity
import com.aotuman.nbahubu.data.entity.news.NewsIDEntity
import com.aotuman.nbahubu.data.local.AppDataBase
import com.aotuman.nbahubu.data.remote.news.NewsService
import com.aotuman.nbahubu.ext.isConnectedNetwork
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException

/**
 * <pre>
 *     author: aotuman
 *     date  : 2021/06/03
 *     desc  :
 * </pre>
 */
@OptIn(ExperimentalPagingApi::class)
class NewsRemoteMediator(
    val api: NewsService,
    val db: AppDataBase
) : RemoteMediator<Int, NewsEntity>(){

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, NewsEntity>
    ): MediatorResult {

        try {
            /**
             * 在这个方法内将会做三件事
             *
             * 1. 参数 LoadType 有个三个值，关于这三个值如何进行判断
             *      LoadType.REFRESH
             *      LoadType.PREPEND
             *      LoadType.APPEND
             *
             * 2. 访问网络数据
             *
             * 3. 将网路插入到本地数据库中
             */
            val newsIDDao = db.newsIDDao()
            val newsDao = db.newsDao()
            // 第一步： 判断 LoadType
            val pageStartNewsId = when (loadType) {
                // 首次访问 或者调用 PagingDataAdapter.refresh()
                LoadType.REFRESH -> {
                    insertOrUpdateNewsIDDB(db)
                }

                // 在当前加载的数据集的开头加载数据时
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)

                LoadType.APPEND -> { // 下来加载更多时触发

                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null) {
                        return MediatorResult.Success(
                            endOfPaginationReached = true
                        )
                    }
                    lastItem.newsId
                }
            }

            // 1.从新闻id表拿出当前页的id集合
            val newsIDEntities = if (loadType == LoadType.REFRESH) {
                newsIDDao.queryFirstPageNews(pageStartNewsId, state.config.pageSize)
            } else {
                newsIDDao.queryNextPageNews(pageStartNewsId, state.config.pageSize)
            }
            val ids = newsIDEntities?.map { it.id }
            if (ids.isNullOrEmpty()) return MediatorResult.Success(endOfPaginationReached = true)
            // 2.根据新闻id集合去新闻表取数据
            val localNewsIDs = newsDao.findByNewsIDs(ids)?.map { it.newsId }
            if (!AppHelper.mContext.isConnectedNetwork()) {
                return MediatorResult.Success(endOfPaginationReached = true)
            }
            if (localNewsIDs.isNullOrEmpty()) {
                // 3.新闻表找不到的id集合，统一去服务器去找
                val newsEntities = fetchLatestNewsDetail(ids)
                // 4.服务器找到数据之后，插入数据库
                newsEntities?.let {
                    // 插入新闻表
                    db.withTransaction {
                        newsDao.insertNews(it)
                    }
                }
            } else {
                // 3.找出新闻ID表与新闻表的差集，统一交给服务器去找差集
                val serverNewsIDs = ids.filterNot { localNewsIDs.contains(it) }
                val newsEntities = fetchLatestNewsDetail(serverNewsIDs)
                // 4.服务器找到数据之后，插入数据库
                newsEntities?.let {
                    // 插入新闻表
                    db.withTransaction {
                        newsDao.insertNews(it)
                    }
                }
            }
            return MediatorResult.Success(endOfPaginationReached = true)

        } catch (e: IOException) {
            return MediatorResult.Error(e)
        } catch (e: HttpException) {
            return MediatorResult.Error(e)
        }
    }

    /**
     * 首次访问的时候，插入或更新新闻ID表
     * @return maxId
     */
    private suspend fun insertOrUpdateNewsIDDB(db: AppDataBase):Long {
        val newsIDDao = db.newsIDDao()
        // 查出数据库最新的新闻ID
        val localMaxID = newsIDDao.queryMaxNewsID()
        Timber.tag(TAG).e("localMaxID = ${localMaxID}")
        if (!AppHelper.mContext.isConnectedNetwork()) return localMaxID
        // 首次访问，请求网络获取新闻id集合（服务器默认返回200条）
        val response = api.fetchNewsID()
        // 筛选比本地数据库还要新的新闻
        if (localMaxID > 0) {
            val latestNewsID = response.data?.filter { it.id > localMaxID }
            val latestNewsIDEntities = latestNewsID?.map {
                NewsIDEntity(
                    id = it.id,
                    type = it.type,
                    column = it.column,
                    needUpdate = it.needUpdate
                )
            }
            latestNewsIDEntities?.let {
                // 插入新闻ID表
                db.withTransaction {
                    newsIDDao.insertNewsID(it)
                }
            }
        } else {
            // 数据库新闻表是空表，全部插入
            val latestNewsIDEntities = response.data?.map {
                NewsIDEntity(
                    id = it.id,
                    type = it.type,
                    column = it.column,
                    needUpdate = it.needUpdate
                )
            }
            latestNewsIDEntities?.let {
                // 插入新闻ID表
                db.withTransaction {
                    newsIDDao.insertNewsID(it)
                }
            }
        }
        return newsIDDao.queryMaxNewsID()
    }

    private suspend fun fetchLatestNewsDetail(latestNewsIDs: List<Long>?): List<NewsEntity>? {
        if (latestNewsIDs.isNullOrEmpty()) return null
        // 组装一连串的新闻id，查询新闻详情
        var articleIds = StringBuffer()
        latestNewsIDs.forEachIndexed { index, newsID ->
            if (index == latestNewsIDs.size - 1) {
                articleIds.append(newsID)
            } else {
                articleIds.append("$newsID%2c")
            }
        }
        return api.fetchNewsByIDs("app", "banner", articleIds.toString()).data.map {
            val newsItem = it.value
            NewsEntity(
                newsId = newsItem.newsId,
                title = newsItem.title,
                url = newsItem.url,
                imgurl = newsItem.imgurl,
                pub_time = newsItem.pub_time,
                upNum = newsItem.upNum,
                commentNum = newsItem.commentNum,
                shareUrl = newsItem.shareUrl
            )
        }
    }


    companion object {
        private val TAG = "NewsRemoteMediator"
    }
}
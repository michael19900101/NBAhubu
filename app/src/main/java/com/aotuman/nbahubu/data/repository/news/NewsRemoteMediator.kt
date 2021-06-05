package com.aotuman.nbahubu.data.repository.news

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.aotuman.nbahubu.AppHelper
import com.aotuman.nbahubu.data.entity.news.NewsEntity
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
            val newsDao = db.newsDao()
            val localMaxID = newsDao.queryMaxNewsID()
            Timber.tag(TAG).e("localMaxID = ${localMaxID}")
            val newsIDMap = mutableMapOf<Int, List<Long>>()
            Timber.tag(TAG).e("loadType = ${loadType}")
            // 第一步： 判断 LoadType
            val pageKey = when (loadType) {
                // 首次访问 或者调用 PagingDataAdapter.refresh()
                LoadType.REFRESH -> {
                    insertOrUpdateNewsDB(db)
                    null
                }

                // 在当前加载的数据集的开头加载数据时
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)

                LoadType.APPEND -> { // 下来加载更多时触发

                    /**
                     * 方式一：这种方式比较简单，当前页面最后一条数据是下一页的开始位置
                     * 通过 load 方法的参数 state 获取当页面最后一条数据
                     */
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null) {
                        return MediatorResult.Success(
                            endOfPaginationReached = true
                        )
                    }
                    lastItem.newsId
                }
            }

            if (!AppHelper.mContext.isConnectedNetwork()) {
                // 无网络加载本地数据
                return MediatorResult.Success(endOfPaginationReached = true)
            }

            // 第二步： 请问网络分页数据
            val page = pageKey ?: 0
            val subNewsIDs = newsIDMap[page]
            var articleIds = StringBuffer()
            subNewsIDs?.forEachIndexed { index, id ->
                if (index == subNewsIDs.size - 1) {
                    articleIds.append(id)
                } else {
                    articleIds.append("$id%2c")
                }
            }
            if (subNewsIDs.isNullOrEmpty())  return MediatorResult.Success(endOfPaginationReached = true)

            val result = api.fetchNewsByIDs("app","banner", articleIds.toString()).data
            if (result.isNullOrEmpty())  return MediatorResult.Success(endOfPaginationReached = true)
            Timber.tag(TAG).e(result.toString())

            val endOfPaginationReached = result.isEmpty()

            val item = result.map {
                val newsItem = it.value
                NewsEntity(
                    newsId = newsItem.newsId,
                    title = newsItem.title,
                    url = newsItem.url,
                    imgurl = newsItem.imgurl
                )
            }

            // 第三步： 插入数据库
            db.withTransaction {
                val nextKey = if (endOfPaginationReached) null else page + 1
                newsDao.insertNews(item)
            }
            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)

        } catch (e: IOException) {
            return MediatorResult.Error(e)
        } catch (e: HttpException) {
            return MediatorResult.Error(e)
        }
    }

    private suspend fun insertOrUpdateNewsDB(db: AppDataBase) {
        val newsDao = db.newsDao()
        // 查出数据库最新的新闻ID
        val localMaxID = newsDao.queryMaxNewsID()
        Timber.tag(TAG).e("localMaxID = ${localMaxID}")
        // 首次访问，请求网络获取新闻id集合（服务器默认返回200条）
        val response = api.fetchNewsID()
        // 筛选比本地数据库还要新的新闻
        if (localMaxID > 0) {
            val latestNewsIDs = response.data?.filter { it.id > localMaxID }?.map { it.id }
            val newsEntities = fetchLatestNewsDetail(latestNewsIDs)
            newsEntities?.let {
                // 插入数据库
                db.withTransaction {
                    newsDao.insertNews(it)
                }
            }
        } else {
            // 数据库新闻表是空表，全部插入
            val latestNewsIDs = response.data?.map { it.id }
            val newsEntities = fetchLatestNewsDetail(latestNewsIDs)
            newsEntities?.let {
                // 插入数据库
                db.withTransaction {
                    newsDao.insertNews(it)
                }
            }
        }
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
                imgurl = newsItem.imgurl
            )
        }
    }

    private fun queryNewsFromDB(db: AppDataBase, startNewsId: Int, pageSize: Int) {

    }


    companion object {
        private val TAG = "NewsRemoteMediator"
    }
}
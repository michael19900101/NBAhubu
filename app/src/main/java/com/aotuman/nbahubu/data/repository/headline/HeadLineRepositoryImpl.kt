package com.aotuman.nbahubu.data.repository.headline

import android.util.Log
import com.aotuman.nbahubu.data.entity.headline.HeadLineNewsResponse
import com.aotuman.nbahubu.data.remote.headline.HeadLineService
import com.aotuman.nbahubu.model.headline.HeadLineNewsItemModel
import com.aotuman.nbahubu.model.headline.TopBannerItemModel
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

    override fun fetchHeadLineNews(): Flow<List<HeadLineNewsItemModel>?> {
        return flow{
            val response = api.fetchHeadLineNews()
            val headLineNews = response.data?.map {
                HeadLineNewsItemModel(
                    id = it.id,
                    newsId = it.newsId,
                    title = it.title,
                    thumbnail = it.thumbnail,
                    thumbnail2x = it.thumbnail_2x,
                    publishTime = it.publish_time,
                    likeNum = it.like_num,
                    lockAt = it.lock_at
                )
            }
            Log.e("jbjb","respnes:${response}")
            // 发射转换后的数据
            emit(headLineNews)
        }.flowOn(Dispatchers.IO)
    }

    override fun fetchHeadLineNews(pageNum: Int, lastTime: String): Flow<List<HeadLineNewsItemModel>?> {
        return flow{
            val response = api.fetchHeadLineNews(pageNum, lastTime)
            val headLineNews = response.data?.map {
                HeadLineNewsItemModel(
                    id = it.id,
                    newsId = it.newsId,
                    title = it.title,
                    thumbnail = it.thumbnail,
                    thumbnail2x = it.thumbnail_2x,
                    publishTime = it.publish_time,
                    likeNum = it.like_num,
                    lockAt = it.lock_at
                )
            }
            Log.e("jbjb","respnes:${response}")
            // 发射转换后的数据
            emit(headLineNews)
        }.flowOn(Dispatchers.IO)
    }

    override fun fetchTopBanner(): Flow<List<TopBannerItemModel>?> {
        return flow{
            val response = api.fetchTopBanner()
            val topBanners = response.data["top_banner"]?.map {
                TopBannerItemModel(
                    id = it.id,
                    news_id = it.news_id,
                    title = it.title,
                    subtitle = it.subtitle,
                    category = it.category,
                    thumb = it.thumb,
                    h5 = it.h5,
                    off_time = it.off_time
                )
            }
            Log.e("jbjb","fetchTopBanner:${response}")
            // 发射转换后的数据
            emit(topBanners)
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun fetchTopBannerData(): List<TopBannerItemModel>? {
        val response = api.fetchTopBanner()
        val topBanners = response.data["top_banner"]?.map {
            TopBannerItemModel(
                id = it.id,
                news_id = it.news_id,
                title = it.title,
                subtitle = it.subtitle,
                category = it.category,
                thumb = it.thumb,
                h5 = it.h5,
                off_time = it.off_time
            )
        }
        return topBanners
    }

    override suspend fun fetchHeadLineNewsData(
        pageNum: Int,
        lastTime: String
    ): List<HeadLineNewsItemModel>? {
        val response = api.fetchHeadLineNews(pageNum, lastTime)
        val headLineNews = response.data?.map {
            HeadLineNewsItemModel(
                id = it.id,
                newsId = it.newsId,
                title = it.title,
                thumbnail = it.thumbnail,
                thumbnail2x = it.thumbnail_2x,
                publishTime = it.publish_time,
                likeNum = it.like_num,
                lockAt = it.lock_at
            )
        }
        return headLineNews
    }
}
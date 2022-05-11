package com.aotuman.nbahubu.data.repository.headline

import com.aotuman.nbahubu.data.remote.headline.HeadLineService
import com.aotuman.nbahubu.model.headline.*
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

    override suspend fun fetchNewsDetailData(newsID: String): HeadLineNewsDetailModel? {
        val response = api.fetchHeadLineNewsDetail(newsID)
        response.data?.let {
            val attList = it.cnt_attr?.map {
                cnt_attr_item ->
                HeadLineNewsDetailAttr(
                    itype = cnt_attr_item.itype,
                    placeholder = cnt_attr_item.placeholder,
                    objectStr = cnt_attr_item.objectStr
                )
            }
            return HeadLineNewsDetailModel(
                id = it.id,
                newsId = it.news_id,
                title = it.title,
                thumbnail = it.thumbnail,
                thumbnail2x = it.thumbnail_2x,
                publishTime = it.publish_time,
                attributes = attList
            )
        }
        return null
    }

    override suspend fun fetchHeadLineNewsDetailVideo(videoID: String): HeadLineNewsDetailVideoModel? {
        val response = api.fetchHeadLineNewsDetailVideo(videoID)
        response.data?.let {
            return HeadLineNewsDetailVideoModel(
                url = it.url,
                vid = it.vid,
                title = it.title
            )
        }
        return null
    }
}
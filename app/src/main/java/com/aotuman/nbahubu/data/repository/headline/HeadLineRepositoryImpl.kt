package com.aotuman.nbahubu.data.repository.headline

import android.util.Log
import com.aotuman.nbahubu.data.remote.headline.HeadLineService
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

    override fun fetchNews(): Flow<Int> {
        return flow{
            val response = api.fetchNews()
            Log.e("jbjb","respnes:${response}")
            // 发射转换后的数据
            emit(2)
        }.flowOn(Dispatchers.IO)
    }

    override fun fetchTopBanner(): Flow<List<TopBannerItemModel>?> {
        return flow{
            val response = api.fetchTopBanner()
            var topBanners = response.data["top_banner"]?.map {
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
}
package com.aotuman.nbahubu.data.repository.headline

import com.aotuman.nbahubu.model.headline.HeadLineNewsItemModel
import com.aotuman.nbahubu.model.headline.TopBannerItemModel
import kotlinx.coroutines.flow.Flow

/**
 * <pre>
 *     author: aotuman
 *     date  : 2022/04/30
 *     desc  :
 * </pre>
 */
interface HeadLineRepository {

    fun fetchHeadLineNews(): Flow<List<HeadLineNewsItemModel>?>

    fun fetchHeadLineNews(pageNum: Int,lastTime: String): Flow<List<HeadLineNewsItemModel>?>

    fun fetchTopBanner(): Flow<List<TopBannerItemModel>?>

    /**以下方法不用flow实现*/
    suspend fun fetchTopBannerData():List<TopBannerItemModel>?

    suspend fun fetchHeadLineNewsData(pageNum: Int,lastTime: String): List<HeadLineNewsItemModel>?
}
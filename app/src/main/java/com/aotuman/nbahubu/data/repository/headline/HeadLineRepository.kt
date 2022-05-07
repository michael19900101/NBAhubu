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

    fun fetchTopBanner(): Flow<List<TopBannerItemModel>?>
}
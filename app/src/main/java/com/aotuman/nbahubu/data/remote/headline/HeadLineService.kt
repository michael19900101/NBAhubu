package com.aotuman.nbahubu.data.remote.headline

import com.aotuman.nbahubu.data.entity.headline.HeadLineNewsResponse
import com.aotuman.nbahubu.data.entity.headline.TopBannerResponse
import retrofit2.http.GET

/**
 * <pre>
 *     author: aotuman
 *     date  : 2022/04/30
 *     desc  :
 * </pre>
 */
interface HeadLineService {
    @GET("cms/v1/news/list?column_id=57&page_no=1&last_time=")
    suspend fun fetchHeadLineNews(): HeadLineNewsResponse

    @GET("cms/v1/news/config?category=top_banner")
    suspend fun fetchTopBanner(): TopBannerResponse
}
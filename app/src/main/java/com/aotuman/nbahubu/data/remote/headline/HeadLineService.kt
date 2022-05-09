package com.aotuman.nbahubu.data.remote.headline

import com.aotuman.nbahubu.data.entity.headline.HeadLineNewsDetailResponse
import com.aotuman.nbahubu.data.entity.headline.HeadLineNewsResponse
import com.aotuman.nbahubu.data.entity.headline.TopBannerResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * <pre>
 *     author: aotuman
 *     date  : 2022/04/30
 *     desc  :
 * </pre>
 */
interface HeadLineService {

    @GET("cms/v1/news/config?category=top_banner")
    suspend fun fetchTopBanner(): TopBannerResponse

    @GET("cms/v1/news/list?column_id=57&page_no=1&last_time=")
    suspend fun fetchHeadLineNews(): HeadLineNewsResponse

    @GET("cms/v1/news/list?column_id=57")
    suspend fun fetchHeadLineNews(@Query("page_no") pageNum: Int,
                                  @Query("last_time") lastTime: String): HeadLineNewsResponse

    @GET("cms/v1/news/info")
    suspend fun fetchHeadLineNewsDetail(@Query("news_id") newsID: String): HeadLineNewsDetailResponse
}
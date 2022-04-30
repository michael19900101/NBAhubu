package com.aotuman.nbahubu.data.remote.headline

import com.aotuman.nbahubu.data.entity.headline.HeadLineResponse
import com.aotuman.nbahubu.data.entity.temp.NewsResponse
import retrofit2.http.GET

/**
 * <pre>
 *     author: aotuman
 *     date  : 2022/04/30
 *     desc  :
 * </pre>
 */
interface HeadLineService {
    @GET("cms/v1/news/list?column_id=3002")
    suspend fun fetchNews(): HeadLineResponse
}
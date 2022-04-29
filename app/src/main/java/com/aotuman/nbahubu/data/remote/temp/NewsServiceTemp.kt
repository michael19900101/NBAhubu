package com.aotuman.nbahubu.data.remote.temp

import com.aotuman.nbahubu.data.entity.temp.NewsResponse
import retrofit2.http.GET

/**
 * <pre>
 *     author: aotuman
 *     date  : 2022/04/29
 *     desc  :
 * </pre>
 */
interface NewsServiceTemp {
    @GET("cms/v1/news/list?column_id=3002")
    suspend fun fetchNews(): NewsResponse
}
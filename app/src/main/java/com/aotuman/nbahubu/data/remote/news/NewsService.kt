package com.aotuman.nbahubu.data.remote.news

import com.aotuman.nbahubu.data.entity.news.NewsIDResponse
import retrofit2.http.GET

/**
 * <pre>
 *     author: aotuman
 *     date  : 2021/05/31
 *     desc  :
 * </pre>
 */
interface NewsService {
    @GET("news/index?from=app&column=banner")
    suspend fun fetchNewsID(): NewsIDResponse
}
package com.aotuman.nbahubu.data.remote.news

import com.aotuman.nbahubu.data.entity.news.NewsIDResponse
import com.aotuman.nbahubu.data.entity.news.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

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

//    @GET("news/item?from=app&column=banner&articleIds={articleIds}")
    @GET("news/item")
    suspend fun fetchNewsByIDs(
        @Query("from") from: String,
        @Query("column") column: String,
        @Query("articleIds", encoded = true) articleIds: String
    ): NewsResponse
}
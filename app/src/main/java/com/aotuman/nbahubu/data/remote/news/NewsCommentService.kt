package com.aotuman.nbahubu.data.remote.news

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * <pre>
 *     author: aotuman
 *     date  : 2021/06/21
 *     desc  :
 * </pre>
 */
interface NewsCommentService {

    @GET("article/{articleId}/comment/v2")
    suspend fun fetchCommentList(@Path("articleId") articleId:Long,
                                 @Query("targetid") targetid: Long,
                                 @Query("cursor") cursor: Long,
                                 @Query("pageflag") pageflag: Long,
                                 @Query("orinum") orinum: Long,
                                 @Query("orirepnum") orirepnum: Long):NewsCommentResponse
}
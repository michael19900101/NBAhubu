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
    suspend fun fetchCommentList(@Path("articleId") articleId:String,
                                 @Query("targetid") targetid: String,
                                 @Query("cursor") cursor: Int,
                                 @Query("pageflag") pageflag: Int,
                                 @Query("orinum") orinum: Int,
                                 @Query("orirepnum") orirepnum: Int):NewsCommentResponse
}
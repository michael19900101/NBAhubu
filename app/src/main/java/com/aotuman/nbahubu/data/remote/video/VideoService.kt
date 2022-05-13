package com.aotuman.nbahubu.data.remote.video

import com.aotuman.nbahubu.data.entity.video.VideoDetailResponse
import com.aotuman.nbahubu.data.entity.video.VideoResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * <pre>
 *     author: aotuman
 *     date  : 2022/05/13
 *     desc  :
 * </pre>
 */
interface VideoService {

    @GET("cms/v1/news/list")
    suspend fun fetchVideos(
        @Query("column_id") columnID: Int,
        @Query("page_no") pageNum: Int,
        @Query("last_time") lastTime: String
    ): VideoResponse

    @GET("cms/v1/video/playurl?quality=shd")
    suspend fun fetchVideoDetail(@Query("vid") videoID: String): VideoDetailResponse
}
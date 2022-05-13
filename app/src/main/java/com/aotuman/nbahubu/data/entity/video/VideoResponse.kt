package com.aotuman.nbahubu.data.entity.video

import com.google.gson.annotations.SerializedName

/**
 * <pre>
 *     author: aotuman
 *     date  : 2022/05/13
 *     desc  :
 * </pre>
 */
data class VideoResponse(
    val code: String?,
    val data: List<VideoItemResponse>?,
    val msg: String?,
)

data class VideoItemResponse(
    var id: Long,
    @SerializedName("news_id")
    var newsId: Long,
    var title: String = "",
    var thumbnail: String = "",
    var thumbnail_2x: String = "",
    var publish_time: String = "",
    var like_num: Int = 0,
    var lock_at: String = "",
    var atype: String = "",
    var vid: String = ""
)

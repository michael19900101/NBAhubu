package com.aotuman.nbahubu.data.entity.headline

import com.google.gson.annotations.SerializedName

/**
 * <pre>
 *     author: aotuman
 *     date  : 2022/04/30
 *     desc  :
 * </pre>
 */

data class HeadLineNewsResponse(
        val code: String?,
        val data: List<HeadLineNewsItem>?,
        val msg: String?,
)

data class HeadLineNewsItem(
        var id: Long,
        @SerializedName("news_id")
        var newsId: Long,
        var title: String = "",
        var thumbnail: String = "",
        var thumbnail_2x: String = "",
        var publish_time: String = "",
        var like_num: Int = 0,
        var lock_at: String = ""
)
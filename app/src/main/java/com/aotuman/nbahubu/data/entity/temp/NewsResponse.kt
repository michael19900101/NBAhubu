package com.aotuman.nbahubu.data.entity.temp

/**
 * <pre>
 *     author: aotuman
 *     date  : 2022/04/29
 *     desc  :
 * </pre>
 */

data class NewsResponse(
        val code: String?,
        val data: List<NewsItem>?,
        val msg: String?,
)

data class NewsItem(
        var id: Long,
        var newsId: Long,
        var title: String = "",
        var thumbnail: String = "",
        var thumbnail_2x: String = "",
        var publish_time: String = "",
        var like_num: Int = 0
)
package com.aotuman.nbahubu.data.entity.headline

/**
 * <pre>
 *     author: aotuman
 *     date  : 2022/04/30
 *     desc  :
 * </pre>
 */

data class HeadLineResponse(
        val code: String?,
        val data: List<HeadLineItem>?,
        val msg: String?,
)

data class HeadLineItem(
        var id: Long,
        var newsId: Long,
        var title: String = "",
        var thumbnail: String = "",
        var thumbnail_2x: String = "",
        var publish_time: String = "",
        var like_num: Int = 0
)
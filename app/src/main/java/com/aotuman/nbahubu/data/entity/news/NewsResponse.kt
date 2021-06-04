package com.aotuman.nbahubu.data.entity.news

/**
 * <pre>
 *     author: aotuman
 *     date  : 2021/06/04
 *     desc  :
 * </pre>
 */
data class NewsResponse(
        val code: String?,
        val data: Map<Long, NewsItem>,
        val version: String?,
)

data class NewsItem(
        var newsId: Long,
        var title: String = "",
        var url: String = "",
        var imgurl: String = ""
)
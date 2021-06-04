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
//        val data: List<NewsItem>,
        val data: Map<Int, NewsItem>,
        val version: String?,
)

data class NewsItem(
        var newsId: Int,
        var title: String = "",
        var url: String = "",
        var imgurl: String = ""
)
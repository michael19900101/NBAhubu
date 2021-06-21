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
        var imgurl: String = "",
        var pub_time: String = "",
        var upNum: Int = 0,
        var commentNum: Int = 0,
        var shareUrl: String = "",
        var content: List<NewsContentItem>,
        var commentParams: CommentParams
)

data class NewsContentItem(
        var type: String,
        var info: String,
        var img: Map<String, NewsContentImg>
)

data class NewsContentImg(
        var height: String,
        var width: String,
        var imgurl: String
)

data class CommentParams(
        var targetId: Long = 0,
        var commentsNum: Long = 0
)

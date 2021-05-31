package com.aotuman.nbahubu.data.entity

/**
 * <pre>
 *     author: aotuman
 *     date  : 2021/05/31
 *     desc  :
 * </pre>
 */

data class NewsIDResponse(
        val code: String?,
        val data: List<NewsID>?,
        val version: String?,
)

data class NewsID(
        var id: String = "",
        var type: String = "",
        var column: String = "",
        var needUpdate: String = "",
)
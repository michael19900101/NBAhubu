package com.aotuman.nbahubu.model.headline

/**
 * <pre>
 *     author: aotuman
 *     date  : 2021/05/07
 *     desc  :
 * </pre>
 */
data class HeadLineNewsItemModel (
    var id: Long,
    var newsId: Long,
    var title: String?,
    var thumbnail: String?,
    var thumbnail2x: String?,
    var publishTime: String?,
    var likeNum: Int,
    var lockAt: String?
)
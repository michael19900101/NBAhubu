package com.aotuman.nbahubu.model.video

/**
 * <pre>
 *     author: aotuman
 *     date  : 2022/05/13
 *     desc  :
 * </pre>
 */
data class VideoItemModel (
    var id: Long,
    var newsId: Long,
    var title: String?,
    var thumbnail: String?,
    var thumbnail2x: String?,
    var publishTime: String?,
    var likeNum: Int,
    var lockAt: String?,
    var atype: String?,
    var vid: String?,
    var videoUrl: String? = null
)
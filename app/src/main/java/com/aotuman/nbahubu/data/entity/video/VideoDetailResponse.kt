package com.aotuman.nbahubu.data.entity.video

/**
 * <pre>
 *     author: aotuman
 *     date  : 2022/05/13
 *     desc  :
 * </pre>
 */
data class VideoDetailResponse(
    val code: String?,
    val data: VideoDetailRsp?,
    val msg: String?,
)

data class VideoDetailRsp(
    var url: String?,
    var vid: String?,
    var title: String?
)
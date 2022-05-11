package com.aotuman.nbahubu.data.entity.headline

/**
 * <pre>
 *     author: aotuman
 *     date  : 2022/05/11
 *     desc  :
 * </pre>
 */
data class HeadLineNewsDetailVideoResponse(
    val code: String?,
    val data: HeadLineNewsDetailVideoRsp?,
    val msg: String?,
)

data class HeadLineNewsDetailVideoRsp(
    var url: String?,
    var vid: String?,
    var title: String?
)
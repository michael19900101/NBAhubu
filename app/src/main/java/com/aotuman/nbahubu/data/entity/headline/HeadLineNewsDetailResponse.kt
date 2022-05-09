package com.aotuman.nbahubu.data.entity.headline

import com.google.gson.annotations.SerializedName

/**
 * <pre>
 *     author: aotuman
 *     date  : 2022/05/09
 *     desc  :
 * </pre>
 */

data class HeadLineNewsDetailResponse(
        val code: String?,
        val data: HeadLineNewsDetailRsp?,
        val msg: String?,
)

data class HeadLineNewsDetailRsp(
        var id: Long,
        var news_id: Long,
        var title: String = "",
        var thumbnail: String = "",
        var thumbnail_2x: String = "",
        var publish_time: String = "",
        var cnt_attr: List<HeadLineNewsDetailAttr>? = null
)

data class HeadLineNewsDetailAttr(
        var itype: String,
        var placeholder: String,

        @SerializedName("object")
        var objectStr: String
)
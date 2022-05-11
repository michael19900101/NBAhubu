package com.aotuman.nbahubu.model.headline

import com.google.gson.annotations.SerializedName

/**
 * <pre>
 *     author: aotuman
 *     date  : 2021/05/09
 *     desc  :
 * </pre>
 */
data class HeadLineNewsDetailModel (
    var id: Long,
    var newsId: Long,
    var title: String?,
    var thumbnail: String?,
    var thumbnail2x: String?,
    var publishTime: String?,
    var attributes: List<HeadLineNewsDetailAttr>?
)

data class HeadLineNewsDetailAttr(
    var itype: String,
    var placeholder: String,
    var objectStr: String)

data class HeadLineNewsDetailTxtAttr(
    var id: Long,
    var content: String?
)

data class HeadLineNewsDetailImgAttr(
    var id: Long,
    @SerializedName("imgurl")
    var imgUrl: String?
)

data class HeadLineNewsDetailVideoAttr(
    var id: Long,
    var vid: String?,
    var image: String?
)

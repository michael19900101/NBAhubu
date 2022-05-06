package com.aotuman.nbahubu.data.entity.headline

/**
 * <pre>
 *     author: aotuman
 *     date  : 2022/05/06
 *     desc  :
 * </pre>
 */

data class TopBannerResponse(
        val code: String?,
        val data: Map<String, List<BannerItemResponse>>,
        val msg: String?,
)

data class BannerItemResponse(
        var id: Long,
        var news_id: String = "",
        var title: String = "",
        var subtitle: String = "",
        var category: String = "",
        var thumb: String = "",
        var h5: String = "",
        var off_time: String = ""
)
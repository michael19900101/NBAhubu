package com.aotuman.nbahubu.model.headline

data class TopBannerItemModel (
    var id: Long,
    var news_id: String?,
    var title: String?,
    var subtitle: String?,
    var category: String?,
    var thumb: String?,
    var h5: String?,
    var off_time: String?
)
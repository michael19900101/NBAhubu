package com.aotuman.nbahubu.data.entity.news

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NewsEntity (
    @PrimaryKey
    var newsId: Long,
    var title: String,
    var url: String,
    var imgurl: String,
    var upNum: Int,
    var commentNum: Int,
    var shareUrl: String
)
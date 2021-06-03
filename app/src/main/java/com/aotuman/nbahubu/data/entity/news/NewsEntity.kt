package com.aotuman.nbahubu.data.entity.news

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NewsEntity (
    @PrimaryKey
    var newsId: String,
    var title: String,
    var url: String,
    var imgurl: String,

    val page: Int = 0
)
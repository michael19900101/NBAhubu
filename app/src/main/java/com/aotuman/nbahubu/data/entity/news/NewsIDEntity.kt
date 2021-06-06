package com.aotuman.nbahubu.data.entity.news

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * <pre>
 *     author: aotuman
 *     date  : 2021/06/06
 *     desc  :
 * </pre>
 */
@Entity
data class NewsIDEntity (
    @PrimaryKey
    var id: Long,
    var type: String,
    var column: String,
    var needUpdate: Boolean)
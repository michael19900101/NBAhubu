package com.aotuman.nbahubu.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * <pre>
 *     author: aotuman
 *     date  : 2021/05/11
 *     desc  :
 * </pre>
 */

@Entity
data class PlayerEntity(
    @PrimaryKey
    var id: String = "",
    var cnName: String = "",
    var enName: String = "",
    var capital: String = "",
    var teamId: String = "",
    var teamName: String = "",
    var teamLogo: String = "",
    var teamUrl: String = "",
    var jerseyNum: String = "",
    var position: String = "",
    var birthStateCountry: String = "",
    var birth: String = "",
    var height: String = "",
    var weight: String = "",
    var icon: String = "",
    var detailUrl: String = "",
    var wage: String = ""
)
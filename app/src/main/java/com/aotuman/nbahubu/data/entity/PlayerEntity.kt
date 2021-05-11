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
    val name: String,
    var pokemonId: Int = 0,
    val page: Int = 0,
    val url: String,
    val remoteName: String
)
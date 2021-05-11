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
data class RemoteKeysEntity(
    @PrimaryKey
    val remoteName: String,
    val nextKey: Int?
)
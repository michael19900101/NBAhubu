package com.aotuman.nbahubu.data.local

import androidx.room.TypeConverter
import com.google.gson.GsonBuilder
import com.aotuman.nbahubu.data.entity.player.PlayerInfoEntity
import com.aotuman.nbahubu.ext.fromJson
import com.aotuman.nbahubu.ext.typedToJson

/**
 * <pre>
 *     author: aotuman
 *     date  : 2021/05/11
 *     desc  :
 * </pre>
 */
open class LocalTypeConverter {

    @TypeConverter
    fun json2StatsEntity(src: String): List<PlayerInfoEntity.Stats>? =
        GsonBuilder().create().fromJson(src)

    @TypeConverter
    fun statsEntity2Json(data: List<PlayerInfoEntity.Stats>): String =
        GsonBuilder().create().typedToJson(data)

    @TypeConverter
    fun json2TypeEntity(src: String): List<PlayerInfoEntity.Type>? =
        GsonBuilder().create().fromJson(src)

    @TypeConverter
    fun typeEntity2Json(data: List<PlayerInfoEntity.Type>): String =
        GsonBuilder().create().typedToJson(data)

}
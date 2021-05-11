package com.aotuman.nbahubu.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.aotuman.nbahubu.data.entity.RemoteKeysEntity
import com.aotuman.nbahubu.data.entity.PlayerEntity
import com.aotuman.nbahubu.data.entity.PlayerInfoEntity

/**
 * <pre>
 *     author: aotuman
 *     date  : 2021/05/11
 *     desc  :
 * </pre>
 */

@Database(
    entities = arrayOf(PlayerEntity::class, RemoteKeysEntity::class, PlayerInfoEntity::class),
    version = 2, exportSchema = false
)
@TypeConverters(value = arrayOf(LocalTypeConverter::class))
abstract class AppDataBase : RoomDatabase() {

    abstract fun playerDao(): PlayerDao
    abstract fun remoteKeysDao(): RemoteKeysDao
    abstract fun playerInfoDao(): PlayerInfoDao
}

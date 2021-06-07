package com.aotuman.nbahubu.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.aotuman.nbahubu.data.entity.RemoteKeysEntity
import com.aotuman.nbahubu.data.entity.news.NewsEntity
import com.aotuman.nbahubu.data.entity.news.NewsIDEntity
import com.aotuman.nbahubu.data.entity.player.PlayerEntity
import com.aotuman.nbahubu.data.entity.player.PlayerInfoEntity
import com.aotuman.nbahubu.data.local.news.NewsDao
import com.aotuman.nbahubu.data.local.news.NewsIDDao
import com.aotuman.nbahubu.data.local.player.PlayerDao
import com.aotuman.nbahubu.data.local.player.PlayerInfoDao

/**
 * <pre>
 *     author: aotuman
 *     date  : 2021/05/11
 *     desc  :
 * </pre>
 */

@Database(
    entities = arrayOf(PlayerEntity::class, NewsEntity::class, NewsIDEntity::class,
        RemoteKeysEntity::class, PlayerInfoEntity::class),
    version = 1, exportSchema = false
)
@TypeConverters(value = arrayOf(LocalTypeConverter::class))
abstract class AppDataBase : RoomDatabase() {

    abstract fun playerDao(): PlayerDao
    abstract fun newsDao(): NewsDao
    abstract fun newsIDDao(): NewsIDDao
    abstract fun remoteKeysDao(): RemoteKeysDao
    abstract fun playerInfoDao(): PlayerInfoDao
}

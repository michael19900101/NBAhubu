package com.aotuman.nbahubu.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aotuman.nbahubu.data.entity.RemoteKeysEntity

/**
 * <pre>
 *     author: aotuman
 *     date  : 2021/05/11
 *     desc  :
 * </pre>
 */

@Dao
interface RemoteKeysDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKeyEntity: RemoteKeysEntity)

    @Query("SELECT * FROM RemoteKeysEntity where remoteName = :name ")
    suspend fun getRemoteKeys(name: String): RemoteKeysEntity?

    @Query("DELETE FROM RemoteKeysEntity where remoteName = :name")
    suspend fun clearRemoteKeys(name: String)
}
package com.aotuman.nbahubu.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aotuman.nbahubu.data.entity.PlayerEntity

/**
 * <pre>
 *     author: aotuman
 *     date  : 2021/05/11
 *     desc  :
 * </pre>
 */
@Dao
interface PlayerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlayer(playerList: List<PlayerEntity>)

    @Query("SELECT * FROM PlayerEntity")
    fun getPlayers(): List<PlayerEntity>

    @Query("SELECT * FROM PlayerEntity")
    fun getPlayer(): PagingSource<Int, PlayerEntity>

    @Query("DELETE FROM PlayerEntity where cnName = :name")
    suspend fun clearPlayer(name: String)

    @Query("DELETE FROM PlayerEntity")
    suspend fun clearPlayer()

    @Query("SELECT COUNT(*) FROM PlayerEntity")
    suspend fun countPlayer(): Int

    @Query("SELECT * FROM PlayerEntity where cnName LIKE '%' || :parameter || '%'")
    fun playerInfoByParameter(parameter: String): PagingSource<Int, PlayerEntity>
}
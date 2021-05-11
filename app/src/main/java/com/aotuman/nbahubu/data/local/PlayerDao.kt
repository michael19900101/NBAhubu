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
    suspend fun insertPokemon(playerList: List<PlayerEntity>)

    @Query("SELECT * FROM PlayerEntity")
    fun getPokemon(): PagingSource<Int, PlayerEntity>

    @Query("DELETE FROM PlayerEntity where remoteName = :name")
    suspend fun clearPokemon(name: String)

    @Query("SELECT * FROM PlayerEntity where name LIKE '%' || :parameter || '%'")
    fun pokemonInfoByParameter(parameter: String): PagingSource<Int, PlayerEntity>
}
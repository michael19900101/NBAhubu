package com.aotuman.nbahubu.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aotuman.nbahubu.data.entity.PlayerInfoEntity

/**
 * <pre>
 *     author: aotuman
 *     date  : 2021/05/11
 *     desc  :
 * </pre>
 */

@Dao
interface PlayerInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemon(playerInfoEntity: PlayerInfoEntity)

    @Query("SELECT * FROM PlayerInfoEntity where name = :name")
    suspend fun getPokemon(name: String): PlayerInfoEntity?

    @Query("DELETE FROM PlayerInfoEntity where id = :id")
    suspend fun clearPokemonInfo(id: Int)
}
package com.aotuman.nbahubu.data.repository.player

import androidx.paging.PagingData
import com.aotuman.nbahubu.data.entity.player.Player
import com.aotuman.nbahubu.data.remote.player.PlayerResult
import com.aotuman.nbahubu.model.player.PlayerInfoModel
import com.aotuman.nbahubu.model.player.PlayerItemModel
import kotlinx.coroutines.flow.Flow

/**
 * <pre>
 *     author: aotuman
 *     date  : 2021/05/11
 *     desc  :
 * </pre>
 */
interface PlayerRepository {

    fun fetchPlayerList1(): Flow<List<Player>>

    fun fetchPlayerList(): Flow<PagingData<PlayerItemModel>>

    suspend fun fetchPlayerInfo(name: String): Flow<PlayerResult<PlayerInfoModel>>

    suspend fun fetchPlayerByParameter(parameter: String): Flow<PagingData<PlayerItemModel>>
}
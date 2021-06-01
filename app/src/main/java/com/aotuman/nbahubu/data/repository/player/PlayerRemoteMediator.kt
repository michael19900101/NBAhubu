package com.aotuman.nbahubu.data.repository.player

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.aotuman.nbahubu.data.local.AppDataBase
import com.aotuman.nbahubu.data.remote.player.PlayerService
import com.aotuman.nbahubu.data.entity.player.PlayerEntity

/**
 * <pre>
 *     author: aotuman
 *     date  : 2021/05/11
 *     desc  :
 * </pre>
 */
@OptIn(ExperimentalPagingApi::class)
class PlayerRemoteMediator(
    val api: PlayerService,
    val db: AppDataBase
) : RemoteMediator<Int, PlayerEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PlayerEntity>
    ): MediatorResult {
        return MediatorResult.Error(Exception("xxx"))
    }

    companion object {
        private val TAG = "PokemonRemoteMediator"
        private val remotePokemon = "pokemon"
    }


}
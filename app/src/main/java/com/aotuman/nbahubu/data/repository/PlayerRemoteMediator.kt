package com.aotuman.nbahubu.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.aotuman.nbahubu.AppHelper
import com.aotuman.nbahubu.data.local.AppDataBase
import com.aotuman.nbahubu.data.remote.PlayerService
import com.aotuman.nbahubu.data.entity.PlayerEntity
import com.aotuman.nbahubu.data.entity.RemoteKeysEntity
import com.aotuman.nbahubu.ext.isConnectedNetwork
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException

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
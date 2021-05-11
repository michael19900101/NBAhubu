package com.aotuman.nbahubu.data.repository

import androidx.paging.PagingData
import com.aotuman.nbahubu.data.entity.PlayerResponse
import com.aotuman.nbahubu.data.remote.PlayerResult
import com.aotuman.nbahubu.model.PlayerInfoModel
import com.aotuman.nbahubu.model.PlayerItemModel
import kotlinx.coroutines.flow.Flow

/**
 * <pre>
 *     author: aotuman
 *     date  : 2021/05/11
 *     desc  :
 * </pre>
 */
interface Repository {

    fun fetchPlayerList1(): Flow<PlayerResponse>

    fun fetchPlayerList(): Flow<PagingData<PlayerItemModel>>

    suspend fun fetchPlayerInfo(name: String): Flow<PlayerResult<PlayerInfoModel>>

    suspend fun fetchPlayerByParameter(parameter: String): Flow<PagingData<PlayerItemModel>>
}
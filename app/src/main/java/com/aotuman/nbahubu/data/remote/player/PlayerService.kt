package com.aotuman.nbahubu.data.remote.player

import com.aotuman.nbahubu.data.entity.player.PlayerResponse
import com.aotuman.nbahubu.data.entity.ListingResponse
import com.aotuman.nbahubu.data.entity.NetWorkPlayerInfo
import com.aotuman.nbahubu.data.entity.player.PlayerDetailResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * <pre>
 *     author: aotuman
 *     date  : 2021/05/11
 *     desc  :
 * </pre>
 */
interface PlayerService {
    @GET("player/list")
    suspend fun fetchPlayerList1(): PlayerResponse

    @GET("player/baseInfo")
    suspend fun fetchPlayerDetail(@Query("playerId") playerId: String): PlayerDetailResponse

    @GET("player")
    suspend fun fetchPlayerList(
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ): ListingResponse

    @GET("player/{name}")
    suspend fun fetchPokemonInfo(@Path("name") name: String): NetWorkPlayerInfo
}
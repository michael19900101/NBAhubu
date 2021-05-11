package com.aotuman.nbahubu.data.remote

import com.aotuman.nbahubu.data.entity.PlayerResponse
import com.aotuman.nbahubu.data.entity.ListingResponse
import com.aotuman.nbahubu.data.entity.NetWorkPlayerInfo
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

    @GET("player")
    suspend fun fetchPlayerList(
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ): ListingResponse

    @GET("player/{name}")
    suspend fun fetchPokemonInfo(@Path("name") name: String): NetWorkPlayerInfo
}
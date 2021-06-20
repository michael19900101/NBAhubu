package com.aotuman.nbahubu.data.entity.player

/**
 * <pre>
 *     author: aotuman
 *     date  : 2021/06/20
 *     desc  :
 * </pre>
 */
data class PlayerSeasonResponse(
    val code: String?,
    val data: PlayerSeasonData,
    val version: String?,
)

data class PlayerSeasonData(
        val lastMatches: PlayerStatsData,
        val stats: PlayerStatsData
)
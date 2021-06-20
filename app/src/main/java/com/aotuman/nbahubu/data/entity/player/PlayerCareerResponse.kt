package com.aotuman.nbahubu.data.entity.player

/**
 * <pre>
 *     author: aotuman
 *     date  : 2021/06/20
 *     desc  :
 * </pre>
 */
data class PlayerCareerResponse(
    val code: String?,
    val data: PlayerCareerData,
    val version: String?,
)

data class PlayerCareerData(
        val reg: PlayerStatsData?,
        val playoff: PlayerStatsData?
)
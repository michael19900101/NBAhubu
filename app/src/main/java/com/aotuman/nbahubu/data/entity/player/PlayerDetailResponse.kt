package com.aotuman.nbahubu.data.entity.player

/**
 * <pre>
 *     author: aotuman
 *     date  : 2021/06/16
 *     desc  :
 * </pre>
 */
data class PlayerDetailResponse(
    val code: String?,
    val data: PlayerDetail,
    val version: String?,
)

data class PlayerDetail(
        var cnName: String = "",
        var enName: String = "",
        var teamName: String = "",
        var teamLogo: String = "",
        var simpleTeamLogo: String = "",
        var jerseyNum: String = "",
        var position: String = "",
        var weight: String = "",
        var height: String = "",
        var logo: String = "",
        var birthDate: String = "",
        var draftYear: String = "",
        val stats: StatsData,
        val maxStats: StatsData,
        val season: String = ""
)

data class StatsData(
    var points: Float,
    var assists: Float,
    var blocks: Float,
    var steals: Float,
    var rebounds: Float,
)
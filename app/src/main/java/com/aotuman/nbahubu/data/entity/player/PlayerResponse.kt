package com.aotuman.nbahubu.data.entity.player

/**
 * <pre>
 *     author: aotuman
 *     date  : 2021/05/11
 *     desc  :
 * </pre>
 */

data class PlayerResponse(
    val code: String?,
    val data: List<Player>?,
    val version: String?,
)

data class Player(
        var id: String = "",
        var cnName: String = "",
        var enName: String = "",
        var capital: String = "",
        var teamId: String = "",
        var teamName: String = "",
        var teamLogo: String = "",
        var teamUrl: String = "",
        var jerseyNum: String = "",
        var position: String = "",
        var birthStateCountry: String = "",
        var birth: String = "",
        var height: String = "",
        var weight: String = "",
        var icon: String = "",
        var detailUrl: String = "",
        var wage: String = ""
)
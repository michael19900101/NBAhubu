package com.aotuman.nbahubu.data.remote.news

data class NewsCommentResponse(
    val data: Data,
    val errCode: Int
) {
    data class Data(
        val targetid: Long,
        val hottotal: Int,
        val hotreqnum: Int,
        val hotretnum: Int,
        val orireqnum: Int,
        val oriretnum: Int,
        val oritotal: Int,
        val bucketid: Int,
        val first: String,
        val last: String,
        val hasnext: Boolean,
        val hotCommList: List<Any>,
        val oriCommList: List<OriComm>,
        val repCommList: Map<String, List<OriComm>>,
        val targetInfo: TargetInfo,
        val userList: Map<String, List<User>>
    )

    data class User(
        val gender: String,
        val head: String,
        val mediaid: String,
        val nick: String,
        val region: String,
        val thirdlogin: String,
        val uidex: String,
        val userid: String,
        val viptype: String,
        val wbuserinfo: List<Any>
    )

    data class OriComm(
        val checkhotscale: String,
        val checkstatus: String,
        val checktype: String,
        val content: String,
        val custom: String,
        val emotionaltag: Int,
        val highlight: Int,
        val id: String,
        val indexscore: Long,
        val isauthor: Int,
        val iscity: Int,
        val isdeleted: Int,
        val isdown: Int,
        val isfans: Int,
        val isgod: Int,
        val ishide: Int,
        val ispick: Int,
        val jumpdesc: String,
        val jumpurl: String,
        val orireplynum: String,
        val parent: String,
        val pokenum: String,
        val puserid: String,
        val repnum: String,
        val richtype: String,
        val rootid: String,
        val source: String,
        val targetid: String,
        val thirdid: String,
        val time: String,
        val up: String,
        val userid: String,
    )

    data class TargetInfo(
        val appid: String,
        val articleid: String,
        val checkstatus: String,
        val checktype: String,
        val city: String,
        val commentnum: String,
        val commup: String,
        val topicids: String
    )
}
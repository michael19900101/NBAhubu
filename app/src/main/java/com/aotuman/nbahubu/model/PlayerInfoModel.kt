package com.aotuman.nbahubu.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * <pre>
 *     author: aotuman
 *     date  : 2021/05/11
 *     desc  :
 * </pre>
 */
@Parcelize
data class PlayerInfoModel(
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
    var wage: String = "",
) : Parcelable

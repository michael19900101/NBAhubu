package com.aotuman.nbahubu.model.player

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import kotlinx.android.parcel.Parcelize

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/11
 *     desc  :
 * </pre>
 */

@Parcelize
data class PlayerItemModel(
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
) : Parcelable {
    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<PlayerItemModel>() {
            override fun areItemsTheSame(
                oldItem: PlayerItemModel,
                newItem: PlayerItemModel
            ): Boolean =
                oldItem.enName == newItem.enName

            override fun areContentsTheSame(
                oldItem: PlayerItemModel,
                newItem: PlayerItemModel
            ): Boolean =
                oldItem == newItem
        }
    }
}
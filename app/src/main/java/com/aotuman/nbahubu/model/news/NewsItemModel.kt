package com.aotuman.nbahubu.model.news

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import kotlinx.android.parcel.Parcelize

/**
 * <pre>
 *     author: aotuman
 *     date  : 2020/06/03
 *     desc  :
 * </pre>
 */

@Parcelize
data class NewsItemModel(
        var newsId: String = "",
        var title: String = "",
        var url: String = "",
        var imgurl: String = ""
) : Parcelable {
    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<NewsItemModel>() {
            override fun areItemsTheSame(
                oldItem: NewsItemModel,
                newItem: NewsItemModel
            ): Boolean =
                oldItem.newsId == newItem.newsId

            override fun areContentsTheSame(
                oldItem: NewsItemModel,
                newItem: NewsItemModel
            ): Boolean =
                oldItem == newItem
        }
    }
}
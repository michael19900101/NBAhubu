
package com.aotuman.nbahubu.ui.headline

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.aotuman.nbahubu.R
import com.aotuman.nbahubu.model.headline.HeadLineNewsItemModel
import com.aotuman.nbahubu.utils.TimeUtil
import com.drakeet.multitype.ViewHolderInflater

class HeadLineHolderInflater : ViewHolderInflater<HeadLineNewsItemModel, HeadLineHolderInflater.ViewHolder>() {

  override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
    return ViewHolder(inflater.inflate(R.layout.item_headline_news, parent, false))
  }

  override fun onBindViewHolder(holder: ViewHolder, item: HeadLineNewsItemModel) {
    holder.title.text = item.title?:""
    holder.tvUpNum.text = item.likeNum.toString()
    holder.tvDate.text = TimeUtil.formatDisplayTime(item.publishTime,"yyyy-MM-dd HH:mm:ss")
    item.thumbnail?.let {
      holder.imageView.load(it) {
        crossfade(true)
        placeholder(R.drawable.default_atlas)
      }
    }
    holder.itemView.setOnClickListener {
      HeadLineNewsDetailActivity.jumpActivity(it.context, item.newsId.toString())
    }
  }

  class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val title: TextView = itemView.findViewById(R.id.tvTitle)
    val imageView: ImageView = itemView.findViewById(R.id.imageView)
    val tvUpNum: TextView = itemView.findViewById(R.id.tvUpNum)
    val tvDate: TextView = itemView.findViewById(R.id.tvDate)
  }
}

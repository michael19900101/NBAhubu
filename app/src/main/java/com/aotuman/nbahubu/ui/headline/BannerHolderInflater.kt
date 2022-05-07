package com.aotuman.nbahubu.ui.headline

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.aotuman.nbahubu.R
import com.aotuman.nbahubu.model.headline.TopBannerItemModel
import com.drakeet.multitype.ViewHolderInflater
import com.youth.banner.adapter.BannerImageAdapter
import com.youth.banner.holder.BannerImageHolder
import com.youth.banner.indicator.CircleIndicator

class BannerHolderInflater : ViewHolderInflater<BannerInflaterModel, BannerHolderInflater.ViewHolder>() {

  override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
    return ViewHolder(inflater.inflate(R.layout.item_banner, parent, false))
  }

  override fun onBindViewHolder(bannerViewHolder: ViewHolder, item: BannerInflaterModel) {
    val topBanners = item.topBanners
    val urls = mutableListOf<String>()
    topBanners.forEach {
      if (!it.thumb.isNullOrEmpty()) {
        urls.add(it.thumb!!)
      } else {
        urls.add("")
      }
    }
    if (urls.isNullOrEmpty()) return
    bannerViewHolder.bannerLayout?.apply {
      indicator = CircleIndicator(context)
      setAdapter(object : BannerImageAdapter<String>(urls) {
        override fun onBindView(holder: BannerImageHolder, data: String, position: Int, size: Int) {
          holder.imageView.load(data) {
            crossfade(true)
            placeholder(R.mipmap.ic_launcher)
          }

          val originTitle = topBanners[position].title?: ""
          bannerViewHolder.bannerTitle.text = convert(originTitle)
        }
      })
    }
  }

  class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val bannerTitle: TextView = itemView.findViewById(R.id.bannerTitle)
    val bannerLayout: com.youth.banner.Banner<String, BannerImageAdapter<String>> = itemView.findViewById(R.id.bannerLayout)
  }


  /**
   * 这里只记录一个场景，那就是服务器下发的text中包含 “\n”，TextView.setText 后没有识别到，导致无法换行。
  我的理解是text中 "\n"是包含两个字符 "\" 和 "n" ，然后单独的去展示了。
  所以我会扫描一遍字符串，如果遇到这两个字符连在一起的时候，就用
   */
  private fun convert(oriStr: String):String {
    val chars: CharArray = oriStr.toCharArray()
    val stringBuilder: StringBuilder = StringBuilder()
    var i = 0
    while (i <= chars.size - 1) {
      if (i != chars.size - 1 && chars[i] == '\\' && chars[i + 1] == 'n') {
        stringBuilder.append("\n")
        i++
      } else {
        stringBuilder.append(chars[i])
      }
      i++
    }
    return stringBuilder.toString()
  }
}

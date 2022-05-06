/*
 * Copyright (c) 2016-present. Drakeet Xu
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.aotuman.nbahubu.ui.headline

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.aotuman.nbahubu.R
import com.drakeet.multitype.ViewHolderInflater
import com.youth.banner.adapter.BannerImageAdapter
import com.youth.banner.holder.BannerImageHolder
import com.youth.banner.indicator.CircleIndicator

class BannerHolderInflater : ViewHolderInflater<Banner, BannerHolderInflater.ViewHolder>() {

  var imageUrls = listOf(
    "https://img.zcool.cn/community/01b72057a7e0790000018c1bf4fce0.png",
    "https://img.zcool.cn/community/016a2256fb63006ac7257948f83349.jpg",
    "https://img.zcool.cn/community/01233056fb62fe32f875a9447400e1.jpg",
    "https://img.zcool.cn/community/01700557a7f42f0000018c1bd6eb23.jpg"
  )


  override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
    return ViewHolder(inflater.inflate(R.layout.item_banner, parent, false))
  }

  override fun onBindViewHolder(holder: ViewHolder, item: Banner) {
    holder.bannerLayout.apply {
      indicator = CircleIndicator(context)
      setAdapter(object : BannerImageAdapter<String>(imageUrls) {
        override fun onBindView(holder: BannerImageHolder, data: String, position: Int, size: Int) {
          holder.imageView.load(data) {
            crossfade(true)
            placeholder(R.mipmap.ic_launcher)
          }
        }
      })
    }
  }

  class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val bannerLayout: com.youth.banner.Banner<String, BannerImageAdapter<String>> = itemView.findViewById(R.id.bannerLayout)
  }
}

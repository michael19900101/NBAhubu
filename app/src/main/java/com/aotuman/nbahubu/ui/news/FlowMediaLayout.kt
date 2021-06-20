package com.aotuman.nbahubu.ui.news

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import coil.load
import com.aotuman.nbahubu.R
import com.aotuman.nbahubu.data.entity.news.NewsContentItem
import com.aotuman.nbahubu.data.entity.player.PlayerCareerData
import com.aotuman.nbahubu.data.entity.player.PlayerSeasonData
import com.aotuman.nbahubu.model.news.NewsItemModel
import com.aotuman.nbahubu.utils.dp2px
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class FlowMediaLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    fun addViewByModel(itemModel: NewsItemModel) {
        val listType = object : TypeToken<List<NewsContentItem>>() { }.type
        val itemContents = Gson().fromJson<List<NewsContentItem>>(itemModel.content, listType)
        itemContents.forEach {
            if(it.type == "img") {
                val imageView = ImageView(context)
                imageView.scaleType = ImageView.ScaleType.CENTER_CROP
                imageView.load(it.img["imgurl0"]?.imgurl) {
                    crossfade(true)
                    placeholder(R.mipmap.ic_launcher)
                }
                addView(imageView, LayoutParams(LayoutParams.MATCH_PARENT, dp2px(250)))
            }
            if(it.type == "text") {
                val textView = TextView(context)
                textView.text = it.info
                textView.textSize = 16f
                textView.setTextColor(Color.parseColor("#FF11202B"))
                val layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
                layoutParams.topMargin = dp2px(10)
                addView(textView, layoutParams)
            }
        }
    }

    fun addViewBySeasonData(data: PlayerSeasonData?, type: Int) {
        val headDatas: List<String>? = if (type == 1) data?.stats?.head else data?.lastMatches?.head
        val rowDatas: List<List<String>>? = if (type == 1) data?.stats?.rows else data?.lastMatches?.rows
        val headLayout = LinearLayout(context)
        headLayout.orientation = HORIZONTAL
        headLayout.setBackgroundResource(R.drawable.player_stat_table_head)
        val contentLayout = LinearLayout(context)
        contentLayout.orientation = VERTICAL
        val headLayoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        val contentLayoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        val rowLayoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)

        val headTextParams = LayoutParams(dp2px(65), dp2px(40))
        val headTextParams0 = if (type == 1) {
            LayoutParams(dp2px(90), dp2px(40))
        } else {
            LayoutParams(dp2px(125), dp2px(40))
        }
        headTextParams0.leftMargin = dp2px(10)
        headDatas?.let {
            for (i in headDatas.indices){
                val textView = TextView(context)
                textView.text = headDatas[i]
                textView.textSize = 14f
                textView.setTextColor(Color.parseColor("#FF11202B"))
                if (i == 0) {
                    textView.gravity = Gravity.CENTER_VERTICAL
                    headLayout.addView(textView, headTextParams0)
                } else {
                    textView.gravity = Gravity.CENTER
                    headLayout.addView(textView, headTextParams)
                }
            }
        }
        rowDatas?.forEach{
            val rowLayout = LinearLayout(context)
            rowLayout.orientation = HORIZONTAL
            for(i in it.indices){
                val textView = TextView(context)
                textView.text = it[i]
                textView.textSize = 14f
                textView.setTextColor(Color.parseColor("#FF11202B"))
                if (i == 0) {
                    textView.gravity = Gravity.CENTER_VERTICAL
                    rowLayout.addView(textView, headTextParams0)
                } else {
                    textView.gravity = Gravity.CENTER
                    rowLayout.addView(textView, headTextParams)
                }
            }
            contentLayout.addView(rowLayout, rowLayoutParams)
        }
        addView(headLayout, headLayoutParams)
        addView(contentLayout, contentLayoutParams)
    }

    fun addViewByCareerData(data: PlayerCareerData?, type: Int) {
        val headDatas: List<String>? = if (type == 1) data?.reg?.head else data?.playoff?.head
        val rowDatas: List<List<String>>? = if (type == 1) data?.reg?.rows else data?.playoff?.rows
        val headLayout = LinearLayout(context)
        headLayout.orientation = HORIZONTAL
        headLayout.setBackgroundResource(R.drawable.player_stat_table_head)
        val contentLayout = LinearLayout(context)
        contentLayout.orientation = VERTICAL
        val headLayoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        val contentLayoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        val rowLayoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)

        val headTextParams = LayoutParams(dp2px(65), dp2px(40))
        val headTextParams0 =  LayoutParams(dp2px(90), dp2px(40))
        headTextParams0.leftMargin = dp2px(10)
        headDatas?.let {
            for (i in headDatas.indices){
                val textView = TextView(context)
                textView.text = headDatas[i]
                textView.textSize = 14f
                textView.setTextColor(Color.parseColor("#FF11202B"))
                if (i == 0) {
                    textView.gravity = Gravity.CENTER_VERTICAL
                    headLayout.addView(textView, headTextParams0)
                } else {
                    textView.gravity = Gravity.CENTER
                    headLayout.addView(textView, headTextParams)
                }
            }
        }
        rowDatas?.forEach{
            val rowLayout = LinearLayout(context)
            rowLayout.orientation = HORIZONTAL
            for(i in it.indices){
                val textView = TextView(context)
                textView.text = it[i]
                textView.textSize = 14f
                textView.setTextColor(Color.parseColor("#FF11202B"))
                if (i == 0) {
                    textView.gravity = Gravity.CENTER_VERTICAL
                    rowLayout.addView(textView, headTextParams0)
                } else {
                    textView.gravity = Gravity.CENTER
                    rowLayout.addView(textView, headTextParams)
                }
            }
            contentLayout.addView(rowLayout, rowLayoutParams)
        }
        addView(headLayout, headLayoutParams)
        addView(contentLayout, contentLayoutParams)
    }
}
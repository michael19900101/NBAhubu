package com.aotuman.nbahubu.ui.news

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import coil.load
import com.aotuman.nbahubu.R
import com.aotuman.nbahubu.data.entity.news.NewsContentItem
import com.aotuman.nbahubu.model.news.NewsItemModel
import com.aotuman.nbahubu.utils.dp2px
import com.aotuman.nbahubu.utils.sp2px
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
}
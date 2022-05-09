package com.aotuman.nbahubu.ui.headline

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import coil.load
import com.aotuman.nbahubu.R
import com.aotuman.nbahubu.model.headline.HeadLineNewsDetailImgAttr
import com.aotuman.nbahubu.model.headline.HeadLineNewsDetailModel
import com.aotuman.nbahubu.model.headline.HeadLineNewsDetailTxtAttr
import com.aotuman.nbahubu.utils.dp2px
import com.google.gson.Gson

class HeadLineNewsFlowMediaLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    fun addViewByModel(itemModel: HeadLineNewsDetailModel) {
        val itemContent = itemModel.attributes
        itemContent?.forEach {
            if(it.itype == "1") {
                val textAttr = Gson().fromJson(it.objectStr, HeadLineNewsDetailTxtAttr::class.java)
                val textView = TextView(context)
                textView.text = textAttr.content
                textView.textSize = 16f
                textView.setTextColor(Color.WHITE)
                val layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
                layoutParams.topMargin = dp2px(10)
                addView(textView, layoutParams)
            }
            if(it.itype == "2") {
                val imgAttr = Gson().fromJson(it.objectStr, HeadLineNewsDetailImgAttr::class.java)
                val imageView = ImageView(context)
                imageView.scaleType = ImageView.ScaleType.CENTER_CROP
                imageView.load(imgAttr.imgUrl) {
                    crossfade(true)
                    placeholder(R.mipmap.ic_launcher)
                }
                val layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, dp2px(250))
                layoutParams.topMargin = dp2px(10)
                addView(imageView, layoutParams)
            }
            if(it.itype == "3") {

            }
        }
    }
}
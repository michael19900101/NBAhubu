package com.aotuman.nbahubu.ui.headline

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.viewModelScope
import coil.load
import com.aotuman.nbahubu.R
import com.aotuman.nbahubu.model.headline.HeadLineNewsDetailImgAttr
import com.aotuman.nbahubu.model.headline.HeadLineNewsDetailModel
import com.aotuman.nbahubu.model.headline.HeadLineNewsDetailTxtAttr
import com.aotuman.nbahubu.model.headline.HeadLineNewsDetailVideoAttr
import com.aotuman.nbahubu.utils.dp2px
import com.google.gson.Gson
import com.shuyu.gsyvideoplayer.utils.OrientationUtils
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HeadLineNewsFlowMediaLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    var orientationUtils: OrientationUtils? = null

    private fun initVideoPlayer(videoPlayer: StandardGSYVideoPlayer) {
        //增加title
        videoPlayer.titleTextView.visibility = GONE
        //设置返回键
        videoPlayer.backButton.visibility = GONE
        //设置旋转
        orientationUtils = OrientationUtils(this.context as Activity?, videoPlayer)
        //设置全屏按键功能,这是使用的是选择屏幕，而不是全屏
        videoPlayer.fullscreenButton
            .setOnClickListener { // ------- ！！！如果不需要旋转屏幕，可以不调用！！！-------
                // 不需要屏幕旋转，还需要设置 setNeedOrientationUtils(false)
                orientationUtils?.resolveByClick()
            }
        //是否可以滑动调整
        videoPlayer.setIsTouchWiget(true)
        //设置返回按键功能
//        videoPlayer.getBackButton().setOnClickListener { onBackPressed() }


        //不需要屏幕旋转
        videoPlayer.isNeedOrientationUtils = false
//        videoPlayer.startPlayLogic()
    }

    fun addViewByModel(itemModel: HeadLineNewsDetailModel, viewModel: HeadLineNewsDetailViewModel, lifecycle: Lifecycle) {
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
                    placeholder(R.drawable.default_atlas)
                }
                val layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, dp2px(250))
                layoutParams.topMargin = dp2px(10)
                addView(imageView, layoutParams)
            }
            if(it.itype == "3") {
                val videoAttr = Gson().fromJson(it.objectStr, HeadLineNewsDetailVideoAttr::class.java)
                val videoPlayer = StandardGSYVideoPlayer(context)
                val layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, dp2px(250))
                layoutParams.topMargin = dp2px(10)
                initVideoPlayer(videoPlayer)
                addView(videoPlayer, layoutParams)
                videoAttr.image?.let { url ->
                    //增加封面
                    val imageView = ImageView(this.context)
                    imageView.scaleType = ImageView.ScaleType.CENTER_CROP
                    imageView.load(url) {
                        crossfade(true)
                        placeholder(R.drawable.default_atlas)
                    }
                    videoPlayer.thumbImageView = imageView
                }
                videoAttr.vid?.let {
                    viewModel.viewModelScope.launch(Dispatchers.IO) {
                        val rsp = viewModel.fetchHeadLineNewsDetailVideo(it)
                        rsp?.let {
                            model ->
                            viewModel.viewModelScope.launch(Dispatchers.Main) {
                                videoPlayer.setUp(model.url, true, model.title)
                            }
                        }
                    }
                }
                lifecycle.addObserver(VideoLifecycleObserver(videoPlayer))
            }
        }
    }
}
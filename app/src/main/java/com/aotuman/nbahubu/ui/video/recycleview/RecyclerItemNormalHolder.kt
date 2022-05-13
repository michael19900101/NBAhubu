package com.aotuman.nbahubu.ui.video.recycleview

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.viewModelScope
import com.aotuman.nbahubu.R
import com.aotuman.nbahubu.model.video.VideoItemModel
import com.aotuman.nbahubu.ui.video.VideoViewModel
import com.aotuman.nbahubu.utils.TimeUtil
import com.shuyu.gsyvideoplayer.GSYVideoManager
import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder
import com.shuyu.gsyvideoplayer.listener.GSYSampleCallBack
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RecyclerItemNormalHolder(var context: Context,
                               v: View,
                               var videoViewModel: VideoViewModel
) : RecyclerItemBaseHolder(v) {

    var player: SampleCoverVideo = v.findViewById(R.id.video_item_player)
    var tvTitle: TextView = v.findViewById(R.id.tvTitle)
    var tvDate: TextView = v.findViewById(R.id.tvDate)
    var tvUpNum: TextView = v.findViewById(R.id.tvUpNum)
    var imageView: ImageView = ImageView(context)

    var gsyVideoOptionBuilder: GSYVideoOptionBuilder = GSYVideoOptionBuilder()

    fun onBind(position: Int, videoItemModel: VideoItemModel) {
        tvTitle.text = videoItemModel.title
        tvDate.text = TimeUtil.formatDisplayTime(videoItemModel.publishTime,"yyyy-MM-dd HH:mm:ss")
        tvUpNum.text = videoItemModel.likeNum.toString()

        initPlayer(position, videoItemModel)

        player.startButton.setOnClickListener {
            if (videoItemModel.videoUrl.isNullOrEmpty()) {
                videoItemModel.vid?.let { vid ->
                    videoViewModel.viewModelScope.launch(Dispatchers.IO) {
                        val rsp = videoViewModel.fetchVideoDetail(vid)
                        rsp?.url?.let { videoUrl ->
                            videoItemModel.videoUrl = videoUrl
                            videoViewModel.viewModelScope.launch(Dispatchers.Main) {
                                Log.e("jbjb", "videoUrl:${videoUrl}")
                                player.setUp(videoUrl, true, videoItemModel.title)
                                player.startPlayLogic()
                            }
                        }
                    }
                }
            } else {
                Log.e("jbjb", "not empty")
            }
        }
    }

    /**
     * 全屏幕按键处理
     */
    private fun resolveFullBtn(standardGSYVideoPlayer: StandardGSYVideoPlayer) {
        standardGSYVideoPlayer.startWindowFullscreen(context, true, true)
    }

    companion object {
        const val TAG = "RecyclerView2List"
    }

    private fun initPlayer(position: Int, videoItemModel: VideoItemModel) {
        val header: MutableMap<String, String> = HashMap()
        header["ee"] = "33"

        //防止错位，离开释放
        //gsyVideoPlayer.initUIState();

        gsyVideoOptionBuilder
            .setIsTouchWiget(false) //.setThumbImageView(imageView)
//            .setUrl(videoItemModel.videoUrl)
            .setVideoTitle(videoItemModel.title)
            .setCacheWithPlay(false)
            .setRotateViewAuto(true)
            .setLockLand(true)
            .setPlayTag(TAG)
            .setMapHeadData(header)
            .setShowFullAnimation(true)
            .setNeedLockFull(true)
            .setPlayPosition(position)
            .setVideoAllCallBack(object : GSYSampleCallBack() {
                override fun onPrepared(url: String, vararg objects: Any) {
                    super.onPrepared(url, *objects)
//                    if (!player.isIfCurrentIsFullscreen) {
                        //静音
                        GSYVideoManager.instance().isNeedMute = true
//                    }
                }

                override fun onQuitFullscreen(url: String, vararg objects: Any) {
                    super.onQuitFullscreen(url, *objects)
                    //全屏不静音
                    GSYVideoManager.instance().isNeedMute = true
                }

                override fun onEnterFullscreen(url: String, vararg objects: Any) {
                    super.onEnterFullscreen(url, *objects)
                    GSYVideoManager.instance().isNeedMute = false
                    player.currentPlayer.titleTextView.text = objects[0] as String
                }
            }).build(player)

        //增加title
        player.titleTextView.visibility = View.GONE

        //设置返回键
        player.backButton.visibility = View.GONE

        //设置全屏按键功能
        player.fullscreenButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                resolveFullBtn(player)
            }
        })
        //设置封面
        player.loadCoverImage(videoItemModel.thumbnail, R.drawable.default_atlas)
    }

}
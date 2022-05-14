package com.aotuman.nbahubu.ui.headline

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.shuyu.gsyvideoplayer.GSYVideoManager
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer

class VideoLifecycleObserver(player: StandardGSYVideoPlayer) : DefaultLifecycleObserver {

    private var videoPlayer: StandardGSYVideoPlayer = player

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        videoPlayer.onVideoResume()
    }

    override fun onPause(owner: LifecycleOwner) {
        super.onPause(owner)
        videoPlayer.onVideoPause()
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        GSYVideoManager.releaseAllVideos()
        videoPlayer.setVideoAllCallBack(null)
    }

}
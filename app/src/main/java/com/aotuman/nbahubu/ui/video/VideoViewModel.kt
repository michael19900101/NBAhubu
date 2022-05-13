package com.aotuman.nbahubu.ui.video

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.aotuman.nbahubu.data.repository.video.VideoRepository
import com.aotuman.nbahubu.model.video.VideoDetailModel
import com.aotuman.nbahubu.model.video.VideoItemModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

/**
 * <pre>
 *     author: aotuman
 *     date  : 2022/05/13
 *     desc  :
 * </pre>
 */
@FlowPreview
@ExperimentalCoroutinesApi
class VideoViewModel @ViewModelInject constructor(
    private val videoRepository: VideoRepository
) : ViewModel() {

    suspend fun fetchVideosData(columnID: Int, pageNum: Int,lastTime: String): List<VideoItemModel>? =
        videoRepository.fetchVideosData(columnID, pageNum, lastTime)

    suspend fun fetchVideoDetail(videoID: String): VideoDetailModel? =
        videoRepository.fetchVideoDetail(videoID)

}
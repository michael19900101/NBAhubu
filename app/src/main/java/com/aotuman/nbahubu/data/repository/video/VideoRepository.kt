package com.aotuman.nbahubu.data.repository.video

import com.aotuman.nbahubu.data.entity.video.VideoDetailResponse
import com.aotuman.nbahubu.model.video.VideoDetailModel
import com.aotuman.nbahubu.model.video.VideoItemModel

/**
 * <pre>
 *     author: aotuman
 *     date  : 2022/05/13
 *     desc  :
 * </pre>
 */
interface VideoRepository {

    suspend fun fetchVideosData(columnID: Int, pageNum: Int,lastTime: String): List<VideoItemModel>?

    suspend fun fetchVideoDetail(videoID: String): VideoDetailModel?
}
package com.aotuman.nbahubu.data.repository.video

import com.aotuman.nbahubu.data.remote.video.VideoService
import com.aotuman.nbahubu.model.video.VideoDetailModel
import com.aotuman.nbahubu.model.video.VideoItemModel

/**
 * <pre>
 *     author: aotuman
 *     date  : 2022/05/13
 *     desc  :
 * </pre>
 */
class VideoRepositoryImpl (
    val api: VideoService
) : VideoRepository {

    override suspend fun fetchVideosData(columnID: Int, pageNum: Int, lastTime: String): List<VideoItemModel>? {
        val response = api.fetchVideos(columnID, pageNum, lastTime)
        val videos = response.data?.map {
            VideoItemModel(
                id = it.id,
                newsId = it.newsId,
                title = it.title,
                thumbnail = it.thumbnail,
                thumbnail2x = it.thumbnail_2x,
                publishTime = it.publish_time,
                likeNum = it.like_num,
                lockAt = it.lock_at,
                atype = it.atype,
                vid = it.vid
            )
        }
        return videos
    }

    override suspend fun fetchVideoDetail(videoID: String): VideoDetailModel? {
        val response = api.fetchVideoDetail(videoID)
        response.data?.let {
            return VideoDetailModel(
                url = it.url,
                vid = it.vid,
                title = it.title
            )
        }
        return null
    }
}
package com.aotuman.nbahubu.data

import com.aotuman.nbahubu.data.remote.video.VideoService
import com.aotuman.nbahubu.data.repository.video.VideoRepository
import com.aotuman.nbahubu.data.repository.video.VideoRepositoryImpl

/**
 * <pre>
 *     author: aotuman
 *     date  : 2022/05/13
 *     desc  :
 * </pre>
 */
object VideoFactory {

    fun makeVideoRepository(api: VideoService): VideoRepository =
        VideoRepositoryImpl(api)

}
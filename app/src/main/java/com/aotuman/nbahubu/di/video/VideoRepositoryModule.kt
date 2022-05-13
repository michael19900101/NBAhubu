package com.aotuman.nbahubu.di.video

import com.aotuman.nbahubu.data.VideoFactory
import com.aotuman.nbahubu.data.remote.video.VideoService
import com.aotuman.nbahubu.data.repository.video.VideoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

/**
 * <pre>
 *     author: aotuman
 *     date  : 2022/05/13
 *     desc  :
 * </pre>
 */
@Module
@InstallIn(ApplicationComponent::class)
class VideoRepositoryModule {

    @Singleton
    @Provides
    fun provideTasksRepository(
        api: VideoService
    ): VideoRepository {
        return VideoFactory.makeVideoRepository(api)
    }
}
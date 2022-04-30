package com.aotuman.nbahubu.di.headline

import com.aotuman.nbahubu.data.HeadLineFactory
import com.aotuman.nbahubu.data.remote.headline.HeadLineService
import com.aotuman.nbahubu.data.repository.headline.HeadLineRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

/**
 * <pre>
 *     author: aotuman
 *     date  : 2021/05/31
 *     desc  :
 * </pre>
 */
@Module
@InstallIn(ApplicationComponent::class)
class HeadLineRepositoryModule {

    @Singleton
    @Provides
    fun provideTasksRepository(
        api: HeadLineService
    ): HeadLineRepository {
        return HeadLineFactory.makeHeadLineRepository(api)
    }
}
package com.aotuman.nbahubu.di.news

import com.aotuman.nbahubu.data.NewsFactory
import com.aotuman.nbahubu.data.local.AppDataBase
import com.aotuman.nbahubu.data.remote.news.NewsCommentService
import com.aotuman.nbahubu.data.remote.news.NewsService
import com.aotuman.nbahubu.data.remote.temp.NewsServiceTemp
import com.aotuman.nbahubu.data.repository.news.NewsCommentRepository
import com.aotuman.nbahubu.data.repository.news.NewsRepository
import com.aotuman.nbahubu.data.repository.temp.NewsRepositoryTemp
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
class NewsRepositoryModule {

    @Singleton
    @Provides
    fun provideTasksRepository(
        api: NewsService,
        db: AppDataBase
    ): NewsRepository {
        return NewsFactory.makeNewsRepository(api, db)
    }

    @Singleton
    @Provides
    fun provideTasksRepository1(
        api: NewsCommentService
    ): NewsCommentRepository {
        return NewsFactory.makeNewsCommentRepository(api)
    }

    @Singleton
    @Provides
    fun provideTasksRepository2(
        api: NewsServiceTemp
    ): NewsRepositoryTemp {
        return NewsFactory.makeNewsRepositoryTemp(api)
    }
}
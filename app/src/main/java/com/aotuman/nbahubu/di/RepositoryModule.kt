package com.aotuman.nbahubu.di

import com.aotuman.nbahubu.data.PlayerFactory
import com.aotuman.nbahubu.data.local.AppDataBase
import com.aotuman.nbahubu.data.remote.PlayerService
import com.aotuman.nbahubu.data.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

/**
 * <pre>
 *     author: aotuman
 *     date  : 2021/05/11
 *     desc  :
 * </pre>
 */
@Module
@InstallIn(ApplicationComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideTasksRepository(
            api: PlayerService,
            db: AppDataBase
    ): Repository {
        return PlayerFactory.makePlayerRepository(api, db)
    }

}
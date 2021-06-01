package com.aotuman.nbahubu.di.player

import com.aotuman.nbahubu.data.PlayerFactory
import com.aotuman.nbahubu.data.local.AppDataBase
import com.aotuman.nbahubu.data.remote.player.PlayerService
import com.aotuman.nbahubu.data.repository.player.PlayerRepository
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
class PlayerRepositoryModule {

    @Singleton
    @Provides
    fun provideTasksRepository(
        api: PlayerService,
        db: AppDataBase
    ): PlayerRepository {
        return PlayerFactory.makePlayerRepository(api, db)
    }

}
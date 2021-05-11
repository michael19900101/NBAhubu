package com.aotuman.nbahubu.data

import androidx.paging.PagingConfig
import com.aotuman.nbahubu.data.local.AppDataBase
import com.aotuman.nbahubu.data.remote.PlayerService
import com.aotuman.nbahubu.data.repository.PlayerRepositoryImpl
import com.aotuman.nbahubu.data.repository.Repository
import com.aotuman.nbahubu.data.mapper.Entity2ItemModelMapper
import com.aotuman.nbahubu.data.mapper.InfoEntity2InfoModelMapper

/**
 * <pre>
 *     author: aotuman
 *     date  : 2021/05/11
 *     desc  :
 * </pre>
 */
object PlayerFactory {

    fun makePlayerRepository(api: PlayerService, db: AppDataBase): Repository =
        PlayerRepositoryImpl(
            api,
            db,
            pagingConfig,
            Entity2ItemModelMapper(),
            InfoEntity2InfoModelMapper()
        )

    val pagingConfig = PagingConfig(
        // 每页显示的数据的大小
        pageSize = 30,

        // 开启占位符
        enablePlaceholders = true,

        // 预刷新的距离，距离最后一个 item 多远时加载数据
        // 默认为 pageSize
        prefetchDistance = 4,

        /**
         * 初始化加载数量，默认为 pageSize * 3
         *
         * internal const val DEFAULT_INITIAL_PAGE_MULTIPLIER = 3
         * val initialLoadSize: Int = pageSize * DEFAULT_INITIAL_PAGE_MULTIPLIER
         */
        initialLoadSize = 30
    )
}
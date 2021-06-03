package com.aotuman.nbahubu.data

import androidx.paging.PagingConfig
import com.aotuman.nbahubu.data.local.AppDataBase
import com.aotuman.nbahubu.data.mapper.news.Entity2ItemModelMapper
import com.aotuman.nbahubu.data.remote.news.NewsService
import com.aotuman.nbahubu.data.repository.news.NewsRepository
import com.aotuman.nbahubu.data.repository.news.NewsRepositoryImpl

/**
 * <pre>
 *     author: aotuman
 *     date  : 2021/05/31
 *     desc  :
 * </pre>
 */
object NewsFactory {

    fun makePlayerRepository(api: NewsService, db: AppDataBase): NewsRepository =
        NewsRepositoryImpl(
            api,
            db,
            pagingConfig,
            Entity2ItemModelMapper()
        )

    val pagingConfig = PagingConfig(
        // 每页显示的数据的大小
        pageSize = 20,

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

        /**
         * 初始化加载数量，默认为 pageSize * 3
         *
         * internal const val DEFAULT_INITIAL_PAGE_MULTIPLIER = 3
         * val initialLoadSize: Int = pageSize * DEFAULT_INITIAL_PAGE_MULTIPLIER
         */
        initialLoadSize = 20
    )
}
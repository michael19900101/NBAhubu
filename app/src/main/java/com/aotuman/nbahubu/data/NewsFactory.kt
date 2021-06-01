package com.aotuman.nbahubu.data

import com.aotuman.nbahubu.data.local.AppDataBase
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
            db
        )

}
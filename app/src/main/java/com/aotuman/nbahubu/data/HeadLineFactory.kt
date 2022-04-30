package com.aotuman.nbahubu.data

import com.aotuman.nbahubu.data.remote.headline.HeadLineService
import com.aotuman.nbahubu.data.remote.temp.NewsServiceTemp
import com.aotuman.nbahubu.data.repository.headline.HeadLineRepository
import com.aotuman.nbahubu.data.repository.headline.HeadLineRepositoryImpl
import com.aotuman.nbahubu.data.repository.temp.NewsRepositoryImplTemp
import com.aotuman.nbahubu.data.repository.temp.NewsRepositoryTemp

/**
 * <pre>
 *     author: aotuman
 *     date  : 2022/04/30
 *     desc  :
 * </pre>
 */
object HeadLineFactory {

    fun makeHeadLineRepository(api: HeadLineService): HeadLineRepository =
        HeadLineRepositoryImpl(api)

}
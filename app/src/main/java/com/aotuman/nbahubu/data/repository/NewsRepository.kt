package com.aotuman.nbahubu.data.repository

import com.aotuman.nbahubu.data.entity.NewsID
import kotlinx.coroutines.flow.Flow

/**
 * <pre>
 *     author: aotuman
 *     date  : 2021/05/31
 *     desc  :
 * </pre>
 */
interface NewsRepository {

    fun fetchNewsID(): Flow<List<NewsID>>
}
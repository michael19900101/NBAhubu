package com.aotuman.nbahubu.data.repository.headline

import kotlinx.coroutines.flow.Flow

/**
 * <pre>
 *     author: aotuman
 *     date  : 2022/04/30
 *     desc  :
 * </pre>
 */
interface HeadLineRepository {

    fun fetchNews(): Flow<Int>
}
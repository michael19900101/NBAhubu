package com.aotuman.nbahubu.data.repository.temp

import kotlinx.coroutines.flow.Flow

/**
 * <pre>
 *     author: aotuman
 *     date  : 2022/04/29
 *     desc  :
 * </pre>
 */
interface NewsRepositoryTemp {

    fun fetchNews(): Flow<Int>
}
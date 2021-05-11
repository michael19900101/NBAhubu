package com.aotuman.nbahubu.data.mapper

/**
 * <pre>
 *     author: aotuman
 *     date  : 2021/05/11
 *     desc  :
 * </pre>
 */
interface Mapper<I, O> {
    fun map(input: I): O
}
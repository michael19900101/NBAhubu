package com.aotuman.nbahubu.data.local.news

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import com.aotuman.nbahubu.data.entity.news.NewsEntity

/**
 * <pre>
 *     author: aotuman
 *     date  : 2021/06/03
 *     desc  :
 * </pre>
 */
@Dao
interface NewsDao {

    @Query("SELECT * FROM NewsEntity")
    fun getNews(): PagingSource<Int, NewsEntity>
}
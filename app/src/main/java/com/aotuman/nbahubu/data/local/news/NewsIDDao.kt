package com.aotuman.nbahubu.data.local.news

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aotuman.nbahubu.data.entity.news.NewsEntity
import com.aotuman.nbahubu.data.entity.news.NewsIDEntity

/**
 * <pre>
 *     author: aotuman
 *     date  : 2021/06/06
 *     desc  :
 * </pre>
 */
@Dao
interface NewsIDDao {

    @Query("SELECT * FROM NewsIDEntity")
    fun getNews(): PagingSource<Int, NewsIDEntity>

    @Query("SELECT COUNT(*) FROM NewsIDEntity")
    fun countNews(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewsID(news: List<NewsIDEntity>)

    @Query("SELECT max(id) FROM NewsIDEntity")
    fun queryMaxNewsID(): Long

    @Query("SELECT * FROM NewsIDEntity WHERE id < :startNewsID ORDER BY id DESC LIMIT :limit")
    fun queryNextPageNews(startNewsID: Long, limit: Int): List<NewsIDEntity>?
}
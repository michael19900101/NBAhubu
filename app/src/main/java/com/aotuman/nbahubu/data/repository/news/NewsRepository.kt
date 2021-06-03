package com.aotuman.nbahubu.data.repository.news

import androidx.paging.PagingData
import com.aotuman.nbahubu.data.entity.news.NewsID
import com.aotuman.nbahubu.model.news.NewsItemModel
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

    fun fetchNews(ids: List<NewsID>): Flow<PagingData<NewsItemModel>>
}
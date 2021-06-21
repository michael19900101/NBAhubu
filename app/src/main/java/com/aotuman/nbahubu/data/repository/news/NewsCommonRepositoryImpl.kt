package com.aotuman.nbahubu.data.repository.news

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.aotuman.nbahubu.data.entity.news.NewsEntity
import com.aotuman.nbahubu.data.entity.news.NewsID
import com.aotuman.nbahubu.data.local.AppDataBase
import com.aotuman.nbahubu.data.mapper.Mapper
import com.aotuman.nbahubu.data.remote.news.NewsCommentResponse
import com.aotuman.nbahubu.data.remote.news.NewsCommentService
import com.aotuman.nbahubu.data.remote.news.NewsService
import com.aotuman.nbahubu.model.news.NewsItemModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

/**
 * <pre>
 *     author: aotuman
 *     date  : 2021/06/21
 *     desc  :
 * </pre>
 */

class NewsCommonRepositoryImpl(
    val api: NewsCommentService,
) : NewsCommentRepository {
    override fun fetchComments(articleId: String): Flow<NewsCommentResponse.Data> {
        return flow {
            val response = api.fetchCommentList(articleId,articleId,0,1,20,2)
            emit(response.data)
        }.flowOn(Dispatchers.IO)

    }


}
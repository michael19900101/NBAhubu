package com.aotuman.nbahubu.data.repository.news

import com.aotuman.nbahubu.data.remote.news.NewsCommentResponse
import com.aotuman.nbahubu.data.remote.news.NewsCommentService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

/**
 * <pre>
 *     author: aotuman
 *     date  : 2021/06/21
 *     desc  :
 * </pre>
 */

class NewsCommentRepositoryImpl(
    val api: NewsCommentService,
) : NewsCommentRepository {

    override fun fetchComments(articleId: Long): Flow<NewsCommentResponse.Data> {
        return flow {
            val response = api.fetchCommentList(articleId,articleId,0,1,20,2)
            emit(response.data)
        }.flowOn(Dispatchers.IO)

    }


}
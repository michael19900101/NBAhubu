package com.aotuman.nbahubu.data.repository.news

import com.aotuman.nbahubu.data.remote.news.NewsCommentResponse
import kotlinx.coroutines.flow.Flow

/**
 * <pre>
 *     author: aotuman
 *     date  : 2021/06/21
 *     desc  :
 * </pre>
 */
interface NewsCommentRepository {

    fun fetchComments(articleId: String): Flow<NewsCommentResponse.Data>
}
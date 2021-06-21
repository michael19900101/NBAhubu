package com.aotuman.nbahubu.ui.news

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.aotuman.nbahubu.data.entity.news.NewsID
import com.aotuman.nbahubu.data.remote.news.NewsCommentResponse
import com.aotuman.nbahubu.data.repository.news.NewsCommentRepository
import com.aotuman.nbahubu.data.repository.news.NewsRepository
import com.aotuman.nbahubu.model.news.NewsItemModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow

/**
 * <pre>
 *     author: aotuman
 *     date  : 2021/06/21
 *     desc  :
 * </pre>
 */
@FlowPreview
@ExperimentalCoroutinesApi
class NewsDetailViewModel @ViewModelInject constructor(
    private val newsRepository: NewsCommentRepository
) : ViewModel() {

    fun fetchComments(articleId: String): LiveData<NewsCommentResponse.Data> =
        newsRepository.fetchComments(articleId).asLiveData()
}
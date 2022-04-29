package com.aotuman.nbahubu.ui.temp

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.aotuman.nbahubu.data.entity.news.NewsID
import com.aotuman.nbahubu.data.repository.news.NewsRepository
import com.aotuman.nbahubu.data.repository.temp.NewsRepositoryTemp
import com.aotuman.nbahubu.model.news.NewsItemModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

/**
 * <pre>
 *     author: aotuman
 *     date  : 2022/04/29
 *     desc  :
 * </pre>
 */
@FlowPreview
@ExperimentalCoroutinesApi
class NewsViewModelTemp @ViewModelInject constructor(
    private val newsRepository: NewsRepositoryTemp
) : ViewModel() {

    fun fetchNews(): LiveData<Int> =
        newsRepository.fetchNews().asLiveData()

}
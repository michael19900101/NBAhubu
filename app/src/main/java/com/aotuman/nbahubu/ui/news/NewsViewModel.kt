package com.aotuman.nbahubu.ui.news

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.aotuman.nbahubu.data.entity.NewsID
import com.aotuman.nbahubu.data.repository.news.NewsRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

/**
 * <pre>
 *     author: aotuman
 *     date  : 2021/06/01
 *     desc  :
 * </pre>
 */
@FlowPreview
@ExperimentalCoroutinesApi
class NewsViewModel @ViewModelInject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {

    fun fetchNewsIDs(): LiveData<List<NewsID>> =
        newsRepository.fetchNewsID().asLiveData()
}
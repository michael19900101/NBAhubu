package com.aotuman.nbahubu.ui.headline

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.aotuman.nbahubu.data.repository.headline.HeadLineRepository
import com.aotuman.nbahubu.model.headline.HeadLineNewsItemModel
import com.aotuman.nbahubu.model.headline.TopBannerItemModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow

/**
 * <pre>
 *     author: aotuman
 *     date  : 2022/04/30
 *     desc  :
 * </pre>
 */
@FlowPreview
@ExperimentalCoroutinesApi
class HeadLineViewModel @ViewModelInject constructor(
    private val headLineRepository: HeadLineRepository
) : ViewModel() {

    fun fetchHeadLineNews(): LiveData<List<HeadLineNewsItemModel>?> =
        headLineRepository.fetchHeadLineNews().asLiveData()

    fun fetchTopBanner(): LiveData<List<TopBannerItemModel>?> =
        headLineRepository.fetchTopBanner().asLiveData()
}
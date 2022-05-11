package com.aotuman.nbahubu.ui.headline

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.aotuman.nbahubu.data.repository.headline.HeadLineRepository
import com.aotuman.nbahubu.model.headline.HeadLineNewsDetailModel
import com.aotuman.nbahubu.model.headline.HeadLineNewsDetailVideoModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

/**
 * <pre>
 *     author: aotuman
 *     date  : 2022/05/09
 *     desc  :
 * </pre>
 */
@FlowPreview
@ExperimentalCoroutinesApi
class HeadLineNewsDetailViewModel  @ViewModelInject constructor(
    private val headLineRepository: HeadLineRepository
) : ViewModel() {

    suspend fun fetchNewsDetailData(newsID: String): HeadLineNewsDetailModel? {
        return headLineRepository.fetchNewsDetailData(newsID)
    }

    suspend fun fetchHeadLineNewsDetailVideo(videoID: String): HeadLineNewsDetailVideoModel? {
        return headLineRepository.fetchHeadLineNewsDetailVideo(videoID)
    }

}
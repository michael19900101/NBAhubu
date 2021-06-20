package com.aotuman.nbahubu.ui.player

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.aotuman.nbahubu.data.entity.player.Player
import com.aotuman.nbahubu.data.entity.player.PlayerCareerData
import com.aotuman.nbahubu.data.entity.player.PlayerDetail
import com.aotuman.nbahubu.data.entity.player.PlayerSeasonData
import com.aotuman.nbahubu.data.repository.player.PlayerRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

/**
 * <pre>
 *     author: aotuman
 *     date  : 2021/06/16
 *     desc  :
 * </pre>
 */
@FlowPreview
@ExperimentalCoroutinesApi
class PlayerDetailViewModel @ViewModelInject constructor(
    private val playerRepository: PlayerRepository
) : ViewModel() {

    fun fetchPlayerDetail(playerId: String): LiveData<PlayerDetail> =
        playerRepository.fetchPlayerDetail(playerId).asLiveData()

    fun fetchPlayerSeasonData(playerId: String): LiveData<PlayerSeasonData> =
        playerRepository.fetchPlayerSeasonData(playerId).asLiveData()

    fun fetchPlayerCareerData(playerId: String): LiveData<PlayerCareerData> =
        playerRepository.fetchPlayerCareerData(playerId).asLiveData()
}
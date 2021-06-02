package com.aotuman.nbahubu.ui.player

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.aotuman.nbahubu.data.entity.player.Player
import com.aotuman.nbahubu.data.repository.player.PlayerRepository
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
class PlayerViewModel  @ViewModelInject constructor(
    private val playerRepository: PlayerRepository
) : ViewModel() {

    fun fetchPlayers(): LiveData<List<Player>> =
        playerRepository.fetchPlayerList1().asLiveData()
}
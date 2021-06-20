package com.aotuman.nbahubu.ui.player

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.aotuman.nbahubu.R
import com.aotuman.nbahubu.databinding.FragmentPlayerSeasonBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

/**
 * <pre>
 *     author: aotuman
 *     date  : 2021/06/20
 *     desc  :
 * </pre>
 */

@FlowPreview
@ExperimentalCoroutinesApi
@AndroidEntryPoint
class PlayerSeasonFragment: Fragment(R.layout.fragment_player_season)  {

    private var fragmentBinding: FragmentPlayerSeasonBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentBinding = FragmentPlayerSeasonBinding.inflate(inflater, container, false)
        return fragmentBinding?.root
    }

}
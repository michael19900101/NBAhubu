package com.aotuman.nbahubu.ui.player

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.aotuman.nbahubu.R
import com.aotuman.nbahubu.databinding.FragmentPlayerSeasonBinding
import com.aotuman.nbahubu.ui.news.FlowMediaLayout
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
    var dataAverageLayout: FlowMediaLayout? = null
    var dataLatest5Layout: FlowMediaLayout? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentBinding = FragmentPlayerSeasonBinding.inflate(inflater, container, false)
        dataAverageLayout = fragmentBinding?.dataAverageLayout
        dataLatest5Layout = fragmentBinding?.dataLatest5Layout
        return fragmentBinding?.root
    }

    override fun onDestroyView() {
        // Fragment 的存在时间比其视图长。请务必在 Fragment 的 onDestroyView() 方法中清除对绑定类实例的所有引用。
        fragmentBinding = null
        super.onDestroyView()
    }


}
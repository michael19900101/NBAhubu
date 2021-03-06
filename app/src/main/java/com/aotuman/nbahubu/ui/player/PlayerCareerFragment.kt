package com.aotuman.nbahubu.ui.player

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.aotuman.nbahubu.R
import com.aotuman.nbahubu.databinding.FragmentPlayerCareerBinding
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
class PlayerCareerFragment: Fragment(R.layout.fragment_player_career)  {

    private var fragmentBinding: FragmentPlayerCareerBinding? = null
    var dataRegularLayout: FlowMediaLayout? = null
    var dataPlayOff5Layout: FlowMediaLayout? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentBinding = FragmentPlayerCareerBinding.inflate(inflater, container, false)
        dataRegularLayout = fragmentBinding?.dataRegularLayout
        dataPlayOff5Layout = fragmentBinding?.dataPlayOff5Layout
        return fragmentBinding?.root
    }

    override fun onDestroyView() {
        // Fragment 的存在时间比其视图长。请务必在 Fragment 的 onDestroyView() 方法中清除对绑定类实例的所有引用。
        fragmentBinding = null
        super.onDestroyView()
    }
}
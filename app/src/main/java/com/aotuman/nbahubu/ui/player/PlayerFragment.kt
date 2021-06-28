package com.aotuman.nbahubu.ui.player

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.aotuman.nbahubu.R
import com.aotuman.nbahubu.databinding.FragmentPlayerBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@FlowPreview
@ExperimentalCoroutinesApi
@AndroidEntryPoint
class PlayerFragment : Fragment(R.layout.fragment_player) {

    private var fragmentPlayerBinding: FragmentPlayerBinding? = null
    private val viewModel: PlayerViewModel by viewModels()
    private val playerAdapter by lazy { PlayerAdapter(this, arrayListOf()) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentPlayerBinding = FragmentPlayerBinding.inflate(inflater, container, false)
        return fragmentPlayerBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentPlayerBinding?.apply {
            recyleView.adapter = playerAdapter
        }
        viewModel.fetchPlayers().observe(viewLifecycleOwner, Observer { players ->
            players?.let {
                playerAdapter.addData(it)
                playerAdapter.notifyDataSetChanged()
            }
            fragmentPlayerBinding?.swiperRefresh?.isEnabled = false
        })
    }

    override fun onDestroyView() {
        // Fragment 的存在时间比其视图长。请务必在 Fragment 的 onDestroyView() 方法中清除对绑定类实例的所有引用。
        fragmentPlayerBinding = null
        super.onDestroyView()
    }

}
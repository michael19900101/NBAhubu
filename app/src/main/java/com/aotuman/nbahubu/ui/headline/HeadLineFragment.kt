package com.aotuman.nbahubu.ui.headline

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.aotuman.nbahubu.R
import com.aotuman.nbahubu.databinding.FragmentHeadlineBinding
import com.aotuman.nbahubu.databinding.FragmentNewsBinding
import com.aotuman.nbahubu.ui.temp.NewsViewModelTemp
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@FlowPreview
@ExperimentalCoroutinesApi
@AndroidEntryPoint
class HeadLineFragment : Fragment(R.layout.fragment_headline) {

    private var fragmentNewsBinding: FragmentHeadlineBinding? = null
    private val viewModel: HeadLineViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentNewsBinding = FragmentHeadlineBinding.inflate(inflater, container, false)
        return fragmentNewsBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentNewsBinding?.apply {

        }
    }

    override fun onDestroyView() {
        // Fragment 的存在时间比其视图长。请务必在 Fragment 的 onDestroyView() 方法中清除对绑定类实例的所有引用。
        fragmentNewsBinding = null
        super.onDestroyView()
    }

    companion object {
        private val TAG = "HeadLineFragment"
    }

}
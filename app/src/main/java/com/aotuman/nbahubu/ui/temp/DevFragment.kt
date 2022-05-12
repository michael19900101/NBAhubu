package com.aotuman.nbahubu.ui.temp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.aotuman.nbahubu.R
import com.aotuman.nbahubu.databinding.FragmentDevBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview


@FlowPreview
@ExperimentalCoroutinesApi
@AndroidEntryPoint
class DevFragment : Fragment(R.layout.fragment_dev) {

    private var fragmentDevBinding: FragmentDevBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentDevBinding = FragmentDevBinding.inflate(inflater, container, false)
        return fragmentDevBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        // Fragment 的存在时间比其视图长。请务必在 Fragment 的 onDestroyView() 方法中清除对绑定类实例的所有引用。
        fragmentDevBinding = null
        super.onDestroyView()
    }

    companion object {
        private val TAG = "DevFragment"
    }

}
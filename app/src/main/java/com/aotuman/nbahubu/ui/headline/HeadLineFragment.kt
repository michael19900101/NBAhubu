package com.aotuman.nbahubu.ui.headline

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.aotuman.nbahubu.R
import com.aotuman.nbahubu.databinding.FragmentHeadlineBinding
import com.drakeet.multitype.MultiTypeAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview


@FlowPreview
@ExperimentalCoroutinesApi
@AndroidEntryPoint
class HeadLineFragment : Fragment(R.layout.fragment_headline) {

    private var fragmentHeadlineBinding: FragmentHeadlineBinding? = null
    private val headLineViewModel: HeadLineViewModel by viewModels()
    private var adapter: MultiTypeAdapter? = null
    private var items: MutableList<Any> = ArrayList()
    private var bannerHolderInflater: BannerHolderInflater ? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        adapter = MultiTypeAdapter()
        bannerHolderInflater = BannerHolderInflater()
        adapter?.register(bannerHolderInflater!!)
        adapter?.register(HeadLineHolderInflater())
        testData()
        fragmentHeadlineBinding = FragmentHeadlineBinding.inflate(inflater, container, false)
        return fragmentHeadlineBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentHeadlineBinding?.apply {
            recyleView.adapter = adapter
        }
        adapter?.items = items
        adapter?.notifyDataSetChanged()
        headLineViewModel.fetchTopBanner().observe(viewLifecycleOwner, Observer {
            Log.e("jbjb","回调fetchTopBanner")
            bannerHolderInflater?.updateViewHolder(it)
        })
    }

    override fun onDestroyView() {
        // Fragment 的存在时间比其视图长。请务必在 Fragment 的 onDestroyView() 方法中清除对绑定类实例的所有引用。
        fragmentHeadlineBinding = null
        super.onDestroyView()
    }

    companion object {
        private val TAG = "HeadLineFragment"
    }

    private fun testData() {
        items.add(Banner("banner title"))
        for (i in 1..9) {
            items.add(HeadLine("headline title[${i}]"))
        }
    }

}
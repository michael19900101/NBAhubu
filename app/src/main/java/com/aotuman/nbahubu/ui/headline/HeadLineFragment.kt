package com.aotuman.nbahubu.ui.headline

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aotuman.nbahubu.R
import com.aotuman.nbahubu.databinding.FragmentHeadlineBinding
import com.aotuman.nbahubu.utils.StatusBarUtil
import com.aotuman.nbahubu.utils.SystemUiUtils
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
    private var headLineHolderInflater: HeadLineHolderInflater ? = null

    var mStatusShadow: View? = null
    var mTopStatusShadow: View? = null
    private var MIN_SCROLL_SHADOW_SHOW = -50
    private var MAX_SCROLL_SHADOW_SHOW = -200

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        adapter = MultiTypeAdapter()
        bannerHolderInflater = BannerHolderInflater()
        headLineHolderInflater = HeadLineHolderInflater()
        adapter?.register(bannerHolderInflater!!)
        adapter?.register(headLineHolderInflater!!)
        testData()
        fragmentHeadlineBinding = FragmentHeadlineBinding.inflate(inflater, container, false)
        return fragmentHeadlineBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentHeadlineBinding?.apply {
            recyleView.adapter = adapter

            // 这一块其实用来设置[滑动列表前]，固定在顶部的(状态栏+magic_indicator)高度
            mTopStatusShadow = statusTopShadowView
            onFirstViewTopChange(0)

            // 设置状态栏阴影高度
            val statusParams = statusBarShadow.layoutParams
            statusParams.height = StatusBarUtil.getStatusBarHeight()
            statusTopShadowView.visibility = View.VISIBLE

            // 这一块其实用来设置[滑动列表后]，固定在顶部的(状态栏+magic_indicator)高度
            mStatusShadow = fixedStatusShadowView
            val statusShadowParams = mStatusShadow?.layoutParams
            statusShadowParams?.height = StatusBarUtil.getStatusBarHeight() + context?.resources?.getDimensionPixelSize(R.dimen.dp_44)!!
            mStatusShadow?.layoutParams = statusShadowParams
            MAX_SCROLL_SHADOW_SHOW = -(SystemUiUtils.getDisplayHeight()) / 4

            recyleView.addOnScrollListener(object : RecyclerView.OnScrollListener() {

                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                }

                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val manager = (recyclerView.layoutManager as LinearLayoutManager)
                    val pos = manager.findFirstVisibleItemPosition()
                    if (pos == 0) {
                        val firstView = manager.findViewByPosition(pos)
                        firstView?.let { first ->
                            onFirstViewTopChange(first.top)
                        }
                    } else {
                        onFirstViewTopChange(-10000)
                    }
                }
            })

        }
        adapter?.items = items
        adapter?.notifyDataSetChanged()
        headLineViewModel.fetchTopBanner().observe(viewLifecycleOwner, Observer {
            Log.e("jbjb","回调fetchTopBanner")
            bannerHolderInflater?.updateViewHolder(it)
        })
        headLineViewModel.fetchHeadLineNews().observe(viewLifecycleOwner, Observer {
            Log.e("jbjb","回调fetchHeadLineNews")
            it?.let {
                items.addAll(it)
                adapter?.notifyDataSetChanged()
            }
        })
    }

    fun onFirstViewTopChange(top: Int) {
        mTopStatusShadow?.visibility = View.VISIBLE
        if (top > MIN_SCROLL_SHADOW_SHOW) {
            mStatusShadow?.visibility = View.GONE
            mTopStatusShadow?.alpha = 1f
        } else {
            mStatusShadow?.visibility = View.VISIBLE
            var alpha = (MIN_SCROLL_SHADOW_SHOW - top.toFloat()) / (MIN_SCROLL_SHADOW_SHOW - MAX_SCROLL_SHADOW_SHOW)
            if (alpha > 1) {
                alpha = 1f
            } else if (alpha < 0) {
                alpha = 0f
            }
            mStatusShadow?.alpha = alpha
            mTopStatusShadow?.alpha = (1 - alpha)
        }
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
    }

}
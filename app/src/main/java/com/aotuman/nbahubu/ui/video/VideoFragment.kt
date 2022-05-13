package com.aotuman.nbahubu.ui.video

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.aotuman.nbahubu.R
import com.aotuman.nbahubu.databinding.FragmentVideoBinding
import com.aotuman.nbahubu.ui.follow.FollowFragment
import com.aotuman.nbahubu.ui.headline.HeadLineFragment
import com.aotuman.nbahubu.ui.latest.ColorFlipPagerTitleView
import com.aotuman.nbahubu.ui.latest.ViewPager2Helper
import com.aotuman.nbahubu.ui.temp.DevFragment
import com.aotuman.nbahubu.utils.StatusBarUtil
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import net.lucode.hackware.magicindicator.buildins.UIUtil
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView


/**
 * <pre>
 *     author: aotuman
 *     date  : 2022/05/13
 *     desc  :
 * </pre>
 */
@FlowPreview
@ExperimentalCoroutinesApi
@AndroidEntryPoint
class VideoFragment : Fragment(R.layout.fragment_video) {

    private var videoBinding: FragmentVideoBinding? = null

    // tab titles
    private val titles = arrayOf("集锦", "最佳", "晚自习")

    companion object {
        private val TAG = "VideoFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        videoBinding = FragmentVideoBinding.inflate(inflater, container, false)
        return videoBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        videoBinding?.apply {
            val headerParams = statusBarView.layoutParams as FrameLayout.LayoutParams
            headerParams.topMargin = StatusBarUtil.getStatusBarHeight()
            statusBarView.layoutParams = headerParams

            viewpager.adapter = ViewPagerFragmentAdapter(titles, this@VideoFragment.activity)
            viewpager.currentItem = 0
            viewpager.isUserInputEnabled = true
            initMagicIndicator(viewpager)
        }
    }

    override fun onDestroyView() {
        // Fragment 的存在时间比其视图长。请务必在 Fragment 的 onDestroyView() 方法中清除对绑定类实例的所有引用。
        videoBinding = null
        super.onDestroyView()
    }

    private fun initMagicIndicator(mViewPager: ViewPager2) {
        val magicIndicator = videoBinding?.magicIndicator
//        magicIndicator?.setBackgroundResource(R.drawable.round_indicator_bg)
        val commonNavigator = CommonNavigator(context)
        commonNavigator.adapter = object : CommonNavigatorAdapter() {
            override fun getCount(): Int {
                return titles.size
            }

            override fun getTitleView(context: Context?, index: Int): IPagerTitleView? {
                val simplePagerTitleView: SimplePagerTitleView = ColorFlipPagerTitleView(context)
                simplePagerTitleView.text = titles[index]
                simplePagerTitleView.normalColor = Color.parseColor("#B2FFFFFF")
                simplePagerTitleView.selectedColor = Color.WHITE
                simplePagerTitleView.textSize = 16f
                simplePagerTitleView.setOnClickListener {
                    mViewPager.currentItem = index
                }
                return simplePagerTitleView
            }

            override fun getIndicator(context: Context): IPagerIndicator? {
                val indicator = LinePagerIndicator(context)
                indicator.mode = LinePagerIndicator.MODE_EXACTLY
                indicator.lineHeight =
                    UIUtil.dip2px(context, 6.0).toFloat()
                indicator.lineWidth =
                    UIUtil.dip2px(context, 10.0).toFloat()
                indicator.roundRadius =
                    UIUtil.dip2px(context, 3.0).toFloat()
                indicator.startInterpolator = AccelerateInterpolator()
                indicator.endInterpolator = DecelerateInterpolator(2.0f)
                indicator.setColors(Color.WHITE)
                return indicator
            }
        }
        magicIndicator?.navigator = commonNavigator
        ViewPager2Helper.bind(magicIndicator, mViewPager)
    }

}

private class ViewPagerFragmentAdapter(titles: Array<String>,
                                       @NonNull fragmentActivity: FragmentActivity?) :
    FragmentStateAdapter(fragmentActivity!!) {

    var titles: Array<String>? = null

    init {
        this.titles = titles
    }

    @NonNull
    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return VideoHighLightFragment()
            1 -> return DevFragment()
            2 -> return DevFragment()
        }
        return VideoHighLightFragment()
    }

    override fun getItemCount(): Int {
        return titles?.size?:0
    }
}
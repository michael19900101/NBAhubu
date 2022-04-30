package com.aotuman.nbahubu.ui.latest

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.aotuman.nbahubu.R
import com.aotuman.nbahubu.databinding.FragmentLatestBinding
import com.aotuman.nbahubu.ui.headline.HeadLineFragment
import com.aotuman.nbahubu.ui.news.NewsFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import net.lucode.hackware.magicindicator.FragmentContainerHelper
import net.lucode.hackware.magicindicator.MagicIndicator
import net.lucode.hackware.magicindicator.buildins.UIUtil
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ClipPagerTitleView


@FlowPreview
@ExperimentalCoroutinesApi
@AndroidEntryPoint
class LatestFragment : Fragment(R.layout.fragment_latest) {

    private var latestBinding: FragmentLatestBinding? = null

    // tab titles
    private val titles = arrayOf("头条", "资讯")


    private val CHANNELS = arrayOf("头条", "资讯")
    private val mFragments = mutableListOf<Fragment>()
    private val mFragmentContainerHelper = FragmentContainerHelper()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        latestBinding = FragmentLatestBinding.inflate(inflater, container, false)
        return latestBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        latestBinding?.apply {
//            viewpager.adapter = ViewPagerFragmentAdapter(titles, this@LatestFragment.activity)
//            // attaching tab mediator
//            TabLayoutMediator(
//                tabLayout, viewpager
//            ) { tab: TabLayout.Tab, position: Int -> tab.text = titles[position] }.attach()
        }
        initFragments()
        initMagicIndicator()
    }

    override fun onDestroyView() {
        // Fragment 的存在时间比其视图长。请务必在 Fragment 的 onDestroyView() 方法中清除对绑定类实例的所有引用。
        latestBinding = null
        super.onDestroyView()
    }

    companion object {
        private val TAG = "HeadLineFragment"
    }


    private fun switchPages(index: Int) {
        val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        var fragment: Fragment
        var i = 0
        val j: Int = mFragments.size
        while (i < j) {
            if (i == index) {
                i++
                continue
            }
            fragment = mFragments[i]
            if (fragment.isAdded) {
                fragmentTransaction.hide(fragment)
            }
            i++
        }
        fragment = mFragments[index]
        if (fragment.isAdded) {
            fragmentTransaction.show(fragment)
        } else {
            fragmentTransaction.add(R.id.fragment_container, fragment)
        }
        fragmentTransaction.commitAllowingStateLoss()
    }

    private fun initFragments() {
        mFragments.add(HeadLineFragment())
        mFragments.add(NewsFragment())
    }

    private fun initMagicIndicator() {
        val magicIndicator = latestBinding?.magicIndicator
//        magicIndicator?.setBackgroundResource(R.drawable.round_indicator_bg)
        val commonNavigator = CommonNavigator(context)
        commonNavigator.adapter = object : CommonNavigatorAdapter() {
            override fun getCount(): Int {
                return CHANNELS.size
            }

            override fun getTitleView(context: Context?, index: Int): IPagerTitleView? {
                val clipPagerTitleView = ClipPagerTitleView(context)
                clipPagerTitleView.text = CHANNELS[index]
                clipPagerTitleView.textColor = Color.parseColor("#e94220")
                clipPagerTitleView.clipColor = Color.WHITE
                clipPagerTitleView.setOnClickListener {
                    mFragmentContainerHelper.handlePageSelected(index)
                    switchPages(index)
                }
                return clipPagerTitleView
            }

            override fun getIndicator(context: Context): IPagerIndicator? {
                val indicator = LinePagerIndicator(context)
                val navigatorHeight: Float =
                    context.resources.getDimension(R.dimen.common_navigator_height)
                val borderWidth: Float = UIUtil.dip2px(context, 1.0).toFloat()
                val lineHeight = navigatorHeight - 2 * borderWidth
                indicator.lineHeight = lineHeight
                indicator.roundRadius = lineHeight / 2
                indicator.yOffset = borderWidth
                indicator.setColors(Color.parseColor("#bc2a2a"))
                return indicator
            }
        }
        magicIndicator?.navigator = commonNavigator
        mFragmentContainerHelper.attachMagicIndicator(magicIndicator)
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
            0 -> return HeadLineFragment()
            1 -> return NewsFragment()
        }
        return HeadLineFragment()
    }

    override fun getItemCount(): Int {
        return titles?.size?:0
    }
}
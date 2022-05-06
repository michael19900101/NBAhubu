package com.aotuman.nbahubu.ui.main

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.aotuman.nbahubu.R
import com.aotuman.nbahubu.databinding.ActivityMainBinding
import com.aotuman.nbahubu.ui.latest.LatestFragment
import com.aotuman.nbahubu.ui.news.NewsFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@FlowPreview
@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding
    private var fragmentList = mutableListOf<Fragment>()

    lateinit var latestFragment: LatestFragment

    lateinit var newsFragment: NewsFragment

//    lateinit var playerFragment: PlayerFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        setImmersiveStatusBar(mainBinding.root)
        initFragments()

        val viewPager = mainBinding.viewpager
        val bottomNavigationView = mainBinding.bottomNavigationView

        //初始化viewPager
        viewPager.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int {
                return fragmentList.size
            }

            override fun createFragment(position: Int): Fragment {
                return fragmentList[position]
            }
        }
        viewPager.offscreenPageLimit = 1

        // 当ViewPager切换页面时，改变底部导航栏的状态
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                when (position) {
                }
                bottomNavigationView.menu.getItem(position).isChecked = true
            }
        })


        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            // 避免再次点击重复创建
            if (item.isChecked) return@setOnNavigationItemSelectedListener true
            when (item.itemId) {
                R.id.newsFragment -> {
                    viewPager.setCurrentItem(0, true)
                }
                R.id.playerFragment -> {
                    viewPager.setCurrentItem(1, true)
                }
            }
            return@setOnNavigationItemSelectedListener true
        }

    }

    private fun initFragments() {
        latestFragment = LatestFragment()
        newsFragment = NewsFragment()
//        playerFragment = PlayerFragment()
        fragmentList.add(latestFragment)
//        fragmentList.add(playerFragment)
    }

    private fun setImmersiveStatusBar(view: View) {
        // 设置系统栏透明
        window.statusBarColor = Color.TRANSPARENT
        // 控制状态栏
        WindowCompat.setDecorFitsSystemWindows(window,false)
        ViewCompat.requestApplyInsets(window.decorView)
        val controller: WindowInsetsControllerCompat? = ViewCompat.getWindowInsetsController(view)
        // 状态栏颜色
        controller?.isAppearanceLightStatusBars = false
    }
}
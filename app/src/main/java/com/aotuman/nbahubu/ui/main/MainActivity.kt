package com.aotuman.nbahubu.ui.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.aotuman.nbahubu.R
import com.aotuman.nbahubu.databinding.ActivityMainBinding
import com.aotuman.nbahubu.ui.news.NewsFragment
import com.aotuman.nbahubu.ui.player.PlayerFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import javax.inject.Inject

@FlowPreview
@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding
    private var fragmentList = mutableListOf<Fragment>()

    lateinit var newsFragment: NewsFragment

    lateinit var playerFragment: PlayerFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

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
                bottomNavigationView.menu.getItem(position).isChecked = true
            }
        })


        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            // 避免再次点击重复创建
            if (item.isChecked) return@setOnNavigationItemSelectedListener true
            when (item.itemId) {
                R.id.newsFragment -> {
                    mainBinding.head.visibility = View.VISIBLE
                    viewPager.setCurrentItem(0, true)
                }
                R.id.playerFragment -> {
                    mainBinding.head.visibility = View.GONE
                    viewPager.setCurrentItem(1, true)
                }
            }
            return@setOnNavigationItemSelectedListener true
        }

    }

    private fun initFragments() {
        newsFragment = NewsFragment()
        playerFragment = PlayerFragment()
        fragmentList.add(newsFragment)
        fragmentList.add(playerFragment)
    }
}
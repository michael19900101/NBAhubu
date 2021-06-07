package com.aotuman.nbahubu.ui.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.aotuman.nbahubu.R
import com.aotuman.nbahubu.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@FlowPreview
@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        val bottomNavigationView: BottomNavigationView = mainBinding.bottomNavigationView
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController
        bottomNavigationView.setupWithNavController(navController)

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            // 避免再次点击重复创建
            if (item.isChecked) return@setOnNavigationItemSelectedListener true
            // 避免B返回到A重复创建
            val popBackStack = navController.popBackStack(item.itemId, false)
            when (item.itemId) {
                R.id.newsFragment -> {
                    mainBinding.head.visibility = View.VISIBLE
                    if (!popBackStack) {
                        navController.navigate(R.id.newsFragment)
                    }
                }
                R.id.playerFragment -> {
                    mainBinding.head.visibility = View.GONE
                    if (!popBackStack) {
                        navController.navigate(R.id.playerFragment)
                    }
                }
            }
            return@setOnNavigationItemSelectedListener true
        }


    }
}
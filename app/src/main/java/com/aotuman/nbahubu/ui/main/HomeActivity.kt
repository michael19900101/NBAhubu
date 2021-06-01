package com.aotuman.nbahubu.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.aotuman.nbahubu.R
import com.google.android.material.bottomnavigation.BottomNavigationView

// Navigation with View Binding 混合使用有问题
// https://blog.csdn.net/BigBoySunshine/article/details/105774561
class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_home)

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController

//        val configuration = AppBarConfiguration(navController.graph)
//        NavigationUI.setupActionBarWithNavController(this, navController, configuration)
        bottomNavigationView.setupWithNavController(navController)
//        NavigationUI.setupWithNavController(bottomNavigationView, navController)

        bottomNavigationView.setOnNavigationItemSelectedListener(
            BottomNavigationView.OnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.newsFragment -> {
                        return@OnNavigationItemSelectedListener true
                    }
                    R.id.playerFragment -> {
                        return@OnNavigationItemSelectedListener true
                    }
                }
                false
            })

    }
}
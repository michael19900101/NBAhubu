package com.aotuman.nbahubu.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.aotuman.nbahubu.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview


@FlowPreview
@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding
    private val mViewModel: MainViewModel by viewModels()
    private val playerAdapter by lazy { PlayerAdapter(arrayListOf()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        mainBinding.apply {
            recyleView.adapter = playerAdapter
        }

        mViewModel.testRequest().observe(this, Observer { players ->
            players?.let {
                    playerAdapter.addData(it)
                    playerAdapter.notifyDataSetChanged()
            }
            mainBinding.swiperRefresh.isEnabled = false
        })
    }
}
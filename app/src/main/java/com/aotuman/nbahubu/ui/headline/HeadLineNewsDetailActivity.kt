package com.aotuman.nbahubu.ui.headline

import android.content.Context
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.aotuman.nbahubu.databinding.ActivityHeadLineNewsDetailBinding
import com.aotuman.nbahubu.utils.lifecycleScopeLaunch
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.jetbrains.anko.startActivity

/**
 * <pre>
 *     author: aotuman
 *     date  : 2022/05/09
 *     desc  :
 * </pre>
 */

@FlowPreview
@ExperimentalCoroutinesApi
@AndroidEntryPoint
class HeadLineNewsDetailActivity : AppCompatActivity() {

    private lateinit var binding : ActivityHeadLineNewsDetailBinding
    private val viewModel: HeadLineNewsDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.lifecycleScopeLaunch(Dispatchers.IO) {
            viewModel.fetchNewsDetailData()
        }
    }

    companion object {
        private val TAG = "HeadLineNewsDetailActivity"
        private val KEY_NEWS_ID = "newsID"
        fun jumpActivity(act: Context, params: String) {
            act.startActivity<HeadLineNewsDetailActivity>(KEY_NEWS_ID to params)
        }
    }
}
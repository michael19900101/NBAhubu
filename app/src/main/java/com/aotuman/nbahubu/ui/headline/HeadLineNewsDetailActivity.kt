package com.aotuman.nbahubu.ui.headline

import android.content.Context
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.aotuman.nbahubu.R
import com.aotuman.nbahubu.databinding.ActivityHeadLineNewsDetailBinding
import com.aotuman.nbahubu.utils.lifecycleScopeLaunch
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.PlayerView
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

    private lateinit var mBindingActivity : ActivityHeadLineNewsDetailBinding
    private val viewModel: HeadLineNewsDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 设置系统导航栏的颜色
        window.navigationBarColor = ContextCompat.getColor(this, R.color.page_bg_grey_color)
        mBindingActivity = ActivityHeadLineNewsDetailBinding.inflate(layoutInflater)
        setContentView(mBindingActivity.root)
        setSupportActionBar(mBindingActivity.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true) //添加默认的返回图标
        supportActionBar?.setHomeButtonEnabled(true) //设置返回键可用
        mBindingActivity.toolbarTitle.text = "新闻详情"
//        play(mBindingActivity.playerView)

        val newsID = intent.getStringExtra(KEY_NEWS_ID)
        lifecycleScopeLaunch(Dispatchers.IO) {
            val headLineNewsDetailModel = viewModel.fetchNewsDetailData(newsID.toString())
            lifecycleScopeLaunch(Dispatchers.Main){
                headLineNewsDetailModel?.let {
                    mBindingActivity.tvNewsTitle.text = headLineNewsDetailModel.title
                    mBindingActivity.tvNewsPubDate.text = headLineNewsDetailModel.publishTime
                    mBindingActivity.contentLayout.addViewByModel(headLineNewsDetailModel)
                }
            }
        }
    }

    private fun play(playerView: PlayerView){
        //设置播放器
        val player = SimpleExoPlayer.Builder(this).build()
        playerView.setPlayer(player)

        //播放视频

        //播放视频
        val videoUri = "https://v-cdn.zjol.com.cn/276982.mp4"
        // Build the media item.
        val mediaItem: MediaItem = MediaItem.fromUri(videoUri)
        // Set the media item to be played.
        player.setMediaItem(mediaItem)
        // Prepare the player.
        player.prepare()
        // Start the playback.
        player.play()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        private val TAG = "HeadLineNewsDetailActivity"
        private val KEY_NEWS_ID = "newsID"
        fun jumpActivity(act: Context, params: String) {
            act.startActivity<HeadLineNewsDetailActivity>(KEY_NEWS_ID to params)
        }
    }
}
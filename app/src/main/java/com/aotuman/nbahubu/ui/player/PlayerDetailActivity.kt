package com.aotuman.nbahubu.ui.player

import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.aotuman.nbahubu.common.radarview.RadarData
import com.aotuman.nbahubu.common.radarview.RadarView
import com.aotuman.nbahubu.data.entity.player.StatsData
import com.aotuman.nbahubu.databinding.ActivityPlayerDetailBinding
import com.aotuman.nbahubu.utils.dp2px
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import java.util.*

@FlowPreview
@ExperimentalCoroutinesApi
@AndroidEntryPoint
class PlayerDetailActivity : AppCompatActivity()  {

    private lateinit var playerDetailBinding: ActivityPlayerDetailBinding
    private val viewModel: PlayerDetailViewModel by viewModels()
    private lateinit var mRadarView: RadarView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        playerDetailBinding = ActivityPlayerDetailBinding.inflate(layoutInflater)
        setContentView(playerDetailBinding.root)

        playerDetailBinding.apply {
            mRadarView = radarView
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayShowTitleEnabled(false)
            supportActionBar?.setDisplayHomeAsUpEnabled(true) //添加默认的返回图标
            supportActionBar?.setHomeButtonEnabled(true) //设置返回键可用
        }

        val playerId = intent.getStringExtra("playerId")
        if (playerId != null) {
            viewModel.fetchPlayerDetail(playerId).observe(this, androidx.lifecycle.Observer {
                    playerDetail ->
                playerDetailBinding.playerDetail = playerDetail
                initRadarChart(playerDetail.stats, playerDetail.maxStats)
            })
        }

    }

    private fun initRadarChart(stats: StatsData, maxStats: StatsData) {
        mRadarView.emptyHint = "无数据"
        // 环形内层颜色(内->外)
        val layerColor = mutableListOf<Int>()
        Collections.addAll(layerColor, -0x592f06, -0x512b06, -0x492708, -0x391e04, -0x190d02)
        mRadarView.layerColor = layerColor

        val vertexText = mutableListOf<String>()
        Collections.addAll(vertexText, "场均篮板", "场均助攻", "场均盖帽", "场均抢断", "场均得分")
        mRadarView.vertexText = vertexText

        val values = mutableListOf<Float>()
        Collections.addAll(values, stats.rebounds, stats.assists, stats.blocks, stats.steals, stats.points)
        val data = RadarData(values)
        data.isValueTextEnable = true
        data.vauleTextColor = Color.BLACK
        data.color = Color.parseColor("#53A4FA") // 数据之间的区域颜色

        data.valueTextSize = dp2px(8f)
        mRadarView.addData(data)

        val maxValues = mutableListOf<Float>()
        Collections.addAll(maxValues, maxStats.rebounds, maxStats.assists, maxStats.blocks, maxStats.steals, maxStats.points)
        mRadarView.maxValues = maxValues
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
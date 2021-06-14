package com.aotuman.nbahubu.ui.player

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import com.aotuman.nbahubu.R
import com.aotuman.nbahubu.common.radarview.RadarData
import com.aotuman.nbahubu.common.radarview.RadarView
import com.aotuman.nbahubu.utils.dp2px
import com.google.android.material.appbar.CollapsingToolbarLayout
import java.util.*

class PlayerDetailActivity : AppCompatActivity()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_player_detail)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        val toolbarTitle = findViewById<TextView>(R.id.toolbar_title)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true) //添加默认的返回图标
        supportActionBar?.setHomeButtonEnabled(true) //设置返回键可用
        toolbarTitle.text = "球员详情"

        val mRadarView: RadarView = findViewById<View>(R.id.radarView) as RadarView

        mRadarView.emptyHint = "无数据"

        // 环形内层颜色(内->外)
        val layerColor = mutableListOf<Int>()
        Collections.addAll(layerColor, -0x592f06, -0x512b06, -0x492708, -0x391e04, -0x190d02)
        mRadarView.layerColor = layerColor

        val vertexText = mutableListOf<String>()
        Collections.addAll(vertexText, "场均篮板", "场均助攻", "场均盖帽", "场均抢断", "场均得分")
        mRadarView.vertexText = vertexText

        val values = mutableListOf<Float>()
        Collections.addAll(values, 6.0f, 5.3f, 1.5f, 1.6f, 13.8f)
        val data = RadarData(values)
        data.isValueTextEnable = true
        data.vauleTextColor = Color.BLACK
        data.color = Color.parseColor("#53A4FA") // 数据之间的区域颜色

        data.valueTextSize = dp2px(8f)
        mRadarView.addData(data)

        val maxValues = mutableListOf<Float>()
        Collections.addAll(maxValues, 13.9f, 11.8f, 3.0f, 2.8f, 35.6f)
        mRadarView.maxValues = maxValues
    }
}
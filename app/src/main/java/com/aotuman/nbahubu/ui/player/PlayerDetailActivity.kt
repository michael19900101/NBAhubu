package com.aotuman.nbahubu.ui.player

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.aotuman.nbahubu.R
import com.aotuman.nbahubu.common.RadarItem
import com.aotuman.nbahubu.common.RadarView

class PlayerDetailActivity : AppCompatActivity()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_radar)
        val radarview = findViewById<RadarView>(R.id.radarview)

        val radarItemList = mutableListOf<RadarItem>()
        for (i in 0..4) {
            val value: Int = getRandomValue()
            val progress = value / 100.0f
            radarItemList.add(RadarItem("标签" + (i + 1), "" + value, progress))
        }
        radarview.setRadarItemList(radarItemList)
    }

    //随机获取50-100的int
    private fun getRandomValue(): Int {
        return (Math.random() * 50 + 51).toInt()
    }
}
package com.aotuman.nbahubu.ui.news

import android.content.Context
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.aotuman.nbahubu.databinding.ActivityNewsDetailBinding
import com.aotuman.nbahubu.model.news.NewsItemModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.jetbrains.anko.startActivity

/**
 * <pre>
 *     author: aotuman
 *     date  : 2021/06/08
 *     desc  :
 * </pre>
 */

@FlowPreview
@ExperimentalCoroutinesApi
@AndroidEntryPoint
class NewsDetailActivity : AppCompatActivity() {

    private lateinit var mBindingActivity: ActivityNewsDetailBinding
    lateinit var newsItemModel: NewsItemModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBindingActivity = ActivityNewsDetailBinding.inflate(layoutInflater)
        setContentView(mBindingActivity.root)
        setSupportActionBar(mBindingActivity.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true) //添加默认的返回图标
        supportActionBar?.setHomeButtonEnabled(true) //设置返回键可用
        mBindingActivity.toolbarTitle.text = "新闻详情"

        mBindingActivity.apply {
            newsItemModel = requireNotNull(intent.getParcelableExtra(KEY_LIST_MODEL)) {
                "params is not null"
            }
            mBindingActivity.tvNewsTitle.text = newsItemModel.title
            mBindingActivity.tvNewsPubDate.text = newsItemModel.pub_time
            contentLayout.addViewByModel(newsItemModel)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        private val TAG = "NewsDetailActivity"
        private val KEY_LIST_MODEL = "listModel"
        fun jumpActivity(act: Context, params: NewsItemModel) {
            act.startActivity<NewsDetailActivity>(KEY_LIST_MODEL to params)
        }
    }
}
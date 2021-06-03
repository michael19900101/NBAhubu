package com.aotuman.nbahubu.ui.news

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.paging.PagingDataAdapter
import com.aotuman.nbahubu.R
import com.aotuman.nbahubu.databinding.ItemLatestNewsBinding
import com.aotuman.nbahubu.model.news.NewsItemModel
import com.hi.dhl.jdatabinding.DataBindingViewHolder
import com.hi.dhl.jdatabinding.dowithTry

/**
 * <pre>
 *     author: aotuman
 *     date  : 2021/06/03
 *     desc  :
 * </pre>
 */
class NewsAdapter :
    PagingDataAdapter<NewsItemModel, NewsViewHolder>(NewsItemModel.diffCallback) {

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        dowithTry {
            val data = getItem(position)
            data?.let {
                holder.bindData(data, position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = inflateView(parent, R.layout.item_latest_news)
        return NewsViewHolder(view)
    }

    private fun inflateView(viewGroup: ViewGroup, @LayoutRes viewType: Int): View {
        val layoutInflater = LayoutInflater.from(viewGroup.context)
        return layoutInflater.inflate(viewType, viewGroup, false)
    }
}

class NewsViewHolder(view: View) : DataBindingViewHolder<NewsItemModel>(view) {
    private val mBinding: ItemLatestNewsBinding by viewHolderBinding(view)

    override fun bindData(data: NewsItemModel, position: Int) {
        mBinding.apply {
//            data.id = "#${position + 1}"
            newsitem = data
            executePendingBindings()
        }
    }

}
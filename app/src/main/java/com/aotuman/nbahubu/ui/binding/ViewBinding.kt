package com.aotuman.nbahubu.ui.binding

import android.app.Activity
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import coil.load
import com.aotuman.nbahubu.R
import com.aotuman.nbahubu.model.news.NewsItemModel
import com.aotuman.nbahubu.ui.news.NewsDetailActivity
//import com.hi.dhl.jprogressview.JProgressView
import timber.log.Timber

/**
 * <pre>
 *     author: aotuman
 *     date  : 2021/06/03
 *     desc  :
 * </pre>
 */

@BindingAdapter("bindingAvator")
fun bindingAvator(imageView: ImageView, url: String?) {
    imageView.load(url) {
        crossfade(true)
        placeholder(R.mipmap.ic_launcher)
    }
}

@BindingAdapter("bindNewsItemClick")
fun bindingNewsItemClick(view: View, model: NewsItemModel) {
    view.setOnClickListener {
        NewsDetailActivity.jumpActivity(
            view.context,
            model
        )
    }
}

@BindingAdapter("bindSmallImage")
fun bindingSmallImage(imageView: ImageView, url: String) {
    imageView.load(url) {
        crossfade(true)
        placeholder(R.mipmap.ic_launcher)
        size(280, 280)
    }
}

@BindingAdapter("bindLoading")
fun bindingLoading(swipe: SwipeRefreshLayout, isLoading: Boolean) {
    Timber.tag("bindingLoading").e(" isLoading = ${isLoading}")
    swipe.isRefreshing = isLoading
    if (!isLoading) swipe.isEnabled = false
}

//@BindingAdapter("bindProgressValue", "bindProgressMaxValue")
//fun bindingProgressView(progress: JProgressView, progressValue: Int, maxProgressValue: Int) {
//    progress
//        .setProgress(progressValue.toFloat())
//        .setMaxProgress(maxProgressValue)
//        .startAnimal()
//}

@BindingAdapter("bindFinish")
fun bindingFinish(view: View, finish: Boolean) {
    val ctx = view.context
    if (ctx is Activity && finish) {
        view.setOnClickListener { ctx.finish() }
    }
}
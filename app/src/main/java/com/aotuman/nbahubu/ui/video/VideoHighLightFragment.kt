package com.aotuman.nbahubu.ui.video

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aotuman.nbahubu.R
import com.aotuman.nbahubu.databinding.FragmentHighlightBinding
import com.aotuman.nbahubu.model.video.VideoItemModel
import com.aotuman.nbahubu.ui.video.recycleview.RecyclerItemNormalHolder
import com.aotuman.nbahubu.ui.video.recycleview.RecyclerNormalAdapter
import com.aotuman.nbahubu.utils.lifecycleScopeLaunch
import com.shuyu.gsyvideoplayer.GSYVideoManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview


@FlowPreview
@ExperimentalCoroutinesApi
@AndroidEntryPoint
class VideoHighLightFragment : Fragment(R.layout.fragment_highlight) {

    private var highlightBinding: FragmentHighlightBinding? = null
    private val videoViewModel: VideoViewModel by viewModels()
    private var recyclerNormalAdapter: RecyclerNormalAdapter? = null
    private var linearLayoutManager: LinearLayoutManager? = null

    companion object {
        private val TAG = "VideoHighLightFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        highlightBinding = FragmentHighlightBinding.inflate(inflater, container, false)
        return highlightBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        highlightBinding?.recycleView?.apply {
            linearLayoutManager = LinearLayoutManager(activity)
            layoutManager = linearLayoutManager
            addOnScrollListener(object : RecyclerView.OnScrollListener(){

                var firstVisibleItem = 0
                var lastVisibleItem:Int = 0

                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                }

                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    firstVisibleItem = linearLayoutManager?.findFirstVisibleItemPosition()?:0
                    lastVisibleItem = linearLayoutManager?.findLastVisibleItemPosition()?:0
                    //大于0说明有播放
                    if (GSYVideoManager.instance().playPosition >= 0) {
                        //当前播放的位置
                        val position = GSYVideoManager.instance().playPosition
                        //对应的播放列表TAG
                        if (GSYVideoManager.instance().playTag == RecyclerItemNormalHolder.TAG && (position < firstVisibleItem || position > lastVisibleItem)) {

                            //如果滑出去了上面和下面就是否，和今日头条一样
                            //是否全屏
                            if (!GSYVideoManager.isFullState(activity)) {
                                GSYVideoManager.releaseAllVideos()
                                recyclerNormalAdapter?.notifyItemChanged(position)
                            }
                        }
                    }
                }
            })
        }

        // 获取视频列表数据
        activity.lifecycleScopeLaunch(Dispatchers.IO) {
            val videoItemModels = videoViewModel.fetchVideosData(1002,1,"")

            activity.lifecycleScopeLaunch(Dispatchers.Main) {
                videoItemModels?.let {
                    recyclerNormalAdapter = RecyclerNormalAdapter(activity, it, videoViewModel)
                    highlightBinding?.recycleView?.adapter = recyclerNormalAdapter
                    highlightBinding?.recycleView?.adapter?.notifyDataSetChanged()
                }

            }
        }
    }

    override fun onDestroyView() {
        // Fragment 的存在时间比其视图长。请务必在 Fragment 的 onDestroyView() 方法中清除对绑定类实例的所有引用。
        highlightBinding = null
        super.onDestroyView()
    }


    // todo
    fun onBackPressed(): Boolean {
        return GSYVideoManager.backFromWindowFull(activity)
    }

    override fun onPause() {
        super.onPause()
        GSYVideoManager.onPause()
    }

    override fun onResume() {
        super.onResume()
        GSYVideoManager.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        GSYVideoManager.releaseAllVideos()
    }

}
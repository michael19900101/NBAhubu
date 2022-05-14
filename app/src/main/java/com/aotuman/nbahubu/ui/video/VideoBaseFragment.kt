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
import com.aotuman.nbahubu.databinding.FragmentVideoBaseBinding
import com.aotuman.nbahubu.model.video.VideoItemModel
import com.aotuman.nbahubu.ui.video.recycleview.RecyclerItemNormalHolder
import com.aotuman.nbahubu.ui.video.recycleview.RecyclerNormalAdapter
import com.aotuman.nbahubu.utils.lifecycleScopeLaunch
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener
import com.shuyu.gsyvideoplayer.GSYVideoManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import javax.inject.Inject


@FlowPreview
@ExperimentalCoroutinesApi
@AndroidEntryPoint
class VideoBaseFragment @Inject constructor(var videoType: VideoType): Fragment(R.layout.fragment_video_base) {

    private var videoBaseBinding: FragmentVideoBaseBinding? = null
    private val videoViewModel: VideoViewModel by viewModels()
    private var recyclerNormalAdapter: RecyclerNormalAdapter? = null
    private var linearLayoutManager: LinearLayoutManager? = null
    private var curPage = 1
    private var lastNewsTime = "" // 最近同步的最新的一条新闻的时间
    private var videoModels = mutableListOf<VideoItemModel>()

    companion object {
        private val TAG = "VideoBaseFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        videoBaseBinding = FragmentVideoBaseBinding.inflate(inflater, container, false)
        return videoBaseBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        videoBaseBinding?.recycleView?.apply {
            linearLayoutManager = LinearLayoutManager(activity)
            layoutManager = linearLayoutManager
            recyclerNormalAdapter = RecyclerNormalAdapter(activity, videoViewModel)
            adapter = recyclerNormalAdapter
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

        videoBaseBinding?.apply {
            // 进入页面自动加载数据
            smartRefreshLayout.autoRefresh()

            smartRefreshLayout.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
                override fun onRefresh(refreshLayout: RefreshLayout) {
                    // 重设 curPage和lastNewsTime
                    curPage = 1
                    lastNewsTime = ""

                    fetPageData(curPage, lastNewsTime){
                        // update ui
                        smartRefreshLayout.finishRefresh()
                        recyclerNormalAdapter?.setListData(videoModels)
                    }

                }

                override fun onLoadMore(refreshLayout: RefreshLayout) {
                    curPage++
                    fetPageData(curPage, lastNewsTime){
                        // update ui
                        smartRefreshLayout.finishLoadMore()
                        recyclerNormalAdapter?.setListData(videoModels)
                    }
                }

            })
        }
    }

    private fun fetPageData(pageNum: Int, lastTime: String, updateUI: () -> Unit){
        // 获取视频列表数据
        activity.lifecycleScopeLaunch(Dispatchers.IO) {
            val columnID = when(videoType){
                VideoType.HIGHLIGHT -> 1002
                VideoType.OPTIMUM -> 1003
                VideoType.EVENING_CLASS -> 1004
            }
            val videoItemModels = videoViewModel.fetchVideosData(columnID, pageNum, lastTime)

            activity.lifecycleScopeLaunch(Dispatchers.Main) {
                videoItemModels?.let {
                    if (it[0].publishTime?.isEmpty() == false) {
                        // update lastNewsTime
                        lastNewsTime = it[0].publishTime.toString()
                        Log.d(TAG,"videoType:${videoType}, 入参：pageNum:${pageNum} ,lastTime:${lastTime}" +
                                    "\n出参：lastNewsTime:${lastNewsTime}")
                    }

                    if (pageNum == 1) {
                        videoModels.clear()
                    }
                    videoModels.addAll(videoItemModels)
                }
                updateUI()
            }
        }
    }

    override fun onDestroyView() {
        // Fragment 的存在时间比其视图长。请务必在 Fragment 的 onDestroyView() 方法中清除对绑定类实例的所有引用。
        videoBaseBinding = null
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
        if (isVisible && isFirstFetchData) {
            videoBaseBinding?.smartRefreshLayout?.autoRefresh()
            isFirstFetchData = false
        }
        GSYVideoManager.onResume()
    }

    private var isFirstFetchData = true

    override fun onDestroy() {
        super.onDestroy()
        GSYVideoManager.releaseAllVideos()
    }

}
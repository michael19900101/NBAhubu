package com.aotuman.nbahubu.ui.news

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.aotuman.nbahubu.R
import com.aotuman.nbahubu.databinding.FragmentNewsBinding
import com.aotuman.nbahubu.ui.temp.NewsViewModelTemp
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@FlowPreview
@ExperimentalCoroutinesApi
@AndroidEntryPoint
class NewsFragment : Fragment(R.layout.fragment_news) {

    private var fragmentNewsBinding: FragmentNewsBinding? = null
    private val viewModel: NewsViewModel by viewModels()
    private val newsAdapter by lazy { NewsAdapter() }
    private val newsTempVM: NewsViewModelTemp by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentNewsBinding = FragmentNewsBinding.inflate(inflater, container, false)
        return fragmentNewsBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentNewsBinding?.apply {
            recyleView.adapter = newsAdapter
            swipeRefresh.autoRefreshAnimationOnly()
            swipeRefresh.setOnRefreshListener {
                newsAdapter.refresh()
            }
        }
        lifecycleScope.launch {
            newsAdapter.loadStateFlow.collectLatest { state ->
                val success = state.refresh is LoadState.Loading
                fragmentNewsBinding?.swipeRefresh?.finishLoadMore(1000, success, true)
            }
        }

        viewModel.postOfData().observe(viewLifecycleOwner, Observer {
            newsAdapter.submitData(lifecycle, it)
        })

        newsTempVM.fetchNews().observe(viewLifecycleOwner, Observer {

            Log.e("jbjb","??????")
        })
    }

    override fun onDestroyView() {
        // Fragment ????????????????????????????????????????????? Fragment ??? onDestroyView() ???????????????????????????????????????????????????
        fragmentNewsBinding = null
        super.onDestroyView()
    }

    companion object {
        private val TAG = "NewsFragment"
    }

}
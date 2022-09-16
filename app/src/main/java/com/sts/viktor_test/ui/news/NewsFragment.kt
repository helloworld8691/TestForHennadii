package com.sts.viktor_test.ui.news

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.sts.viktor_test.R
import com.sts.viktor_test.databinding.FragmentNewsBinding
import com.sts.viktor_test.ui.adapter.NewsAdapter
import com.sts.viktor_test.ui.common.BaseFragment

class NewsFragment : BaseFragment<FragmentNewsBinding, NewsViewModel>() {

    override fun getViewModelClass() = NewsViewModel::class.java

    override fun getViewBinding() = FragmentNewsBinding.inflate(layoutInflater)

    private val adapter : NewsAdapter by lazy { NewsAdapter(newsListListener) }

    override fun setUpViews() {
        super.setUpViews()
        binding.header.lytSearchFilterSortContainer.visibility = View.GONE
        binding.rcvNews.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rcvNews.adapter = adapter
    }

    override fun observeData() {
        super.observeData()

        viewModel.loadingViewVisible.observe(viewLifecycleOwner, Observer {
            setProgressBarVisible(it.peekContent())
        })

        viewModel.newsList.observe(viewLifecycleOwner, Observer {
            adapter.update(it)
        })
    }

    private val newsListListener = object : NewsAdapter.Listener {
        override fun newsItemClicked(position: Int) {

        }
    }
}
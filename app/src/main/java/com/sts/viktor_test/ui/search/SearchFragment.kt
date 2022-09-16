package com.sts.viktor_test.ui.search

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.jakewharton.rxbinding.widget.RxTextView
import com.sts.viktor_test.MainActivity
import com.sts.viktor_test.databinding.FragmentSearchBinding
import com.sts.viktor_test.ui.adapter.NewsAdapter
import com.sts.viktor_test.ui.adapter.SearchHistoryAdapter
import com.sts.viktor_test.ui.common.BaseFragment
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

class SearchFragment : BaseFragment<FragmentSearchBinding, SearchViewModel>() {

    override fun getViewModelClass() = SearchViewModel::class.java

    override fun getViewBinding() = FragmentSearchBinding.inflate(layoutInflater)

    private val newsAdapter : NewsAdapter by lazy { NewsAdapter(newsListListener) }
    private val searchHistoryAdapter : SearchHistoryAdapter by lazy { SearchHistoryAdapter(searchHistoryListListener) }

    override fun setUpViews() {
        super.setUpViews()
        binding.rcvSearchResult.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rcvSearchResult.adapter = newsAdapter

        binding.rcvSearchHistory.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rcvSearchHistory.adapter = searchHistoryAdapter

        binding.header.edtSearchKey.setOnFocusChangeListener(object : View.OnFocusChangeListener{
            override fun onFocusChange(view: View?, hasFocus: Boolean) {
                if (!hasFocus){
                    hideKeyboard()

                    val searchKey = binding.header.edtSearchKey.text.toString().trim()
                    viewModel.saveSearchKey(searchKey)

                    searchNews()
                }
            }
        })

        RxTextView.textChanges(binding.header.edtSearchKey).observeOn(AndroidSchedulers.mainThread())
            .subscribe{
                if (it.toString().length == 0){
                    readSearchHistory()
                }
            }.addToContainer()

        readSearchHistory()
    }

    private fun searchNews(){
        viewModel.search(searchKey = binding.header.edtSearchKey.text.toString().trim(),
            fromDate = fromDate,
            toDate = toDate,
            searchIn = searchIn,
            sortBy = sortBy
        )
    }

    private fun hideKeyboard() {
        requireActivity().currentFocus.let {
            val imm = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(binding.header.edtSearchKey.windowToken, 0)
        }
    }

    private fun readSearchHistory(){
        binding.lytSearchResult.visibility = View.GONE
        binding.lytSearchHistory.visibility = View.VISIBLE
        viewModel.readSearchHistory()
    }

    override fun observeData() {
        super.observeData()

        viewModel.newsList.observe(viewLifecycleOwner, Observer {

            binding.lytSearchResult.visibility = View.VISIBLE
            binding.lytSearchHistory.visibility = View.GONE

            newsAdapter.update(it.articles)
            binding.txvTopHeadlinesCnt.text = "${it.totalArticles} news"
        })

        viewModel.searchHistory.observe(viewLifecycleOwner, Observer {
            searchHistoryAdapter.update(it)
        })

        viewModel.loadingViewVisible.observe(viewLifecycleOwner, Observer {
            setProgressBarVisible(it.peekContent())
        })
    }

    override fun setOnClickListeners() {
        super.setOnClickListeners()

        binding.header.frmFilter.setOnClickListener {
            navigationService.openFilterScreen()
        }

        binding.header.frmSort.setOnClickListener {
            val sortBottomSheetFragment = SortBottomSheetFragment.newInstance()
            sortBottomSheetFragment.showNow(parentFragmentManager, "")
            sortBottomSheetFragment.dialog?.setOnDismissListener {
                onResume()
            }
        }
    }

    private val newsListListener = object : NewsAdapter.Listener {
        override fun newsItemClicked(position: Int) {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(viewModel.newsList.value!!.articles.get(position).url))
            startActivity(browserIntent)
        }
    }

    private val searchHistoryListListener = object : SearchHistoryAdapter.Listener {
        override fun onItemClearInSearchHistory(position: Int) {
            viewModel.deleteItemInSearchHistory(position)
        }

        override fun onItemClicked(position: Int) {
            binding.header.edtSearchKey.setText(viewModel.searchHistory.value!!.get(position))
            searchNews()
        }
    }

    override fun onResume() {
        super.onResume()

        if (isFilterApplied && binding.header.edtSearchKey.text.toString().trim().isNotEmpty()){
            isFilterApplied = false
            searchNews()
        }
    }

    companion object {
        var fromDate : String = ""
        var toDate : String = ""
        var searchIn : String = ""
        var sortBy : String = ""

        var isFilterApplied = false
    }
}
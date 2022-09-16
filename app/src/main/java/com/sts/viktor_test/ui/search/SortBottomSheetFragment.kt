package com.sts.viktor_test.ui.search

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.sts.viktor_test.R
import com.sts.viktor_test.databinding.FragmentSortBottomSheetBinding
import com.sts.viktor_test.di.ViewModelProviderFactory
import com.sts.viktor_test.ui.common.DaggerBottomSheetDialogFragment
import javax.inject.Inject

class SortBottomSheetFragment : DaggerBottomSheetDialogFragment<FragmentSortBottomSheetBinding, SearchViewModel>() {

    override fun getViewModelClass() =  SearchViewModel::class.java
    override fun getViewBinding() = FragmentSortBottomSheetBinding.inflate(layoutInflater)


    override fun setUpViews() {
        super.setUpViews()

        binding.radioGroup.setOnCheckedChangeListener { radioGroup, checkedId ->
            when(checkedId) {
                binding.rdbUploadDate.id -> {
                    SearchFragment.sortBy = "publishedAt"
                }
                binding.rdbRelevance.id -> {
                    SearchFragment.sortBy = "relevance"
                }
            }
            SearchFragment.isFilterApplied = true
        }
    }

    companion object {
        fun newInstance() = SortBottomSheetFragment()
    }
}
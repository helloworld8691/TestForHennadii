package com.sts.viktor_test.ui.filter

import com.sts.viktor_test.databinding.FragmentFilterSectorBinding
import com.sts.viktor_test.ui.common.BaseFragment
import com.sts.viktor_test.ui.search.SearchFragment
import com.sts.viktor_test.ui.search.SearchViewModel

class FilterSectorFragment : BaseFragment<FragmentFilterSectorBinding, SearchViewModel>() {
    override fun getViewModelClass() = SearchViewModel::class.java

    override fun getViewBinding() = FragmentFilterSectorBinding.inflate(layoutInflater)

    override fun setUpViews() {
        super.setUpViews()
        handleBackButton {
            navigationService.navigationBack()
        }

        binding.btnApply.setOnClickListener {

            SearchFragment.isFilterApplied = true

            var searchIn = ""

            if (binding.switchTitle.isChecked) {
                searchIn += "title,"
            }

            if (binding.switchDescription.isChecked){
                searchIn += "description,"
            }

            if (binding.switchContent.isChecked){
                searchIn += "content,"
            }

            SearchFragment.searchIn = searchIn.dropLast(1)

            navigationService.navigationBack()
        }
    }

    override fun setOnClickListeners() {
        super.setOnClickListeners()
        binding.header.imvBack.setOnClickListener{
            navigationService.navigationBack()
        }
    }
}
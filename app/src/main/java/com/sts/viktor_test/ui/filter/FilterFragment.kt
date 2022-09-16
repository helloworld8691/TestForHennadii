package com.sts.viktor_test.ui.filter

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sts.viktor_test.R
import com.sts.viktor_test.databinding.FragmentFilterBinding
import com.sts.viktor_test.ui.common.BaseFragment
import com.sts.viktor_test.ui.search.SearchFragment
import com.sts.viktor_test.ui.search.SearchViewModel
import com.sts.viktor_test.utils.DAY_END_TIME
import com.sts.viktor_test.utils.DAY_START_TIME
import com.sts.viktor_test.utils.formatDate
import com.sts.viktor_test.utils.getCurrentDate
import java.util.*

class FilterFragment : BaseFragment<FragmentFilterBinding, SearchViewModel>() {
    override fun getViewModelClass() = SearchViewModel::class.java
    override fun getViewBinding() = FragmentFilterBinding.inflate(layoutInflater)

    override fun setUpViews() {
        super.setUpViews()

        binding.txvToDate.text = getCurrentDate()

        handleBackButton {
            navigationService.backToMainActivity()
        }
    }

    override fun setOnClickListeners() {
        super.setOnClickListeners()
        binding.header.imvBack.setOnClickListener{
            navigationService.backToMainActivity()
        }

        binding.lytSearchIn.setOnClickListener {
            navigationService.openFilterSectorScreen()
        }

        binding.imbPickupToDate.setOnClickListener{
            showDatePicker(DATE_TYPE.TO)
        }

        binding.imbPickupFromDate.setOnClickListener {
            showDatePicker(DATE_TYPE.FROM)
        }

        binding.btnApplyFilter.setOnClickListener{

            SearchFragment.fromDate = binding.txvFromDate.text.toString().trim() + DAY_START_TIME
            SearchFragment.toDate = binding.txvToDate.text.toString().trim() + DAY_END_TIME
            SearchFragment.isFilterApplied = true

            navigationService.navigationBack()
        }
    }

    private fun showDatePicker(dateType : DATE_TYPE){
        val calendar = Calendar.getInstance()
        val _year = calendar.get(Calendar.YEAR)
        val _month = calendar.get(Calendar.MONTH)
        val _day = calendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(requireActivity(), DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            when(dateType){
                DATE_TYPE.TO-> {
                    binding.txvToDate.text = formatDate(year, monthOfYear, dayOfMonth)
                }
                DATE_TYPE.FROM -> {
                    binding.txvFromDate.text = formatDate(year, monthOfYear, dayOfMonth)
                }
            }
        }, _year, _month, _day)

        dpd.show()
    }

    enum class DATE_TYPE {
        FROM,
        TO
    }
}
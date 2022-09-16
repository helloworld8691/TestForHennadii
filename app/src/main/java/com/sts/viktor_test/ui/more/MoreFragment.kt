package com.sts.viktor_test.ui.more

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sts.viktor_test.R
import com.sts.viktor_test.databinding.FragmentHomeBinding
import com.sts.viktor_test.databinding.FragmentMoreBinding
import com.sts.viktor_test.ui.common.BaseFragment

class MoreFragment : BaseFragment<FragmentMoreBinding, MoreViewModel>() {
    override fun getViewModelClass() = MoreViewModel::class.java

    override fun getViewBinding() = FragmentMoreBinding.inflate(layoutInflater)

}
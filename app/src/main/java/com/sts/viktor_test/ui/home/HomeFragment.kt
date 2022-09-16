package com.sts.viktor_test.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sts.viktor_test.R
import com.sts.viktor_test.databinding.FragmentHomeBinding
import com.sts.viktor_test.ui.common.BaseFragment


class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    override fun getViewModelClass() = HomeViewModel::class.java
    override fun getViewBinding() = FragmentHomeBinding.inflate(layoutInflater)
}
package com.sts.viktor_test.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sts.viktor_test.R
import com.sts.viktor_test.databinding.FragmentProfileBinding
import com.sts.viktor_test.ui.common.BaseFragment

class ProfileFragment : BaseFragment<FragmentProfileBinding, ProfileViewModel>() {

    override fun getViewModelClass() = ProfileViewModel::class.java
    override fun getViewBinding() = FragmentProfileBinding.inflate(layoutInflater)
}
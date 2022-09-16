package com.sts.viktor_test.ui.common

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.sts.viktor_test.R
import com.sts.viktor_test.di.ViewModelProviderFactory
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

abstract class DaggerBottomSheetDialogFragment<VBinding: ViewBinding, VModel: ViewModel> : BottomSheetDialogFragment(), HasAndroidInjector {

    open var useSharedViewModel : Boolean = false

    protected lateinit var binding: VBinding
    protected lateinit var viewModel: VModel
    protected abstract fun getViewModelClass() : Class<VModel>
    protected abstract fun getViewBinding(): VBinding

    @Inject
    lateinit var factory: ViewModelProviderFactory

    @Inject
    lateinit var mChildFragmentInjector: DispatchingAndroidInjector<Any>

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return mChildFragmentInjector
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpViews()
        observeData()
        setOnClickListeners()
    }

    open fun setUpViews(){}

    open fun setOnClickListeners(){}

    open fun observeData(){}

    private fun init(){

        setStyle(DialogFragment.STYLE_NORMAL, R.style.AppBottomSheetDialogTheme)

        binding = getViewBinding()
        viewModel = if (useSharedViewModel){
            ViewModelProvider(requireActivity(), factory).get(getViewModelClass())
        }else {
            ViewModelProvider(this, factory).get(getViewModelClass())
        }
    }
}
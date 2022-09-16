package com.sts.viktor_test.ui.common

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.sts.viktor_test.MainActivity
import com.sts.viktor_test.R
import com.sts.viktor_test.di.ViewModelProviderFactory
import com.sts.viktor_test.navigation.INavigationService
import dagger.android.support.DaggerFragment
import rx.Subscription
import rx.subscriptions.CompositeSubscription
import javax.inject.Inject


abstract class BaseFragment<VBinding: ViewBinding, VModel: ViewModel> : DaggerFragment(){

    open var useSharedViewModel : Boolean = false

    protected lateinit var viewModel: VModel
    protected abstract fun getViewModelClass() : Class<VModel>

    protected lateinit var binding: VBinding
    protected abstract fun getViewBinding(): VBinding

    lateinit var progressBar : ProgressDialog

    @Inject
    lateinit var factory: ViewModelProviderFactory

    @Inject
    lateinit var navigationService: INavigationService

    private val container = CompositeSubscription()

    private val mainActivity : MainActivity?
        get() {
            return try {
                requireActivity() as MainActivity
            }catch (e: Exception){
                null
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
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
        progressBar = ProgressDialog(requireContext(), R.style.MyTheme)
        progressBar.setCancelable(false)

        binding = getViewBinding()
        viewModel = if (useSharedViewModel){
            ViewModelProvider(requireActivity(), factory).get(getViewModelClass())
        }else {
            ViewModelProvider(this, factory).get(getViewModelClass())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        container.clear()
    }

    protected fun handleBackButton(body: () -> Unit){
        mainActivity?.onBackPressedDispatcher?.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                body()
            }
        })
    }

    fun setProgressBarVisible(isVisible : Boolean){
        if (isVisible) progressBar.show() else progressBar.dismiss()
    }

    fun Subscription.addToContainer() = container.add(this)

    fun clearSubscription(){
        container.clear()
    }
}
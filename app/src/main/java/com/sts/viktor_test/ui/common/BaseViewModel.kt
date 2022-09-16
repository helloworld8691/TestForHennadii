package com.sts.viktor_test.ui.common

import androidx.annotation.StringRes
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sts.viktor_test.R
import com.sts.viktor_test.network.exceptions.ForbiddenException
import com.sts.viktor_test.network.exceptions.NetworkException
import com.sts.viktor_test.network.exceptions.NoNetworkException
import com.sts.viktor_test.network.exceptions.ServerException
import com.sts.viktor_test.utils.Event
import com.sts.viktor_test.utils.rx.SchedulerProvider
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.functions.Consumer
import kotlinx.coroutines.CoroutineScope
import kotlin.coroutines.CoroutineContext

open class BaseViewModel constructor(
    coroutineContextProvider: CoroutineContextProvider,
    val mSchedulerProvider: SchedulerProvider,
    val mCompositeDisposable: CompositeDisposable
) : ViewModel(), CoroutineScope {

    val loadingViewVisible = MutableLiveData<Event<Boolean>>()
    val errorMessage = MutableLiveData<Event<Pair<Int, String>>>()

    fun showLoading() {loadingViewVisible.postValue(Event(true))}
    fun hideLoading() {loadingViewVisible.postValue(Event(false))}
    fun showErrorMessage(@StringRes messageResId : Int, message : String) {errorMessage.postValue(Event(Pair(messageResId, message)))}

    override val coroutineContext: CoroutineContext = coroutineContextProvider.io

    override fun onCleared() {
        super.onCleared()
        hideLoading()
        mCompositeDisposable.dispose()
    }

    protected open fun <T: Any> callInteract(
        observableMethod : Observable<T>,
        networkException : NetworkException = NetworkException(0, ""),
        consumer: (T) -> Unit){
        showLoading()
        mCompositeDisposable.add(
            observableMethod
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .doFinally { hideLoading() }
                .subscribe({
                    consumer(it)
                }, errorConsumer(networkException))
        )
    }

    private fun errorConsumer(networkException: NetworkException) : Consumer<Throwable>{
        return Consumer { throwable : Throwable ->
            if (throwable is NoNetworkException){
                showErrorMessage(R.string.network_error, "Please check your connectivity")
            }else if (throwable is ServerException ){
                showErrorMessage(R.string.network_error, "Cannot connect to server")
            }else if (throwable is ForbiddenException){
                throwable.message?.let { showErrorMessage(R.string.network_error, it) }
            }
        }
    }
}
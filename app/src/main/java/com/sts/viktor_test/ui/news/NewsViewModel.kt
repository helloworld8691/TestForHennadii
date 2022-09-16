package com.sts.viktor_test.ui.news

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sts.viktor_test.models.NewsModel
import com.sts.viktor_test.network.repository.GetTopHeadlineRepository
import com.sts.viktor_test.ui.common.BaseViewModel
import com.sts.viktor_test.ui.common.CoroutineContextProvider
import com.sts.viktor_test.utils.Event
import com.sts.viktor_test.utils.rx.SchedulerProvider
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

class NewsViewModel @Inject constructor(
    coroutineContextProvider: CoroutineContextProvider,
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    private val topHeadlineRepository: GetTopHeadlineRepository
) : BaseViewModel(coroutineContextProvider, schedulerProvider, compositeDisposable) {

    private val _newsList = MutableLiveData<List<NewsModel>>()
    val newsList : LiveData<List<NewsModel>> get() = _newsList

    init {
        getTopHeadlines()
    }

    private fun getTopHeadlines(){
        callInteract(topHeadlineRepository.getTopHeadlineRepository()) {
            _newsList.value = it.articles
        }
    }
}
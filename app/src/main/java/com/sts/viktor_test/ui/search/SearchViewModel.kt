package com.sts.viktor_test.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sts.viktor_test.MainActivity
import com.sts.viktor_test.models.TopHeadLines
import com.sts.viktor_test.network.repository.SearchRepository
import com.sts.viktor_test.sqlite.SqliteHelper
import com.sts.viktor_test.ui.common.BaseViewModel
import com.sts.viktor_test.ui.common.CoroutineContextProvider
import com.sts.viktor_test.utils.rx.SchedulerProvider
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    coroutineContextProvider: CoroutineContextProvider,
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    private val searchRepository: SearchRepository,
)  : BaseViewModel(coroutineContextProvider, schedulerProvider, compositeDisposable){

    private val _newsList = MutableLiveData<TopHeadLines>()
    val newsList : LiveData<TopHeadLines> get() = _newsList

    private val _searchHistory = MutableLiveData<ArrayList<String>>()
    val searchHistory : LiveData<ArrayList<String>> get() = _searchHistory

    fun search(searchKey : String, fromDate : String = "", toDate : String = "", searchIn : String = "", sortBy : String = "") {
        callInteract(searchRepository.search(searchKey, fromDate, toDate, searchIn, sortBy)) { it ->
            _newsList.value = it
        }
    }

    fun saveSearchKey(searchKey: String){
        MainActivity.sqliteHelper.addSearchHistory(searchKey)
    }

    fun readSearchHistory(){

        val tempSearchHistory = arrayListOf<String>()

        val cursor = MainActivity.sqliteHelper.getSearchHistory()
        cursor.moveToFirst()

        if (cursor.count == 0) return

        val searchKey = cursor.getString(cursor.getColumnIndex(SqliteHelper.COL_SEARCH_KEY))
        tempSearchHistory.add(searchKey)

        while (cursor.moveToNext()){
            val searchKey = cursor.getString(cursor.getColumnIndex(SqliteHelper.COL_SEARCH_KEY))
            tempSearchHistory.add(searchKey)
        }

        _searchHistory.value = tempSearchHistory

        cursor.close()
    }

    fun deleteItemInSearchHistory(position : Int){
        MainActivity.sqliteHelper.deleteSearchKey(_searchHistory.value!!.get(position))
        readSearchHistory()
    }
}
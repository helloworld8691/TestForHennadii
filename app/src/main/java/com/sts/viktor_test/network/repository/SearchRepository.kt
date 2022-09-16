package com.sts.viktor_test.network.repository

import com.sts.viktor_test.models.TopHeadLines
import com.sts.viktor_test.network.api_endpoint.ApiEndpointInterface
import io.reactivex.rxjava3.core.Observable

class SearchRepository(private val apiEndpointInterface: ApiEndpointInterface) {
    fun search(searchKey : String,
               fromDate : String = "",
               toDate : String = "",
               searchIn : String = "",
               sortBy : String = ""
    ) : Observable<TopHeadLines> = apiEndpointInterface.search(searchKey = searchKey, fromDate = fromDate, toDate = toDate, searchIn = searchIn, sortBy = sortBy)
}
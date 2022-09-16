package com.sts.viktor_test.network.repository

import com.sts.viktor_test.models.TopHeadLines
import com.sts.viktor_test.network.api_endpoint.ApiEndpointInterface
import io.reactivex.rxjava3.core.Observable

class GetTopHeadlineRepository(private val apiEndpointInterface: ApiEndpointInterface) {
    fun getTopHeadlineRepository() : Observable<TopHeadLines> = apiEndpointInterface.getTopHeadlines()
}
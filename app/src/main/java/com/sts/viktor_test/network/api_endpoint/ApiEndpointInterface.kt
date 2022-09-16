package com.sts.viktor_test.network.api_endpoint

import com.sts.viktor_test.di.module.NetworkModule
import com.sts.viktor_test.models.TopHeadLines
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiEndpointInterface {

    @GET(ApiEndpoint.TOP_HEADLINES)
    fun getTopHeadlines(@Query(NetworkModule.KEY_API_TOKEN) token : String = NetworkModule.API_TOKEN) : Observable<TopHeadLines>

    @GET(ApiEndpoint.SEARCH)
    fun search(@Query("q") searchKey : String,
               @Query(NetworkModule.KEY_API_TOKEN) token : String = NetworkModule.API_TOKEN,
               @Query("from") fromDate : String,
               @Query("to") toDate : String,
               @Query("in") searchIn : String,
               @Query("sortby") sortBy : String
    ) : Observable<TopHeadLines>
}
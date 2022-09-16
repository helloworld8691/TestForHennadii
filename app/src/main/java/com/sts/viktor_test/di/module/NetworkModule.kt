package com.sts.viktor_test.di.module

import android.content.Context
import com.sts.viktor_test.network.api_endpoint.ApiEndpoint
import com.sts.viktor_test.network.api_endpoint.ApiEndpointInterface
import com.sts.viktor_test.network.exceptions.NetworkResponseInterceptor
import com.sts.viktor_test.network.repository.GetTopHeadlineRepository
import com.sts.viktor_test.network.repository.SearchRepository
import dagger.Module
import dagger.Provides
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    companion object {
        private const val CALL_TIMEOUT = 1L
        private const val BASE_URL = "https://gnews.io/api/v4/"
        const val KEY_API_TOKEN = "token"
        const val API_TOKEN = "9025211538f909c9fa64bf94e71109a4"
    }

    @Provides
    @Singleton
    fun provideOkHttpClient() : OkHttpClient {
        return OkHttpClient.Builder()
            .callTimeout(CALL_TIMEOUT, TimeUnit.MINUTES)
            .addInterceptor(NetworkResponseInterceptor())
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient) : Retrofit {
        return Retrofit.Builder()
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(okHttpClient).build()
    }

    @Provides
    @Singleton
    fun provideApiEndpoint(retrofit: Retrofit) : ApiEndpointInterface {
        return retrofit.create(ApiEndpointInterface::class.java)
    }

    @Provides
    @Singleton
    fun getTopHeadlineRepository(apiEndpointInterface: ApiEndpointInterface) : GetTopHeadlineRepository {
        return GetTopHeadlineRepository(apiEndpointInterface)
    }

    @Provides
    @Singleton
    fun search(apiEndpointInterface: ApiEndpointInterface) : SearchRepository {
        return SearchRepository(apiEndpointInterface)
    }
}
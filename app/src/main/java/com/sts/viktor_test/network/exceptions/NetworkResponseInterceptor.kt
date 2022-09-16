package com.sts.viktor_test.network.exceptions

import okhttp3.Interceptor
import okhttp3.Response

class NetworkResponseInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request()).newBuilder().build()

        when (response.code()){
            in 500..599 -> throw  ServerException(response.code(), response.message())
            in 401..403 -> throw  ForbiddenException(response.code(), response.message())
        }

        return response
    }

}
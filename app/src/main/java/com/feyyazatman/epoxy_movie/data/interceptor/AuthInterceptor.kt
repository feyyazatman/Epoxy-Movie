package com.feyyazatman.epoxy_movie.data.interceptor

import com.feyyazatman.epoxy_movie.data.remote.utils.Constants
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request()

        val newUrl = requestBuilder.url.newBuilder()
            .addQueryParameter(Constants.API_KEY,Constants.API_KEY_VALUE)
            .build()

        val newRequest = requestBuilder.newBuilder().url(newUrl).build()

        return chain.proceed(newRequest)
    }
}
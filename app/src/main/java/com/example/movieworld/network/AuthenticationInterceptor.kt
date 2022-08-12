package com.example.movieworld.network

import okhttp3.Interceptor
import okhttp3.Response


private const val apikey = "0342cc129b7a51435cf9900445392f2c"
private const val API_KEY = "api_key"

class AuthenticationInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val newRequest = request.url.newBuilder()
            .addQueryParameter(API_KEY, apikey).build()
        return chain.proceed(request.newBuilder().url(newRequest).build())
    }
}
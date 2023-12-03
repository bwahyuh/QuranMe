package com.example.quranme.repo.remote.network

import com.example.quranme.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class GptInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val newRequest = originalRequest.newBuilder()
            .header("Authorization", "Bearer ${BuildConfig.GPT_API_KEY}")
            .build()
        return chain.proceed(newRequest)
    }
}

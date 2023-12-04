package com.example.quranme.repo.remote.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.logging.HttpLoggingInterceptor


object ChatService {
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY // Set log level to BODY
    }

    private val httpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor) // Add the logging interceptor
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.openai.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClient) // Set the OkHttpClient with logging interceptor
        .build()

    val openAIApi: OpenAIApi = retrofit.create(OpenAIApi::class.java)

    private val hadithRetrofit = Retrofit.Builder()
        .baseUrl("https://api.hadith.gading.dev/") // Replace with actual base URL
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val hadithApi: HadithApi = hadithRetrofit.create(HadithApi::class.java)
}


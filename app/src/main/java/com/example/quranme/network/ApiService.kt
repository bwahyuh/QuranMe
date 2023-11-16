package com.example.quranme.network

import com.example.quranme.model.Surat
import com.example.quranme.model.SuratResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {


    @GET("api/v2/surat/{number}")
    suspend fun getSuratByNumber(@Path("number") number: Int): Surat

    @GET("api/v2/surat")
    suspend fun getAllSurat(): SuratResponse

    companion object {
        private var apiService: ApiService? = null

        fun getInstance(): ApiService {
            if (apiService == null) {
                apiService = Retrofit.Builder()
                    .baseUrl("https://equran.id/") // The base URL is up to /api/v2/
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(ApiService::class.java)
            }
            return apiService!!
        }
    }
}

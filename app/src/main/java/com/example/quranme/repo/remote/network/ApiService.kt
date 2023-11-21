package com.example.quranme.repo.remote.network

import android.content.Context
import com.example.quranme.R
import com.example.quranme.data.model.Surat
import com.example.quranme.data.model.SuratResponse
import com.google.gson.Gson

class ApiService(private val context: Context) {


    fun getAllSurat(): SuratResponse {
        val inputStream = context.resources.openRawResource(R.raw.surah)
        val jsonString = inputStream.bufferedReader().use { it.readText() }
        return Gson().fromJson(jsonString, SuratResponse::class.java)
    }

    companion object {
        private var apiService: ApiService? = null

        fun getInstance(context: Context): ApiService {
            if (apiService == null) {
                apiService = ApiService(context)
            }
            return apiService!!
        }
    }
}

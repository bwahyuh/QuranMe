package com.example.quranme.repo.remote.network

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface HadithApi {
    @GET("/books")
    suspend fun getBooks(): List<String>

    @GET("/books/{name}")
    suspend fun getHadithsByRange(
        @Path("name") bookName: String,
        @Query("range") range: String
    ): List<Hadith>

    @GET("/books/{name}/{number}")
    suspend fun getSpecificHadith(
        @Path("name") bookName: String,
        @Path("number") number: Int
    ): Hadith
}

data class Hadith(val content: String) // Replace with actual response fields

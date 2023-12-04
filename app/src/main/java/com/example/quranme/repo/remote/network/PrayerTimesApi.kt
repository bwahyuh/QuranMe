package com.example.quranme.repo.remote.network
import com.example.quranme.data.model.PrayerTimesResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Response

interface PrayerTimesApi {
    @GET("v1/timingsByCity")
    suspend fun getPrayerTimesByCity(
        @Query("city") city: String,
        @Query("country") country: String,
        @Query("method") method: Int
    ): Response<PrayerTimesResponse>
}

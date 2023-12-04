package com.example.quranme.repo.remote.network

// Make sure to import your PrayerTimesApi interface and the Response model
import com.example.quranme.repo.remote.network.PrayerTimesApi
import com.example.quranme.data.model.PrayerTimesResponse
import com.example.quranme.data.model.Timings
import retrofit2.Response

class PrayerTimesRepository(private val api: PrayerTimesApi) {
    suspend fun getPrayerTimes(city: String, country: String, method: Int): Timings {
        val response = api.getPrayerTimesByCity(city, country, method)
        if(response.isSuccessful && response.body() != null) {
            return response.body()!!.data.timings
        } else {
            throw Exception("Failed to fetch prayer times")
        }
    }
}

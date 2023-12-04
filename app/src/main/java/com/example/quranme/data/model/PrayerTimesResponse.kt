package com.example.quranme.data.model

data class PrayerTimesResponse(
    val code: Int,
    val status: String,
    val data: Data
)

data class Data(
    val timings: Timings
)

data class Timings(
    val Fajr: String,
    val Sunrise: String,
    val Dhuhr: String,
    val Asr: String,
    val Maghrib: String,
    val Isha: String
)


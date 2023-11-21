package com.example.quranme.data.model

import com.google.gson.annotations.SerializedName

data class JuzData(
    @SerializedName("data")
    val data: List<Juz>
)

data class Juz(
    @SerializedName("index")
    val index: String,
    @SerializedName("start")
    val start: SurahInfo,
    @SerializedName("end")
    val end: SurahInfo
)

data class SurahInfo(
    @SerializedName("index")
    val index: String,
    @SerializedName("verse")
    val verse: String,
    @SerializedName("name")
    val name: String
)

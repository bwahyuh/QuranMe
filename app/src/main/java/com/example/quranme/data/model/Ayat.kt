package com.example.quranme.data.model

import com.google.gson.annotations.SerializedName

data class Ayat(
    @SerializedName("nomor") val number: Int,
    @SerializedName("ar") val arabicText: String,
    @SerializedName("id") val translationId: String,
    @SerializedName("tr") val transliteration: String? = null // Optional transliteration
)

// This represents the list of Ayahs for a Surah
data class AyatData(
    @SerializedName("surat") val surat: List<List<Ayat>?>
)

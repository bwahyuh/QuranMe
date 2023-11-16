package com.example.quranme.model

import com.google.gson.annotations.SerializedName

data class SuratResponse(
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: List<Surat>
)

data class Surat(
    @SerializedName("nomor") val nomor: Int,
    @SerializedName("nama") val nama: String,
    @SerializedName("namaLatin") val namaLatin: String,
    @SerializedName("jumlahAyat") val jumlahAyat: Int,
    @SerializedName("tempatTurun") val tempatTurun: String,
    @SerializedName("arti") val arti: String,
    @SerializedName("deskripsi") val deskripsi: String,
    @SerializedName("audioFull") val audioFull: Map<String, String>
)

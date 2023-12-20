package com.example.quranme.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Bookmark(
    @PrimaryKey val id: Int,
    val surahNumber: Int,
    val ayatNumber: Int
)

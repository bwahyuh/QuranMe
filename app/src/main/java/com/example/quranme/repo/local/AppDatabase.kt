package com.example.quranme.repo.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.quranme.data.model.Bookmark

@Database(entities = [Bookmark::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun bookmarkDao(): BookmarkDao
}

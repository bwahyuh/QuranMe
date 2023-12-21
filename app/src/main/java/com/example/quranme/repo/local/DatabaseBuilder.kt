package com.example.quranme.repo.local

import android.content.Context
import androidx.room.Room

object DatabaseBuilder {
    private var instance: AppDatabase? = null

    fun getDatabase(context: Context): AppDatabase {
        if (instance == null) {
            synchronized(AppDatabase::class) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "quranme-database"
                )
                    .fallbackToDestructiveMigration() // Add this line
                    .build()
            }
        }
        return instance!!
    }
}

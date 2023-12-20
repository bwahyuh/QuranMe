package com.example.quranme.repo.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.quranme.data.model.Bookmark

@Dao
interface BookmarkDao {
    @Query("SELECT * FROM bookmark WHERE id = 0")
    suspend fun getBookmark(): Bookmark?

    @Insert
    suspend fun insertBookmark(bookmark: Bookmark)
}

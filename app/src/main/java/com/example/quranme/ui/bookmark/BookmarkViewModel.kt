package com.example.quranme.ui.bookmark

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.quranme.data.model.Bookmark
import com.example.quranme.repo.local.BookmarkDao

class BookmarkViewModel(private val bookmarkDao: BookmarkDao) : ViewModel() {
    val lastBookmark: LiveData<Bookmark?> = bookmarkDao.getLastBookmark()
}

class BookmarkViewModelFactory(private val bookmarkDao: BookmarkDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BookmarkViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BookmarkViewModel(bookmarkDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}


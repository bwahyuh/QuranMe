package com.example.quranme.ui.quran

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.quranme.R
import com.example.quranme.data.model.Ayat
import com.example.quranme.data.model.AyatData
import com.example.quranme.data.model.Bookmark
import com.example.quranme.data.model.Surat
import com.example.quranme.data.model.SuratResponse
import com.example.quranme.repo.local.DatabaseBuilder
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.math.min

class QuranViewModel(private val context: Context) : ViewModel() {
    private val _ayahsForSurah = MutableLiveData<List<Ayat>>()
    val ayahsForSurah: LiveData<List<Ayat>> = _ayahsForSurah

    private val _surahs = MutableLiveData<List<Surat>>()
    val surahs: LiveData<List<Surat>> = _surahs
    private var lastAyahLoaded = 0 // Tracks the index of the last Ayah loaded
    private val pageSize = 10 // Number of Ayahs to load each time
    // Database and BookmarkDao instance
    private val db = DatabaseBuilder.getDatabase(context)
    private val bookmarkDao = db.bookmarkDao()
    // LiveData for bookmarks
    val bookmarks: LiveData<List<Bookmark>> = bookmarkDao.getAllBookmarks()
    init {
        loadSurahs()
    }

    private fun loadSurahs() {
        viewModelScope.launch {
            try {
                val inputStream = context.resources.openRawResource(R.raw.surah)
                val jsonString = inputStream.bufferedReader().use { it.readText() }
                val gson = Gson()
                val suratResponse = gson.fromJson(jsonString, SuratResponse::class.java)
                _surahs.postValue(suratResponse.data)
            } catch (e: Exception) {
                Log.e("QuranViewModel", "Error loading Surahs: ${e.message}")
            }
        }
    }

    // Method to load Ayahs for a specific Surah
    fun loadAyatForSurah(surahNumber: Int) {
        viewModelScope.launch {
            val ayahs = withContext(Dispatchers.IO) {
                val inputStream = context.resources.openRawResource(R.raw.ayat)
                val jsonString = inputStream.bufferedReader().use { it.readText() }
                val gson = Gson()
                val ayatDataWrapper = gson.fromJson(jsonString, AyatDataWrapper::class.java)
                ayatDataWrapper.surat.getOrNull(surahNumber)
            } ?: emptyList()
            _ayahsForSurah.postValue(ayahs)
        }
    }

    // Data classes...
    data class AyatDataWrapper(
        val surat: List<List<Ayat>?>
    )

    class QuranViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(QuranViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return QuranViewModel(context) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
    // Function to load next set of Ayahs for the specified Surah
    // Function to load next set of Ayahs for the specified Surah
    fun loadNextAyahs(surahNumber: Int) {
        viewModelScope.launch {
            val newAyahs = loadAyahs(surahNumber, lastAyahLoaded, pageSize)
            lastAyahLoaded += newAyahs.size // Update the index of the last Ayah loaded

            val currentAyahs = _ayahsForSurah.value.orEmpty()
            _ayahsForSurah.postValue(currentAyahs + newAyahs)
        }
    }


    // Method to save a bookmark
    fun saveBookmark(surahNumber: Int, ayatNumber: Int) {
        viewModelScope.launch {
            val bookmark = Bookmark(surahNumber = surahNumber, ayatNumber = ayatNumber)
            bookmarkDao.insertBookmark(bookmark)
        }
    }

    // Function to load Ayahs from your data source
    private suspend fun loadAyahs(surahNumber: Int, start: Int, count: Int): List<Ayat> {
        // This is a placeholder logic to simulate Ayah loading
        // You should replace this with your actual data loading logic
        return withContext(Dispatchers.IO) {
            // Simulate fetching Ayahs from a data source
            // Example: Fetching Ayahs from a JSON file, database, API, etc.
            // This is just a placeholder and does not represent actual data fetching
            val loadedAyahs = mutableListOf<Ayat>()
            for (i in start until (start + count)) {
                loadedAyahs.add(Ayat(i, "Ayah $i Arabic Text", "Ayah $i Translation"))
            }
            loadedAyahs
        }
    }

    fun deleteBookmark(bookmark: Bookmark) {
        viewModelScope.launch {
            bookmarkDao.deleteBookmark(bookmark)
        }
    }


}


    class QuranViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(QuranViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return QuranViewModel(context) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

    // Rename the method to avoid clash
//    fun loadBookmark(): LiveData<Bookmark?> {
//        return liveData {
//            emit(bookmarkDao.getBookmark())
//        }
//    }








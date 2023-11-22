package com.example.quranme.ui.quran

import android.content.Context
import androidx.lifecycle.*
import com.example.quranme.R
import com.example.quranme.compose.page.Ayah
import com.example.quranme.data.model.Ayat
import com.example.quranme.data.model.AyatData
import com.google.gson.Gson
import kotlinx.coroutines.launch

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import androidx.lifecycle.ViewModelProvider
import com.example.quranme.data.model.Surat
import com.example.quranme.data.model.SuratResponse
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken

class QuranViewModel(private val context: Context) : ViewModel() {
    private val _ayahsForSurah = MutableLiveData<List<Ayat>?>()
    val ayahsForSurah: LiveData<List<Ayat>?> = _ayahsForSurah

    private val _surahs = MutableLiveData<List<Surat>>()
    val surahs: LiveData<List<Surat>> = _surahs

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
                // Handle exceptions
            }
        }
    }

    fun loadAyatForSurah(surahNumber: Int) {
        viewModelScope.launch {
            try {
                val inputStream = context.resources.openRawResource(R.raw.ayat)
                val jsonString = inputStream.bufferedReader().use { it.readText() }
                val gson = Gson()
                val ayatDataWrapper = gson.fromJson(jsonString, AyatDataWrapper::class.java)
                val ayatData = ayatDataWrapper.surat[surahNumber] ?: emptyList()
                _ayahsForSurah.postValue(ayatData)
            } catch (e: JsonSyntaxException) {
                // Handle the JSON parsing error
            } catch (e: Exception) {
                // Handle other exceptions
            }
        }
    }

    data class AyatDataWrapper(
        val surat: Array<List<Ayat>?>
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
}


package com.example.quranme.ui.quran

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.quranme.data.model.JuzData
import com.example.quranme.data.model.Surat
import com.example.quranme.data.model.SuratResponse
import kotlinx.coroutines.launch
import com.google.gson.Gson
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.quranme.R
import com.example.quranme.data.model.Juz

class JuzViewModel(private val context: Context) : ViewModel() {
    val juzData = MutableLiveData<JuzData?>()
    val isLoading = MutableLiveData<Boolean>()
    val error = MutableLiveData<String>()
    private val _allSurahs = MutableLiveData<SuratResponse?>()
    val allSurahs: LiveData<SuratResponse?> = _allSurahs
    private val _surahsByJuz = MutableLiveData<Map<Int, List<Surat>>>()
    val surahsByJuz: LiveData<Map<Int, List<Surat>>> = _surahsByJuz
    init {
        viewModelScope.launch {
            val juzDataLoaded = loadJuzData()  // Attempt to load Juz data
            if (juzDataLoaded != null) {
                loadAllSurahs()  // Load Surah data only if Juz data is loaded successfully
            }
        }
    }


    private suspend fun loadJuzData(): JuzData? {
        try {
            Log.d("JuzViewModel", "Loading Juz data...")
            isLoading.postValue(true)
            val inputStream = context.resources.openRawResource(R.raw.juz)
            val jsonString = inputStream.bufferedReader().use { it.readText() }
            val data = Gson().fromJson(jsonString, JuzData::class.java)
            juzData.postValue(data)
            Log.d("JuzViewModel", "Juz data loaded successfully.")

            return data  // Return the loaded data
        } catch (e: Exception) {
            error.postValue("Error: ${e.message}")
            Log.e("JuzViewModel", "Error loading Juz data", e)
        } finally {
            isLoading.postValue(false)
        }
        return null
    }




    fun loadAllSurahs() {
        viewModelScope.launch {
            try {
                Log.d("SuratViewModel", "Loading all Surah data...")
                val inputStream = context.resources.openRawResource(R.raw.surah)
                val jsonString = inputStream.bufferedReader().use { it.readText() }
                val data = Gson().fromJson(jsonString, SuratResponse::class.java)
                _allSurahs.postValue(data)
                Log.d("SuratViewModel", "All Surah data loaded successfully.")
            } catch (e: Exception) {
                error.postValue("Error: ${e.message}")
                Log.e("SuratViewModel", "Error loading all Surah data", e)
            }
            juzData.value?.let { juzData ->
                processJuzData(juzData)
            }
        }
    }


    private fun processJuzData(juzData: JuzData?) {
        val juzToSurahMap = mutableMapOf<Int, List<Surat>>()

        val allSurahsResponse = _allSurahs.value
        val allSurahs = allSurahsResponse?.data ?: return  // If surah data is null, return early

        // Process the Juz data to map each Juz index to its Surahs
        juzData?.data?.forEach { juz ->
            val startSurahIndex = juz.start.index.toInt()
            val endSurahIndex = juz.end.index.toInt()

            val surahsInJuz = allSurahs.filter { surat ->
                val suratIndex = surat.nomor
                suratIndex >= startSurahIndex && (suratIndex <= endSurahIndex || startSurahIndex == endSurahIndex)
            }

            juzToSurahMap[juz.index.toInt()] = surahsInJuz
        }

        _surahsByJuz.postValue(juzToSurahMap)
    }
    fun constructSurahsByJuzMap() {
        val juzDataValue = juzData.value
        val allSurahsValue = _allSurahs.value?.data ?: emptyList()

        val map = juzDataValue?.data?.associate { juz ->
            // Here we determine the range of Surahs for each Juz
            val startSurahIndex = juz.start.index.toInt()
            val endSurahIndex = juz.end.index.toInt()

            // Filter the Surahs based on whether their 'nomor' falls within the range
            val surahsInJuz = allSurahsValue.filter { surat ->
                surat.nomor in startSurahIndex..endSurahIndex
            }
            juz.index.toInt() to surahsInJuz
        } ?: emptyMap()

        _surahsByJuz.value = map
    }

    fun createSurahsByJuzMap(juzData: List<Juz>, surahs: List<Surat>): Map<Int, List<Surat>> {
        val map = mutableMapOf<Int, List<Surat>>()

        juzData.forEach { juz ->
            val startSurahIndex = juz.start.index.toInt()
            val endSurahIndex = juz.end.index.toInt()
            val surahsInJuz = surahs.filter { surah ->
                val surahIndex = surah.nomor
                surahIndex in startSurahIndex..endSurahIndex
            }
            map[juz.index.toInt()] = surahsInJuz
        }

        return map
    }

}

class JuzViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(JuzViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return JuzViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

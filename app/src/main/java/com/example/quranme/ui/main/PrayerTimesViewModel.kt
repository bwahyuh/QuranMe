package com.example.quranme.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateOf
// Import the repository class and data models
import com.example.quranme.repo.remote.network.PrayerTimesRepository
import com.example.quranme.data.model.Timings
import com.example.quranme.repo.remote.network.RetrofitInstance

class PrayerTimesViewModel : ViewModel() {
    var prayerTimes = mutableStateOf(Timings("", "", "", "", "", ""))
    private val repository = PrayerTimesRepository(RetrofitInstance.api)

    init {
        viewModelScope.launch {
            try {
                prayerTimes.value = repository.getPrayerTimes("Sleman", "Indonesia", 2) // Replace with actual values
            } catch (e: Exception) {
                // Handle exceptions
            }
        }
    }
}

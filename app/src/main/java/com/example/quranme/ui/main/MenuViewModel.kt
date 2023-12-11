package com.example.quranme.ui.main

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import java.text.SimpleDateFormat
import java.util.*

open class MenuViewModel(application: Application) : AndroidViewModel(application) {
    private var fusedLocationProviderClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(application)

    val locationData = MutableLiveData<String>()
    val prayerTimeData = MutableLiveData<String>()

    init {
        fetchLocationAndTime()
    }

    @SuppressLint("MissingPermission")
    private fun fetchLocationAndTime() {
        fusedLocationProviderClient.lastLocation.addOnSuccessListener { location ->
            location?.let {
                // For demonstration, we are just posting the formatted current system time.
                val currentTime = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())
                prayerTimeData.postValue(currentTime)

                val locationString = "Lat: ${location.latitude}, Lng: ${location.longitude}"
                locationData.postValue(locationString)
            }
        }
    }
}

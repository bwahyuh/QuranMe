package com.example.quranme.compose.page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.MaterialTheme
import com.example.quranme.compose.ui.components.PrayerTimeCard
// Import your data models
import com.example.quranme.data.model.Timings
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.quranme.compose.ui.components.BottomBar

@Composable
fun PrayerTimesScreen(prayerTimes: Timings) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            PrayerTimeCard(prayerName = "Fajr", prayerTime = prayerTimes.Fajr)
            PrayerTimeCard(prayerName = "Sunrise", prayerTime = prayerTimes.Sunrise)
            PrayerTimeCard(prayerName = "Dhuhr", prayerTime = prayerTimes.Dhuhr)
            PrayerTimeCard(prayerName = "Asr", prayerTime = prayerTimes.Asr)
            PrayerTimeCard(prayerName = "Maghrib", prayerTime = prayerTimes.Maghrib)
            PrayerTimeCard(prayerName = "Isha", prayerTime = prayerTimes.Isha)
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(0.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Top Bar with back button and title
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 32.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

            }

            Spacer(modifier = Modifier.weight(1f))
            // BottomBar component here
            com.example.quranme.compose.ui.components.BottomBar(
            )

        }
    }
}
@Preview(showBackground = true)
@Composable
fun PrayerTimesScreenPreview() {
    // Example timings to pass to the PrayerTimesScreen
    val exampleTimings = Timings(
        Fajr = "05:00 AM",
        Sunrise = "06:30 AM",
        Dhuhr = "12:00 PM",
        Asr = "03:30 PM",
        Maghrib = "06:00 PM",
        Isha = "07:30 PM"
    )

    PrayerTimesScreen(prayerTimes = exampleTimings)
}




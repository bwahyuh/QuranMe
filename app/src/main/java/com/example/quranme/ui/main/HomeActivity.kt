package com.example.quranme.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.quranme.compose.ui.components.BottomBar
import com.example.quranme.compose.ui.components.TopBar
import com.example.quranme.compose.ui.components.Menu
import com.example.quranme.compose.ui.components.LastReadSection
import com.example.quranme.compose.ui.theme.QuranMeTheme
import com.example.quranme.compose.page.PrayerTimeList
import com.example.quranme.compose.page.PrayerTimesScreen
import com.example.quranme.ui.quran.CariBacaanActivity

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                HomePage()
            }
        }
    }

    @Composable
    fun HomePage() {
        val viewModel: PrayerTimesViewModel by viewModels()
        Column {
            TopBar(userName = "Tanvir Ahassan")
            Menu(
                currentLocation = "Your Location",
                currentTime = "15:00 WIB",
                onMenuSelected = { menuTitle ->
                    when (menuTitle) {
                        "Al-Qur'an", "Quiz", "Bookmark" -> navigateToCariBacaanActivity()
                        "Settings" -> navigateToSettingScreen()
                    }
                }
            )
            LastReadSection(
                lastSurahName = "Al-Fatihah",
                lastAyahNumber = 1
            )
            PrayerTimeList(prayerTimes = viewModel.prayerTimes.value)
            // Add PrayerTimeList and BottomNavigationBar if needed
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

    private fun navigateToCariBacaanActivity() {
        val intent = Intent(this, CariBacaanActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToSettingScreen() {
        val intent = Intent(this, SettingsActivity::class.java)
        startActivity(intent)
    }
}
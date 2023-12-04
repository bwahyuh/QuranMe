package com.example.quranme.ui.main
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import androidx.lifecycle.viewmodel.compose.viewModel
// Import your Composable functions and ViewModel
import com.example.quranme.compose.page.PrayerTimesScreen
import com.example.quranme.ui.main.PrayerTimesViewModel

class ScheduleActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: PrayerTimesViewModel by viewModels()
        setContent {
            MaterialTheme {
                PrayerTimesScreen(prayerTimes = viewModel.prayerTimes.value)
            }
        }
    }
}
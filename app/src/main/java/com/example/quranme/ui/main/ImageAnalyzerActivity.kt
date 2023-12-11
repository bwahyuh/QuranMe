package com.example.quranme.ui.main
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.quranme.compose.page.ImageLabelingScreen
// Import your Composable functions and ViewModel
import com.example.quranme.compose.page.PrayerTimesScreen
import com.example.quranme.ui.main.PrayerTimesViewModel

class ImageAnalyzerActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                ImageLabelingScreen(NavController(LocalContext.current))

            }
        }
    }
}
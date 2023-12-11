package com.example.quranme.ui.quran

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.example.quranme.compose.ui.theme.QuranMeTheme
import com.example.quranme.compose.page.QuranReader

class QuranActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        val surahNumber = intent.getIntExtra("SURAH_NUMBER", 1) // Default to 1 if not found

        val viewModel = ViewModelProvider(this, QuranViewModel.QuranViewModelFactory(this))
            .get(QuranViewModel::class.java)
        viewModel.loadAyatForSurah(surahNumber)

        setContent {
            QuranMeTheme {
                QuranReaderScreen(viewModel, surahNumber)
            }
        }
    }

    @Composable
    fun QuranReaderScreen(viewModel: QuranViewModel, surahNumber: Int) {
        val context = LocalContext.current
        val ayahs = viewModel.ayahsForSurah.observeAsState(listOf()).value

        // Fetch the Surat object for the given surah number
        val audioMap = viewModel.surahs.observeAsState(listOf()).value
            .find { it.nomor == surahNumber }
            ?.audioFull
            // Transform the map keys from String to Int
            ?.mapKeys { it.key.toIntOrNull() ?: 0 } ?: emptyMap()

        // Menggunakan Column untuk mengatur tata letak
        Column(modifier = Modifier.fillMaxSize()) {
            // Jika ada ayat, tampilkan QuranReader
            if (ayahs != null) {
                QuranReader(ayahs, audioMap, context)
            }

            // BottomBar ditempatkan di bagian bawah Column
            com.example.quranme.compose.ui.components.BottomBar(NavController(LocalContext.current))
        }
    }


}

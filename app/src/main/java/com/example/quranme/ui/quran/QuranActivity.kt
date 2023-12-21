package com.example.quranme.ui.quran

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelProvider
import com.example.quranme.compose.ui.theme.QuranMeTheme
import com.example.quranme.compose.page.QuranReader
import com.example.quranme.compose.ui.components.SurahDescriptionCard
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map

class QuranActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val surahNumber = intent.getIntExtra("SURAH_NUMBER", 1)

        val viewModel = ViewModelProvider(this, QuranViewModel.QuranViewModelFactory(this))
            .get(QuranViewModel::class.java)

        // Ensure this function is called to load the Ayahs
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
        val listState = rememberLazyListState()
        val ayahs = viewModel.ayahsForSurah.observeAsState(initial = listOf()).value
        val bookmarks = viewModel.bookmarks.observeAsState(initial = listOf()).value
        val surahInfo = viewModel.surahs.value?.find { it.nomor == surahNumber }

        Column {
            // Display Surah information
            surahInfo?.let {
                SurahDescriptionCard(surah = it)
            }

            // Quran reader with Ayahs
            QuranReader(
                ayahs = ayahs,
                audioMap = surahInfo?.audioFull?.mapKeys { it.key.toIntOrNull() ?: 0 } ?: emptyMap(),
                context = context,
                bookmarks = bookmarks,
                onBookmark = { ayatNumber ->
                    viewModel.saveBookmark(surahNumber, ayatNumber)
                },
                listState = listState
            )
        }
    }
}
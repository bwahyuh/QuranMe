package com.example.quranme.ui.bookmark

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.quranme.ui.quran.QuranViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.quranme.compose.page.BookmarkPage
import com.example.quranme.ui.quran.QuranActivity

class BookmarkActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = ViewModelProvider(this, QuranViewModel.QuranViewModelFactory(this))
            .get(QuranViewModel::class.java)

        setContent {
            BookmarkPage(viewModel = viewModel) { surahNumber, ayatNumber ->
                // Navigate to the selected Surah and Ayat
                val intent = Intent(this, QuranActivity::class.java).apply {
                    putExtra("SURAH_NUMBER", surahNumber)
                    putExtra("AYAT_NUMBER", ayatNumber)
                }
                startActivity(intent)
            }
        }
    }
}

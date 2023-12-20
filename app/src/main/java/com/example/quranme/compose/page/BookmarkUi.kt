package com.example.quranme.compose.page

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.quranme.ui.quran.QuranViewModel

@Composable
fun BookmarkPage(viewModel: QuranViewModel = viewModel(), navigateToSurah: (Int, Int) -> Unit) {
    val bookmark = viewModel.loadBookmark().observeAsState().value

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Your Bookmark", style = MaterialTheme.typography.headlineSmall)

        if (bookmark != null) {
            Spacer(modifier = Modifier.height(20.dp))
            Text("Surah Number: ${bookmark.surahNumber}")
            Text("Ayat Number: ${bookmark.ayatNumber}")

            Spacer(modifier = Modifier.height(20.dp))
            Button(onClick = { navigateToSurah(bookmark.surahNumber, bookmark.ayatNumber) }) {
                Text("Go to Bookmark")
            }
        } else {
            Text("No bookmark saved.")
        }
    }
}


package com.example.quranme.compose.page

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.quranme.compose.ui.navigation.TopBar
import com.example.quranme.data.model.Bookmark
import com.example.quranme.data.model.Surat
import com.example.quranme.ui.quran.QuranViewModel
import java.text.SimpleDateFormat
import java.util.*
@Composable
fun BookmarkPage(viewModel: QuranViewModel = viewModel(), navigateToSurah: (Int, Int) -> Unit) {
    val bookmarks = viewModel.bookmarks.observeAsState(initial = listOf())
    val surahs = viewModel.surahs.observeAsState(initial = listOf())

    LazyColumn {
        items(bookmarks.value) { bookmark ->
            val surah = surahs.value.find { it.nomor == bookmark.surahNumber }
            BookmarkItem(bookmark, surah, viewModel, navigateToSurah)
        }
    }
}




@Composable
fun BookmarkItem(
    bookmark: Bookmark,
    surah: Surat?,
    viewModel: QuranViewModel,
    navigateToSurah: (Int, Int) -> Unit
) {
    val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
    val bookmarkDate = dateFormat.format(bookmark.dateAdded)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { navigateToSurah(bookmark.surahNumber, bookmark.ayatNumber) }
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.weight(1f) // Align the content to the left
            ) {
                Text("Surah: ${surah?.namaLatin ?: "Unknown"} (${bookmark.surahNumber})")
                Text("Ayat: ${bookmark.ayatNumber}")
                Text("Date: $bookmarkDate")
            }
            IconButton(
                onClick = {
                    viewModel.deleteBookmark(bookmark)
                },
                modifier = Modifier.align(Alignment.CenterVertically) // Align the icon to the right
            ) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete Bookmark")
            }
        }
    }
}






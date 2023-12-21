package com.example.quranme.compose.page

import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quranme.compose.ui.theme.QuranMeTheme
import com.example.quranme.data.model.Ayat
import com.example.quranme.data.model.Surat
import androidx.compose.ui.res.painterResource
import com.example.quranme.R
import com.example.quranme.data.model.Bookmark

@Composable
fun AyahItem(
    ayahNumber: Int,
    arabicText: String,
    translationText: String,
    audioUrl: String,
    onPlayAudio: (String) -> Unit,
    isBookmarked: Boolean,
    onBookmark: (Int) -> Unit
) {
    var bookmarked by remember { mutableStateOf(isBookmarked) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Number circle and ayah number
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(40.dp)
                    .background(MaterialTheme.colorScheme.primary, CircleShape)
            ) {
                Text(
                    text = ayahNumber.toString(),
                    style = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.onPrimary)
                )
            }

            // Icon row for audio, share, and bookmark
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { onPlayAudio(audioUrl) }) {
                    Icon(
                        Icons.Default.PlayArrow,
                        contentDescription = "Play Audio",
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }

                // Share Button
                IconButton(onClick = { /* TODO: handle share */ }) {
                    Icon(
                        Icons.Default.Share,
                        contentDescription = "Share",
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }

                IconButton(onClick = {
                    bookmarked = !bookmarked
                    onBookmark(ayahNumber)
                }) {
                    Icon(
                        painter = painterResource(id = if (bookmarked) R.drawable.bookmark_added else R.drawable.bookmark_border),
                        contentDescription = "Bookmark",
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Arabic text
        Text(
            text = arabicText,
            style = MaterialTheme.typography.headlineMedium.copy(
                fontSize = 32.sp,
                color = MaterialTheme.colorScheme.onBackground
            ),
            textAlign = TextAlign.Right,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(4.dp))

        // Translation text
        Text(
            text = translationText,
            style = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.onBackground)
        )

        Divider(color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.2f))
    }
}



@Composable
fun QuranReader(
    ayahs: List<Ayat>,
    audioMap: Map<Int, String>,
    context: Context,
    bookmarks: List<Bookmark>,
    onBookmark: (Int) -> Unit,
    listState: LazyListState
) {
    LazyColumn(state = listState) {
        items(ayahs) { ayah ->
            val audioUrl = audioMap[ayah.number.toInt()] ?: ""
            val isBookmarked = bookmarks.any { it.surahNumber == ayah.number && it.ayatNumber == ayah.number.toInt() }
            AyahItem(
                ayahNumber = ayah.number.toInt(),
                arabicText = ayah.arabicText,
                translationText = ayah.translationId,
                audioUrl = audioUrl,
                onPlayAudio = { url -> playAudio(url, context) },
                isBookmarked = isBookmarked,
                onBookmark = onBookmark
            )
        }
    }
}



private fun playAudio(url: String, context: Context) {
    if (url.isNotBlank()) {
        val mediaPlayer = MediaPlayer().apply {
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build()
            )
            setDataSource(context, Uri.parse(url))
            prepareAsync()
            setOnPreparedListener { start() }
        }
    }
}


// Mock data to represent the ayahs
val mockAyats = listOf(
    Ayat(1, "بِسْمِ اللَّهِ الرَّحْمَٰنِ الرَّحِيمِ", "[All] praise is [due] to Allah, Lord of the worlds -", ""),
    Ayat(2, "الرَّحْمَٰنِ الرَّحِيمِ", "The Entirely Merciful, the Especially Merciful,", "")
    // Add more Ayats here
)

//@Preview
//@Composable
//fun PreviewQuranReader() {
//    QuranMeTheme {
//        QuranReader(ayahs = mockAyats)
//    }
//}


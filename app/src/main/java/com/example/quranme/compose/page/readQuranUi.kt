package com.example.quranme.compose.page

import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.quranme.compose.ui.theme.QuranMeTheme
import com.example.quranme.data.model.Ayat
import com.example.quranme.data.model.Surat

@Composable
fun AyahItem(
    ayahNumber: Int,
    arabicText: String,
    translationText: String,
    audioUrl: String,
    onPlayAudio: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        // Ayah number
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
        Spacer(modifier = Modifier.height(8.dp))

        // Play Audio Button
        IconButton(onClick = { onPlayAudio(audioUrl) }) {
            Icon(Icons.Default.PlayArrow, contentDescription = "Play Audio")
        }

        // Arabic text
        Text(
            text = arabicText,
            style = MaterialTheme.typography.headlineMedium.copy(color = MaterialTheme.colorScheme.onBackground),
            textAlign = TextAlign.Right
        )
        Spacer(modifier = Modifier.height(4.dp))

        // Translation text
        Text(
            text = translationText,
            style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.onBackground)
        )

        Divider(
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.2f),
            modifier = Modifier.padding(vertical = 8.dp)
        )
    }
}


@Composable
fun QuranReader(ayahs: List<Ayat>, audioMap: Map<Int, String>, context: Context) {
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        ayahs.forEach { ayah ->
            val audioUrl = audioMap[ayah.number.toInt()] ?: ""
            AyahItem(
                ayahNumber = ayah.number.toInt(),
                arabicText = ayah.arabicText,
                translationText = ayah.translationId,
                audioUrl = audioUrl,
                onPlayAudio = { url ->
                    playAudio(url, context)
                }
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



// Mock data class for Ayah
data class Ayah(
    val nomor: Int,
    val ar: String,
    val id: String
)

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


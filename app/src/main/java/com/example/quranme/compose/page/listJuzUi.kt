package com.example.quranme.compose.page

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.quranme.compose.state.UiState
import com.example.quranme.data.model.Juz
import com.example.quranme.data.model.JuzData
import com.example.quranme.data.model.Surat

@Composable
fun JuzListView(
    juzData: JuzData?,
    surahsByJuzMap: Map<Int, List<Surat>>,
    onSurahClick: (Surat) -> Unit
) {


    LazyColumn {
        juzData?.data?.forEach { juz ->
            item {
                val surahsInJuz = surahsByJuzMap[juz.index.toInt()] ?: listOf()
                JuzItemView(juz, surahsInJuz, onSurahClick)
            }
        }
    }
}

@Composable
fun JuzItemView(
    juz: Juz,
    surahsInJuz: List<Surat>,
    onSurahClick: (Surat) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    LaunchedEffect(juz) {
        Log.d("JuzItemView", "Juz ${juz.index} has ${surahsInJuz.size} surahs")
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                expanded = !expanded
                Log.d("JuzItemView", "Juz ${juz.index} clicked. Expanded: $expanded")
            },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column {
            Text(
                text = "Juz ${juz.index}",
//                style = MaterialTheme.typography.h6,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                textAlign = TextAlign.Center
            )
            AnimatedVisibility(visible = expanded) {
                Column {
                    surahsInJuz.forEach { surah ->
                        SurahItem(surah, onSurahClick)
                    }
                }
            }
        }
    }
}

@Composable
fun SurahItem(surat: Surat, onSurahClick: (Surat) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .clickable { onSurahClick(surat) },
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = surat.nomor.toString(),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = surat.namaLatin,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = "${surat.tempatTurun} • ${surat.jumlahAyat} VERSES",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
                )
            }
            Text(
                text = surat.nama,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.End
            )
        }
    }
}

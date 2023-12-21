package com.example.quranme.compose.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LastReadSection(lastSurahName: String, lastAyahNumber: Int) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Terakhir dibaca", style = MaterialTheme.typography.titleLarge)
            Text(text = lastSurahName, style = MaterialTheme.typography.titleMedium)
            Text(text = "Ayat No: $lastAyahNumber", style = MaterialTheme.typography.bodyLarge)
        }
    }
}

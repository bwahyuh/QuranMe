package com.example.quranme.compose.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.quranme.R

@Composable
fun Menu(currentLocation: String, currentTime: String, onMenuSelected: (String) -> Unit) {
    Column(modifier = Modifier.fillMaxWidth()) {
        // Location and Time Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_location), // Replace with your location icon resource
                contentDescription = "Location",
                modifier = Modifier.padding(end = 8.dp)
            )
            Text(text = currentLocation)
            Spacer(modifier = Modifier.weight(1f))
            Text(text = currentTime)
        }

        Divider()

        // Menu Items
        val menuItems = listOf(
            "Al-Qur'an" to R.drawable.ic_quran,
            "Quiz" to R.drawable.ic_quiz,
            "Bookmark" to R.drawable.ic_bookmark,
            "Settings" to R.drawable.ic_settings
        )

        menuItems.forEach { (title, iconId) ->
            MenuItem(title = title, iconId = iconId, onMenuSelected = onMenuSelected)
            Divider()
        }
    }
}

@Composable
fun MenuItem(title: String, iconId: Int, onMenuSelected: (String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable(onClick = { onMenuSelected(title) }),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = iconId), // Replace with your icon resource
            contentDescription = title,
            modifier = Modifier.padding(end = 8.dp)
        )
        Text(text = title)
    }
}

package com.example.quranme.compose.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.quranme.R
import com.example.quranme.compose.page.SignUpScreen

@Composable
fun BottomBar(
    onHomeClick: () -> Unit = {}, // Placeholder for home click events
    onGPTClick: () -> Unit = {},  // Placeholder for GPT click events
    onScheduleClick: () -> Unit = {} // Placeholder for schedule click events
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.DarkGray) // Replace with your actual color
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Home Button
        IconButton(onClick = onHomeClick) {
            Image(
                painter = painterResource(id = R.drawable.home), // Replace with your actual home icon drawable resource
                contentDescription = "Home",
                modifier = Modifier.size(24.dp)
            )
        }

        // GPT Button
        IconButton(onClick = onGPTClick) {
            Image(
                painter = painterResource(id = R.drawable.gpt), // Replace with your actual GPT icon drawable resource
                contentDescription = "GPT",
                modifier = Modifier.size(40.dp)
            )
        }

        // Schedule Button
        IconButton(onClick = onScheduleClick) {
            Image(
                painter = painterResource(id = R.drawable.schedule), // Replace with your actual schedule icon drawable resource
                contentDescription = "Schedule",
                modifier = Modifier.size(24.dp)
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewBottomBar() {
    BottomBar()
}

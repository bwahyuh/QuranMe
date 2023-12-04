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
import androidx.compose.ui.platform.LocalContext
import android.content.Intent
import com.example.quranme.ui.chatbot.ChatbotActivity
import com.example.quranme.ui.main.HomeActivity
import com.example.quranme.ui.main.ScheduleActivity


@Composable
fun BottomBar(
    onHomeClick: () -> Unit = {}, // Placeholder for home click events
    onGPTClick: () -> Unit = {},  // Placeholder for GPT click events
    onScheduleClick: () -> Unit = {} // Placeholder for schedule click events
) {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.DarkGray) // Replace with your actual color
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Home Button
        IconButton(onClick = {
            // Menangani klik tombol home
            context.startActivity(Intent(context, HomeActivity::class.java))
        }) {
            Image(
                painter = painterResource(id = R.drawable.home), // Replace with your actual home icon drawable resource
                contentDescription = "Home",
                modifier = Modifier.size(24.dp)
            )
        }

        // GPT Button
        IconButton(onClick ={
            // Menangani klik tombol GPT
            context.startActivity(Intent(context, ChatbotActivity::class.java))
        }) {
            Image(
                painter = painterResource(id = R.drawable.gpt), // Replace with your actual GPT icon drawable resource
                contentDescription = "GPT",
                modifier = Modifier.size(40.dp)
            )
        }

        // Schedule Button
        IconButton(onClick = {
            // Menangani klik tombol GPT
            context.startActivity(Intent(context, ScheduleActivity::class.java))
        }) {
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

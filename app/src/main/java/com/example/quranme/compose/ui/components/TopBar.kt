package com.example.quranme.compose.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.quranme.R // Import your R file here

@Composable
fun TopBar(userName: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Assalamualaikum, $userName",
            textAlign = TextAlign.Start,
            modifier = Modifier.weight(1f)
        )
        Image(
            painter = painterResource(id = R.drawable.user_photo), // Replace with your actual user photo resource
            contentDescription = "User Photo",
            modifier = Modifier.padding(8.dp)
        )
    }
}

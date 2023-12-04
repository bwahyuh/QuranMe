package com.example.quranme.compose.page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.quranme.R
import com.example.quranme.compose.ui.components.BottomBar
@Composable
fun SettingsScreen(onBackClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black) // Mengubah latar belakang menjadi hitam
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Top Bar with back button and title
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBackClick) {
                Icon(
                    painter = painterResource(id = R.drawable.back), // Replace with your actual back icon drawable resource
                    contentDescription = "Back",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            }
            Spacer(modifier = Modifier.width(16.dp)) // Space between icon and title
            Text(
                text = "Settings",
                style = MaterialTheme.typography.headlineSmall,
                color = Color.White
            )
        }

        // Rest of the settings items
        SettingItem(title = "Account Setting", color = Color(0xFFA44AFF))
        Spacer(modifier = Modifier.height(16.dp))
        SettingItem(title = "Gaze Tracking", hasSwitch = true, color = Color(0xFFA44AFF))
        Spacer(modifier = Modifier.height(16.dp))
        SettingItem(title = "Privacy Policy", color = Color(0xFFA44AFF))
        Spacer(modifier = Modifier.height(16.dp))
        SettingItem(title = "Terms & Condition", color = Color(0xFFA44AFF))

    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Top Bar with back button and title
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

        }

        Spacer(modifier = Modifier.weight(1f))
        // BottomBar component here
        com.example.quranme.compose.ui.components.BottomBar(
            onHomeClick = { /* Handle home click */ },
            onGPTClick = { /* Handle GPT click */ },
            onScheduleClick = { /* Handle schedule click */ }
        )

    }
}

@Composable
fun SettingItem(title: String, hasSwitch: Boolean = false, color: Color) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            color = Color.White,
            modifier = Modifier
                .weight(1f)
                .padding(8.dp)
        )
        if (hasSwitch) {
            Switch(
                checked = false, // this should be a state in real app
                onCheckedChange = {},
                colors = SwitchDefaults.colors(
                    checkedThumbColor = MaterialTheme.colorScheme.secondary,
                    uncheckedThumbColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    checkedTrackColor = MaterialTheme.colorScheme.secondaryContainer,
                    uncheckedTrackColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f)
                )
            )
        }
    }
}


@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
fun SettingsScreenPreview() {
    MaterialTheme {
        SettingsScreen(onBackClick = {})
    }
}


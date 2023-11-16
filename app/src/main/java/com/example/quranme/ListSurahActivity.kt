package com.example.quranme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.quranme.ui.theme.QuranMeTheme
import com.example.quranme.model.Surat
import com.example.quranme.viewModel.SuratViewModel
import com.example.quranme.view.SurahItem
import com.example.quranme.utils.UiState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text

import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel



class ListSurahActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuranMeTheme {
                // Assuming the viewModel has been initialized at this point
                val viewModel: SuratViewModel = viewModel()

                // Your theme and other UI elements
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Column {
                        TopBar(onMenuClick = {}, onSearchClick = {})
                        GreetingSection(userName = "Muhammad")
                        LastReadSection(surahName = "Al-Fatihah", ayatNumber = 1)
                        TabsSection(tabs = listOf("Surah", "Juz", "Page"))
                        SurahList(viewModel = viewModel) // This will show the list
                    }
                }
            }
        }

    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun TopBar(onMenuClick: () -> Unit, onSearchClick: () -> Unit) {
        SmallTopAppBar(
            title = {
                Text(
                    text = "Quran App",
                    modifier = Modifier.fillMaxWidth(),
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    textAlign = TextAlign.Center
                )
            },
            navigationIcon = {
                IconButton(onClick = { onMenuClick() }) {
                    Icon(
                        imageVector = Icons.Filled.Menu,
                        contentDescription = "Menu",
                        tint = Color.White
                    )
                }
            },
            actions = {
                IconButton(onClick = { onSearchClick() }) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "Search",
                        tint = Color.White
                    )
                }
            },
            colors = TopAppBarDefaults.smallTopAppBarColors(
                containerColor = Color(0xFF040C23), // Match with your theme color
                titleContentColor = Color.White
            )
        )
    }

    @Composable
    fun GreetingSection(userName: String) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Asslamualaikum",
                style = MaterialTheme.typography.headlineSmall.copy(color = Color.White)
            )
            Text(
                text = userName,
                style = MaterialTheme.typography.titleLarge.copy(color = Color.White)
            )
        }
    }


    @Composable
    fun LastReadSection(surahName: String, ayatNumber: Int) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            shape = RoundedCornerShape(20.dp),
            color = MaterialTheme.colorScheme.primaryContainer
        ) {
            Column(
                modifier = Modifier
                    .background(color = Color(0xFF655BFF)) // This color is for illustration. Use your theme color
                    .padding(16.dp)
            ) {
                Text(
                    text = "Last Read",
                    style = MaterialTheme.typography.labelLarge.copy(color = Color.White),
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = surahName,
                    style = MaterialTheme.typography.headlineSmall.copy(color = Color.White),
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = "Ayat No: $ayatNumber",
                    style = MaterialTheme.typography.bodyMedium.copy(color = Color.White)
                )
            }
        }
    }


    @Composable
    fun TabsSection(tabs: List<String>) {
        var selectedTabIndex by remember { mutableStateOf(0) }

        TabRow(
            selectedTabIndex = selectedTabIndex,
            containerColor = Color(0xFF040C23), // Match with your theme color
            contentColor = Color.White
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index },
                    text = {
                        Text(
                            text = title,
                            color = if (selectedTabIndex == index) Color.White else Color.LightGray
                        )
                    }
                )
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        QuranMeTheme {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                Background()
            }
            Column {
                TopBar(onMenuClick = {}, onSearchClick = {})
                GreetingSection(userName = "Muhammad")
                LastReadSection(surahName = "Al-Fatihah", ayatNumber = 1)
                TabsSection(tabs = listOf("Surah", "Juz", "Page"))
            }
        }
    }

    @Composable
    fun Background() {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(0xFF040C23)) // Match with your theme color
        )
    }

    @Composable
    fun SurahList(viewModel: SuratViewModel) {
        val surahListState = viewModel.suratListResponse.observeAsState()

        when (val uiState = surahListState.value) {
            is UiState.Loading -> {
                Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator()
                }
            }
            is UiState.Success -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(vertical = 8.dp)
                ) {
                    items(uiState.data ?: emptyList()) { surat ->
                        SurahItem(surat = surat)
                    }
                }
            }
            is UiState.Error -> {
                Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                    Text(text = uiState.error ?: "Unknown error")
                }
            }
            null -> {
                Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator()
                }
            }
        }
    }



}

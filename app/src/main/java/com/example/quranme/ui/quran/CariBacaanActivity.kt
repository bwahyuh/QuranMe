package com.example.quranme.ui.quran

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quranme.compose.page.JuzListView
//import com.example.quranme.compose.page.ListJuz
import com.example.quranme.compose.page.SurahList
import com.example.quranme.compose.ui.theme.QuranMeTheme
import com.example.quranme.compose.state.UiState
import com.example.quranme.data.model.Juz
import com.example.quranme.data.model.JuzData
import com.example.quranme.data.model.Surat
import com.example.quranme.ui.quran.JuzViewModel
//import com.example.quranme.compose.page.JuzViewModel
import androidx.compose.runtime.livedata.observeAsState
import com.example.quranme.compose.ui.navigation.TopBar
import com.example.quranme.compose.ui.theme.fontFamily

class CariBacaanActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModelSurat: SuratViewModel by viewModels { SuratViewModelFactory(applicationContext) }
        val viewModelJuz: JuzViewModel by viewModels { JuzViewModelFactory(applicationContext) }

        setContent {
            QuranMeTheme {

                // Initialize your ViewModels and other variables
                var selectedTab by remember { mutableStateOf("Juz") }

                // Observe the LiveData from the ViewModel
                val surahListState by viewModelSurat.suratListResponse.observeAsState()
                val juzData by viewModelJuz.juzData.observeAsState()
                val surahsByJuzMap by viewModelJuz.surahsByJuz.observeAsState()

                // Use LaunchedEffect to construct the map once the Surahs are loaded
                LaunchedEffect(surahListState) {
                    if (surahListState is UiState.Success) {
                        viewModelJuz.constructSurahsByJuzMap()
                    }
                }

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        TopBar(onMenuClick = {}, onSearchClick = {})
                        GreetingSection(userName = "Muhammad")
                        LastReadSection(surahName = "Al-Fatihah", ayatNumber = 1)
                        TabsSection(
                            tabs = listOf("Surah", "Juz"),
                            selectedTab = selectedTab,
                            onTabSelected = { tab ->
                                selectedTab = tab
                            }
                        )

                        when (selectedTab) {
                            "Surah" -> {
                                SurahList(viewModel = viewModelSurat)
                            }

                            "Juz" -> {
                                // Display JuzListView when the map is ready
                                surahsByJuzMap?.let { map ->
                                    JuzListView(
                                        juzData = juzData,
                                        surahsByJuzMap = map,
                                        onSurahClick = { surat ->
                                            val intent = Intent(this@CariBacaanActivity, QuranActivity::class.java)
                                            intent.putExtra("SURAH_NUMBER", surat.nomor)
                                            startActivity(intent)
                                        }

                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
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
    fun TabsSection(tabs: List<String>, selectedTab: String, onTabSelected: (String) -> Unit) {
        var selectedTabIndex by remember { mutableStateOf(if (selectedTab == "Surah") 0 else 1) }

        TabRow(
            selectedTabIndex = selectedTabIndex,
            containerColor = Color(0xFF040C23),
            contentColor = Color.White
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = {
                        selectedTabIndex = index
                        onTabSelected(title) // Update the state with the selected tab title
                    },
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
//            TabsSection(tabs = listOf("Surah", "Juz"))
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


}
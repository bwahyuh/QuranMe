package com.example.quranme.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.quranme.compose.page.PrayerTimeList
import com.example.quranme.ui.quiz.PilihanQuiz
import com.example.quranme.compose.ui.components.TopBar
import com.example.quranme.compose.ui.components.Menu
import com.example.quranme.compose.ui.components.LastReadSection
import com.example.quranme.compose.page.PrayerTimeList
import com.example.quranme.repo.local.DatabaseBuilder.getDatabase
//import com.example.quranme.repo.local.getDatabase
import com.example.quranme.ui.bookmark.BookmarkActivity
import com.example.quranme.ui.bookmark.BookmarkViewModel
import com.example.quranme.ui.bookmark.BookmarkViewModelFactory
import com.example.quranme.ui.main.PrayerTimesViewModel
import com.example.quranme.ui.main.SignInActivity
import com.example.quranme.ui.quiz.IsiQuiz
import com.example.quranme.ui.quran.CariBacaanActivity
import com.example.quranme.ui.quran.QuranViewModel

class HomeActivity : ComponentActivity() {
    val viewModel: PrayerTimesViewModel by viewModels()
//    private val viewModel: QuranViewModel by viewModels()
    private val bookmarkViewModel: BookmarkViewModel by viewModels {
        BookmarkViewModelFactory(getDatabase(this).bookmarkDao())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                HomePage()
            }
        }
    }

    @Composable
    fun HomePage() {
        val lastBookmark by bookmarkViewModel.lastBookmark.observeAsState()

        Column {
            TopBar(userName = "Tanvir Ahassan")
            Menu(
                currentLocation = "Your Location",
                currentTime = "15:00 WIB",
                onMenuSelected = { menuTitle ->
                    when (menuTitle) {
                        "Al-Qur'an" -> navigateToCariBacaanActivity()
                        "Bookmark" -> navigateToBookmarkActivity()
                        "Settings" -> navigateToSettingScreen()
                        "Quiz" -> navigateToQuizActivity()
                    }
                }
            )

            // Display the LastReadSection with the last bookmark data
            lastBookmark?.let { bookmark ->
                LastReadSection(
                    lastSurahName = "Surah: ${bookmark.surahNumber}",
                    lastAyahNumber = bookmark.ayatNumber
                )
            }

            PrayerTimeList(prayerTimes = viewModel.prayerTimes.value)

            // Add PrayerTimeList and BottomNavigationBar if needed
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(0.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Bottom Bar with back button and title
                com.example.quranme.compose.ui.components.BottomBar(
                    NavController(LocalContext.current)
                )
            }
        }
    }

    private fun navigateToCariBacaanActivity() {
        val intent = Intent(this, CariBacaanActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToSettingScreen() {
        val intent = Intent(this, SignInActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToQuizActivity() {
        val intent = Intent(this, IsiQuiz::class.java)
        startActivity(intent)
    }

    private fun navigateToBookmarkActivity() {
        val intent = Intent(this, BookmarkActivity::class.java)
        startActivity(intent)
    }
}

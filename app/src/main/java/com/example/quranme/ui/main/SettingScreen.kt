package com.example.quranme.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.*
import com.example.quranme.compose.page.SettingsScreen
import com.example.quranme.compose.ui.components.BottomBar

class SettingsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = "settings"
                    ) {
                        addSettingsGraph(navController)
                    }
                }
            }
        }
    }

    private fun NavGraphBuilder.addSettingsGraph(navController: NavController) {
        composable("settings") {
            SettingsScreen(navController = navController, onBackClick = {})
        }

        // Add other destinations for settings if needed
        // For example, composable("account_settings") { /* Account settings content */ }
    }
}

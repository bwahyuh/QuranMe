package com.example.quranme.compose.page

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.quranme.R
import com.example.quranme.compose.page.ImageLabelingAnalyzer
import com.example.quranme.compose.page.CameraView
import com.example.quranme.ui.main.ImageAnalyzerActivity
import com.example.quranme.ui.quran.CariBacaanActivity
import com.example.quranme.ui.quran.QuranActivity
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionRequired
import com.google.accompanist.permissions.rememberPermissionState
import com.google.mlkit.vision.label.ImageLabel

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ImageLabelingScreen(navController: NavController) {
    val context = LocalContext.current
    val cameraPermissionState =
        rememberPermissionState(permission = android.Manifest.permission.CAMERA)

    PermissionRequired(
        permissionState = cameraPermissionState,
        permissionNotGrantedContent = {
            LaunchedEffect(Unit) {
                cameraPermissionState.launchPermissionRequest()
            }
        },
        permissionNotAvailableContent = {
            Column {
                Toast.makeText(context, "Permission denied.", Toast.LENGTH_LONG).show()
            }
        }) {
        ScanSurface(navController = navController)
    }
}

@Composable
fun ScanSurface(navController: NavController) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val imageLabels = remember { mutableStateListOf<ImageLabel>() }

    Box(modifier = Modifier.fillMaxSize()) {
        CameraView(
            context = context,
            lifecycleOwner = lifecycleOwner,
            analyzer = ImageLabelingAnalyzer {
                imageLabels.clear()
                imageLabels.addAll(it)
            }
        )
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(10.dp)
                .fillMaxHeight()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "back",
                        tint = Color.White
                    )
                }
                Text(
                    text = stringResource(id = R.string.image_labeling_detection_title),
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White
                )
            }

            Card(
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
            ) {
                Text(
                    text = imageLabels.joinToString(separator = "\n") { it.text },
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.padding(horizontal = 10.dp)
                )
            }
        }
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
        com.example.quranme.compose.ui.components.BottomBar(NavController(LocalContext.current))

    }
    for (label in imageLabels) {
        val text = label.text
        val confidence = label.confidence
        val index = label.index
        if(text == "Hand"){
            val intent = Intent(context, QuranActivity::class.java)
            intent.putExtra("SURAH_NUMBER", 114)
            context.startActivity(intent)
            break
        } else if(text == "Skin"){
            val intent = Intent(context, QuranActivity::class.java)
            intent.putExtra("SURAH_NUMBER", 113)
            context.startActivity(intent)
            break
        }
    }

}
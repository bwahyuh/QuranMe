package com.example.quranme.ui.quiz

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quranme.R
import com.example.quranme.compose.ui.theme.QuranMeTheme
import com.example.quranme.ui.main.HomeActivity


class HasilQuizActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Mengambil data dari Intent
        val score = intent.getIntExtra("score", 0)
        val totalQuestions = intent.getIntExtra("totalQuestions", 0)

        Log.d("HasilQuizActivity", "Score: $score, Total Questions: $totalQuestions")

        setContent {
            QuranMeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Background()
                    ScoreText(score, totalQuestions)
                }
            }
        }
    }
}

@Composable
fun Background() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFF040C23)
    ) {
        // Background does not need to contain other composables
    }
}

@Composable
fun ScoreText(score: Int, totalQuestions: Int) {
    val context = LocalContext.current

    Log.d("HasilQuizActivity", "ScoreText Composable: Score: $score, Total Questions: $totalQuestions")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Hei, ini adalah skor akhir mu!",
            style = TextStyle(
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.poppins_bold)),
                fontWeight = FontWeight(700),
                color = Color(0xFFFFFFFF),
                textAlign = TextAlign.Center,
            ),
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Your Score is $score out of $totalQuestions",
            style = TextStyle(
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.poppins_light)),
                fontWeight = FontWeight(700),
                color = Color(0xFFE0E0E0),
                textAlign = TextAlign.Center,
            ),
            modifier = Modifier
                .width(189.dp)
                .height(19.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val intent = Intent(context, HomeActivity::class.java)
                context.startActivity(intent)
            },
            modifier = Modifier
                .wrapContentSize()
                .padding(8.dp)
        ) {
            Text("Selesai!")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HasilQuizActivityPreview() {
    QuranMeTheme {
        Background()
        ScoreText(score = 0, totalQuestions = 10)
    }
}

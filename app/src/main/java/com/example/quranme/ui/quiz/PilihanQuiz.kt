package com.example.quranme.ui.quiz

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import android.util.Log
import androidx.compose.ui.platform.LocalContext
import com.example.quranme.R
import com.example.quranme.compose.ui.theme.QuranMeTheme


class PilihanQuiz : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuranMeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PilihanQuizContent()
                }
            }
        }
    }
}

@Composable
fun PilihanQuizBackground() {
    val context = LocalContext.current

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFF040C23)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Pilih Level",
                style = TextStyle(
                    fontSize = 28.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_bold)),
                    fontWeight = FontWeight(700),
                    color = Color(0xFFFFFFFF),
                    textAlign = TextAlign.Center
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            AlifLevelCard(context)
            Spacer(modifier = Modifier.height(16.dp))
            LamLevelCard(context)
            Spacer(modifier = Modifier.height(16.dp))
            JimLevelCard(context)
            Spacer(modifier = Modifier.height(16.dp))
            WowLevelCard(context)
        }
    }
}


@Composable
fun AlifLevelCard(context: Context) {
    Box(
        modifier = Modifier
            .clickable {
                Log.d("PilihanQuiz", "AlifLevelCard clicked")
                val intent = Intent(context, IsiQuiz::class.java)
                context.startActivity(intent)
            }
            .width(349.dp)
            .height(110.dp)
            .background(color = Color(0xFFC589D4), shape = RoundedCornerShape(size = 15.dp))
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ngaji),
                contentDescription = "image description",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .width(68.43137.dp)
                    .height(57.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = "Alif Level",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_bold)),
                        fontWeight = FontWeight(500),
                        color = Color(0xFFFFFFFF),
                        textAlign = TextAlign.Start
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(24.dp)
                )
                Text(
                    text = "Selesaikan tantangan berikut untuk menguji pemahaman mu!",
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_light)),
                        fontWeight = FontWeight(300),
                        color = Color(0xFFFFFFFF),
                        textAlign = TextAlign.Start
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(30.dp)
                )
            }
        }
    }
}

@Composable
fun LamLevelCard(context: Context) {
    Box(
        modifier = Modifier
            .clickable { /* implementasi navigasi untuk Lam Level */ }
            .width(349.dp)
            .height(110.dp)
            .background(color = Color(0xFFA06262), shape = RoundedCornerShape(size = 15.dp))
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ngaji3),
                contentDescription = "image description",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .width(68.43137.dp)
                    .height(57.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = "Lam Level",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_bold)),
                        fontWeight = FontWeight(500),
                        color = Color(0xFFFFFFFF),
                        textAlign = TextAlign.Start
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(24.dp)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Selesaikan tantangan berikut untuk menguji pemahaman mu!",
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_light)),
                        fontWeight = FontWeight(300),
                        color = Color(0xFFFFFFFF),
                        textAlign = TextAlign.Start
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(30.dp)
                )
            }
        }
    }
}

@Composable
fun JimLevelCard(context: Context) {
    Box(
        modifier = Modifier
            .clickable { /* implementasi navigasi untuk Jim Level */ }
            .width(349.dp)
            .height(110.dp)
            .background(color = Color(0xFFDBAC35), shape = RoundedCornerShape(size = 15.dp))
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ngaji2),
                contentDescription = "image description",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .width(68.43137.dp)
                    .height(57.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = "Jim Level",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_bold)),
                        fontWeight = FontWeight(500),
                        color = Color(0xFFFFFFFF),
                        textAlign = TextAlign.Start
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(24.dp)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Selesaikan tantangan berikut untuk menguji pemahaman mu!",
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_light)),
                        fontWeight = FontWeight(300),
                        color = Color(0xFFFFFFFF),
                        textAlign = TextAlign.Start
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(30.dp)
                )
            }
        }
    }
}

@Composable
fun WowLevelCard(context: Context) {
    Box(
        modifier = Modifier
            .clickable { /* implementasi navigasi untuk Wow Level */ }
            .width(349.dp)
            .height(110.dp)
            .background(color = Color(0xFF7F4B6A), shape = RoundedCornerShape(size = 15.dp))
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ngaji2),
                contentDescription = "image description",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .width(68.43137.dp)
                    .height(57.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = "Wow Level",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_bold)),
                        fontWeight = FontWeight(500),
                        color = Color(0xFFFFFFFF),
                        textAlign = TextAlign.Start
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(24.dp)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Selesaikan tantangan berikut untuk menguji pemahaman mu!",
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_light)),
                        fontWeight = FontWeight(300),
                        color = Color(0xFFFFFFFF),
                        textAlign = TextAlign.Start
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(30.dp)
                )
            }
        }
    }
}

@Composable
fun PilihanQuizContent() {
    PilihanQuizBackground()
}

@Preview(showBackground = true)
@Composable
fun PilihanQuizPreview() {
    QuranMeTheme {
        PilihanQuizContent()
    }
}

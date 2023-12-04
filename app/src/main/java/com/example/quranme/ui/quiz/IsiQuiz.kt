package com.example.quranme.ui.quiz

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import com.example.quranme.R
import com.example.quranme.compose.ui.theme.QuranMeTheme


class IsiQuiz : ComponentActivity() {
    private var score by mutableStateOf(0)
    private var currentQuestion by mutableStateOf(0)
    private val totalQuestions = 3
    private var selectedAnswer by mutableStateOf("")
    private var correctAnswer by mutableStateOf("")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuranMeTheme {
                QuizContent()
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
    fun QuizContent() {
        val questions = listOf(
            "Potongan dari surah manakah ayat ini?",
            "Apa nama surah yang sedang Anda baca?",
            "Berapa nomor surah ini?"
        )

        val images = listOf(
            R.drawable.soal1,
            R.drawable.soal2,
            R.drawable.soal3
        )

        val answers = listOf(
            listOf("Al-Ikhlas", "Yunus", "Al-Kausar"),
            listOf("Al-Baqarah", "An-nas", " Al-Fatihah"),
            listOf("4", "7", "3")
        )

        LaunchedEffect(currentQuestion) {
            if (currentQuestion < totalQuestions - 1) {
                correctAnswer = getCorrectAnswer(currentQuestion)
                // Pastikan bahwa currentQuestion masih dalam rentang panjang array
                if (currentQuestion < questions.size) {
                    // Lakukan operasi dengan array di sini
                }
            } else {
                // Kuis selesai, pindah ke halaman HasilQuizActivity
                Log.d("IsiQuiz", "Pindah ke HasilQuizActivity")
                val intent = Intent(this@IsiQuiz, HasilQuizActivity::class.java)
                intent.putExtra("score", score)
                intent.putExtra("totalQuestions", totalQuestions)
                startActivity(intent)
                finish()
            }
        }



        Background() // Panggil Background di sini

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CustomModifier()
            Spacer(modifier = Modifier.height(16.dp))
            if (currentQuestion < totalQuestions) {
                QuizQuestion(questions[currentQuestion])
                Spacer(modifier = Modifier.height(16.dp))
                QuizImage(images[currentQuestion])
                Spacer(modifier = Modifier.height(16.dp))
            }

            LinearProgressIndicator(
                progress = currentQuestion.toFloat() / totalQuestions.toFloat(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
            )

            Text(
                text = "$currentQuestion/$totalQuestions",
                style = TextStyle(
                    fontSize = 16.sp,
                    color = Color.White
                ),
                modifier = Modifier.padding(8.dp)
            )

            // Tombol jawaban
            answers[currentQuestion].forEach { answer ->
                AnswerButton(answer = answer)
            }
        }
    }


    @Composable
    fun CustomModifier() {
        Text(
            text = "Quiz Al-Qur’an",
            modifier = Modifier
                .fillMaxWidth()
                .height(78.dp)
                .background(color = Color(0xFF863ED5), shape = RoundedCornerShape(size = 10.dp))
                .padding(16.dp),
            style = TextStyle(
                fontSize = 26.sp,
                fontFamily = FontFamily(Font(R.font.poppins_light)),
                fontWeight = FontWeight(600),
                color = Color(0xFFFFFFFF),
                textAlign = TextAlign.Center,
            )
        )
    }

    @Composable
    fun QuizQuestion(question: String) {
        Text(
            text = question,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp),
            style = TextStyle(
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.poppins_light)),
                fontWeight = FontWeight(600),
                color = Color(0xFFFFFFFF),
                textAlign = TextAlign.Center,
            )
        )
    }

    @Composable
    fun QuizImage(imageRes: Int) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = "image description",
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = Color(0xFFFFFFFF),
                    shape = RoundedCornerShape(size = 3.dp)
                )
                .width(350.dp)
                .height(196.dp),
            contentScale = ContentScale.Crop
        )
    }

    @Composable
    fun AnswerButton(answer: String) {
        Button(
            onClick = {
                selectedAnswer = answer
                if (selectedAnswer.isNotEmpty()) {
                    checkAnswer()
                    if (currentQuestion < totalQuestions - 1) {
                        currentQuestion++
                    }
                } else {
                    // Tindakan jika pengguna belum memilih jawaban
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(text = answer)
        }
    }

    private fun checkAnswer() {
        Log.d("IsiQuiz", "Checking answer - selected: $selectedAnswer, correct: $correctAnswer")

        if (selectedAnswer == correctAnswer) {
            score++
        }

        if (currentQuestion < totalQuestions - 1) {
            // Move to the next question
            currentQuestion++
            Log.d("IsiQuiz", "Move to the next question - currentQuestion: $currentQuestion")
        } else {
            // Quiz finished, navigate to HasilQuizActivity
            Log.d("IsiQuiz", "Quiz finished, navigating to HasilQuizActivity")
            val intent = Intent(this@IsiQuiz, HasilQuizActivity::class.java)
            intent.putExtra("score", score)
            intent.putExtra("totalQuestions", totalQuestions)
            startActivity(intent)
            finish()
        }
    }


    private fun getCorrectAnswer(questionIndex: Int): String {
        return when (questionIndex) {
            0 -> "Al-Kausar"
            1 -> "Al-Fatihah"
            2 -> "4"
            else -> ""
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun IsiQuizDefaultPreview() {
        QuranMeTheme {
            Background()
            QuizContent()
        }
    }
}

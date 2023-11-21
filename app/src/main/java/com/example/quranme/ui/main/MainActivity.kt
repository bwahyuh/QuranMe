package com.example.quranme.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
//import com.example.quranme.ui.quran.ListSurahActivity
import com.example.quranme.R
import com.example.quranme.compose.ui.theme.QuranMeTheme
import com.example.quranme.ui.quran.CariBacaanActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuranMeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Background()
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Title("QuranMe")
                        Spacer(modifier = Modifier.height(8.dp))
                        Description("Learn Quran and Recite once everyday")

                        Spacer(modifier = Modifier.height(8.dp))
                        ButtonRect(
                            onClick = {
                                     val navigate = Intent(this@MainActivity, CariBacaanActivity::class.java)
                                        this@MainActivity.startActivity(navigate)
                            },"Get Started")

                    }
                }
            }
        }
    }


@Composable
fun Background() {
    Surface(
        modifier = Modifier
            .fillMaxSize(),

//        shape = RoundedCornerShape(size = 40.dp),
        color = Color(0xFF040C23)
    ) {
        // Background does not need to contain other composables
    }
}

@Composable
fun Title(name: String, modifier: Modifier = Modifier) {
    val poppinsFamily = FontFamily(
        Font(R.font.poppins_bold, FontWeight.Normal)
    )

    Text(
        modifier = modifier,
        text = name,
        style = TextStyle(
            fontSize = 28.sp,
            fontFamily = poppinsFamily,
            fontWeight = FontWeight.W700,
            color = Color.White, // Changed to white for visibility on the dark background
            textAlign = TextAlign.Center
        )
    )
}

@Composable
fun Description(desc: String, modifier: Modifier = Modifier) {
    val poppinsFamily = FontFamily(
        Font(R.font.poppins_light, FontWeight.Normal)
    )

    Text(
        modifier = modifier,
        text = desc,
        style = TextStyle(
            fontSize = 18.sp,
            fontFamily = poppinsFamily,
            fontWeight = FontWeight.W400, // Use FontWeight object
            color = Color(0xFFA19CC5),
            textAlign = TextAlign.Center,
        )
    )
}
@Composable
fun ButtonRect(onClick: () -> Unit, name: String, modifier: Modifier = Modifier){
    val poppinsFamily = FontFamily(
        Font(R.font.poppins_regular, FontWeight.Normal)
    )

    Button(
        onClick = onClick,
        shape = RoundedCornerShape(30.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF9B091)),
        modifier = Modifier
            .width(185.dp)
            .height(60.dp)
    ) {

        Text(
            text = name,
            style = TextStyle(
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.poppins_bold)),
                fontWeight = FontWeight(600),
                color = Color(0xFF091945),
                textAlign = TextAlign.Center,
            )
        )
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
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Title("QuranMe")
                Spacer(modifier = Modifier.height(8.dp))
                Description("Learn Quran and Recite once everyday")

                Spacer(modifier = Modifier.height(8.dp))
                ButtonRect(
                    onClick = { /*TODO*/ }, "Get Started")

                Spacer(modifier = Modifier.height(8.dp))
                ButtonRect(
                    onClick = { /*TODO*/ }, "Halo")
            }
            }
        }
    }
}

package com.example.quranme.view
//
//import android.os.Build
//import androidx.annotation.RequiresApi
//import androidx.compose.foundation.BorderStroke
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.*
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.Surface
//import androidx.compose.material3.Text
//import androidx.compose.runtime.*
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.ColorFilter
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.SpanStyle
//import androidx.compose.ui.text.buildAnnotatedString
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.text.withStyle
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.em
//import androidx.compose.ui.unit.sp
//import java.time.format.TextStyle
//
//@RequiresApi(Build.VERSION_CODES.O)
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun SignIn(modifier: Modifier = Modifier) {
//    Box(
//        modifier = modifier
//            .requiredWidth(width = 375.dp)
//            .requiredHeight(height = 812.dp)
//            .clip(shape = RoundedCornerShape(35.dp))
//            .background(color = Color(0xff040c23))
//    ) {
//        Box(
//            modifier = Modifier
//                .align(alignment = Alignment.TopStart)
//                .offset(x = 0.dp,
//                    y = 134.dp)
//                .requiredWidth(width = 375.dp)
//                .requiredHeight(height = 513.dp)
//        ) {
//            Box(
//                modifier = Modifier
//                    .align(alignment = Alignment.TopStart)
//                    .offset(x = 0.dp,
//                        y = 369.dp)
//                    .requiredWidth(width = 375.dp)
//                    .requiredHeight(height = 144.dp)
//            ) {
//                Box(
//                    modifier = Modifier
//                        .align(alignment = Alignment.TopStart)
//                        .offset(x = 0.dp,
//                            y = (-14).dp)
//                        .requiredWidth(width = 375.dp)
//                        .requiredHeight(height = 144.dp)
//                ) {
//                    Box(
//                        modifier = Modifier
//                            .align(alignment = Alignment.TopStart)
//                            .offset(x = 20.dp,
//                                y = 57.dp)
//                            .requiredWidth(width = 335.dp)
//                            .requiredHeight(height = 48.dp)
//                    ) {
//                        Box(
//                            modifier = Modifier
//                                .requiredWidth(width = 335.dp)
//                                .requiredHeight(height = 48.dp)
//                        ) {
//                            Box(
//                                modifier = Modifier
//                                    .fillMaxSize()
//                                    .clip(shape = RoundedCornerShape(8.dp))
//                                    .background(color = Color.White.copy(alpha = 0.1f))
//                                    .padding(start = 20.dp,
//                                        end = -20.dp,
//                                        top = 57.dp,
//                                        bottom = -57.dp)
//                            ) {
//                                Box(
//                                    modifier = Modifier
//                                        .align(alignment = Alignment.TopStart)
//                                        .offset(x = 72.dp,
//                                            y = 13.dp)
//                                        .requiredWidth(width = 212.dp)
//                                        .requiredHeight(height = 25.dp)
//                                ) {
//                                    Text(
//                                        text = "Continue With Google",
//                                        color = Color.White,
//                                        style = androidx.compose.ui.text.TextStyle(
//                                            fontSize = 16.sp,
//                                            letterSpacing = 0.2.sp
//                                        )
//                                    )
//                                    Image(
//                                        painter = painterResource(id = R.drawable.google21),
//                                        contentDescription = "google (2) 1",
//                                        modifier = Modifier
//                                            .align(alignment = Alignment.TopStart)
//                                            .offset(x = 187.dp,
//                                                y = 0.dp)
//                                            .requiredSize(size = 25.dp))
//                                }
//                            }
//                        }
//                    }
//                    Box(
//                        modifier = Modifier
//                            .align(alignment = Alignment.TopStart)
//                            .offset(x = 114.dp,
//                                y = 21.dp)
//                            .requiredWidth(width = 135.dp)
//                            .requiredHeight(height = 36.dp)
//                    ) {
//                        Text(
//                            text = "OR",
//                            color = Color.White.copy(alpha = 0.45f),
//                            lineHeight = 2.57.em,
//                            style = androidx.compose.ui.text.TextStyle(
//                                fontSize = 14.sp
//                            ),
//                            modifier = Modifier
//                                .align(alignment = Alignment.TopStart)
//                                .offset(x = 57.dp,
//                                    y = 0.dp)
//                                .requiredWidth(width = 52.dp))
//                        Image(
//                            painter = painterResource(id = R.drawable.img_2),
//                            contentDescription = "2",
//                            modifier = Modifier
//                                .align(alignment = Alignment.TopStart)
//                                .offset(x = 0.dp,
//                                    y = 16.dp)
//                                .requiredWidth(width = 48.dp)
//                                .requiredHeight(height = 2.dp))
//                        Image(
//                            painter = painterResource(id = R.drawable.img_1),
//                            contentDescription = "1",
//                            modifier = Modifier
//                                .align(alignment = Alignment.TopStart)
//                                .offset(x = 88.dp,
//                                    y = 16.dp)
//                                .requiredWidth(width = 47.dp)
//                                .requiredHeight(height = 2.dp))
//                    }
//                }
//            }
//            Box(
//                modifier = Modifier
//                    .requiredWidth(width = 374.dp)
//                    .requiredHeight(height = 501.dp)
//            ) {
//                Box(
//                    modifier = Modifier
//                        .requiredWidth(width = 374.dp)
//                        .requiredHeight(height = 501.dp)
//                ) {
//                    Box(
//                        modifier = Modifier
//                            .align(alignment = Alignment.TopStart)
//                            .offset(x = 19.dp,
//                                y = 88.dp)
//                            .requiredWidth(width = 336.dp)
//                            .requiredHeight(height = 139.dp)
//                    ) {
//                        Box(
//                            modifier = Modifier
//                                .requiredWidth(width = 335.dp)
//                                .requiredHeight(height = 60.dp)
//                        ) {
//                            Box(
//                                modifier = Modifier
//                                    .fillMaxSize()
//                                    .clip(shape = RoundedCornerShape(12.dp))
//                                    .background(color = Color.White.copy(alpha = 0.1f))
//                                    .padding(start = 19.dp,
//                                        end = -19.dp,
//                                        top = 222.dp,
//                                        bottom = -222.dp)
//                            ) {
//                                Text(
//                                    text = "Email",
//                                    color = Color.White.copy(alpha = 0.29f),
//                                    textAlign = TextAlign.Center,
//                                    style = androidx.compose.ui.text.TextStyle(
//                                        fontSize = 12.sp,
//                                        fontWeight = FontWeight.Bold,
//                                        letterSpacing = 0.2.sp
//                                    ),
//                                    modifier = Modifier
//                                        .align(alignment = Alignment.TopStart)
//                                        .offset(x = 17.dp,
//                                            y = 11.dp))
//                                Text(
//                                    text = "mulimah.gmail.com",
//                                    color = Color.White.copy(alpha = 0.95f),
//                                    style = androidx.compose.ui.text.TextStyle(
//                                        fontSize = 14.sp,
//                                        fontWeight = FontWeight.Bold,
//                                        letterSpacing = 0.2.sp
//                                    ),
//                                    modifier = Modifier
//                                        .align(alignment = Alignment.TopStart)
//                                        .offset(x = 20.dp,
//                                            y = 31.dp))
//                                Image(
//                                    modifier = Modifier
//                                        .align(alignment = Alignment.TopStart)
//                                        .offset(x = 295.dp,
//                                            y = 19.dp)
//                                        .requiredSize(size = 24.dp))
//                            }
//                        }
//                        Box(
//                            modifier = Modifier
//                                .align(alignment = Alignment.TopStart)
//                                .offset(x = 1.dp,
//                                    y = 79.dp)
//                                .requiredWidth(width = 335.dp)
//                                .requiredHeight(height = 60.dp)
//                        ) {
//                            Box(
//                                modifier = Modifier
//                                    .requiredWidth(width = 335.dp)
//                                    .requiredHeight(height = 60.dp)
//                            ) {
//                                Surface(
//                                    shape = RoundedCornerShape(12.dp),
//                                    color = Color.White.copy(alpha = 0.1f),
//                                    border = BorderStroke(0.5.dp, Color.White.copy(alpha = 0.33f)),
//                                    modifier = Modifier
//                                        .clip(shape = RoundedCornerShape(12.dp))
//                                        .padding(start = 20.dp,
//                                            end = -20.dp,
//                                            top = 301.dp,
//                                            bottom = -301.dp)
//                                ) {
//                                    Box(
//                                        modifier = Modifier
//                                            .requiredWidth(width = 335.dp)
//                                            .requiredHeight(height = 60.dp)
//                                    ) {
//                                        Text(
//                                            text = "Password",
//                                            color = Color.White.copy(alpha = 0.29f),
//                                            style = androidx.compose.ui.text.TextStyle(
//                                                fontSize = 12.sp,
//                                                fontWeight = FontWeight.Bold,
//                                                letterSpacing = 0.2.sp
//                                            ),
//                                            modifier = Modifier
//                                                .align(alignment = Alignment.TopStart)
//                                                .offset(x = 20.dp,
//                                                    y = 12.dp))
//                                        Box(
//                                            modifier = Modifier
//                                                .align(alignment = Alignment.TopStart)
//                                                .offset(x = 20.dp,
//                                                    y = 31.dp)
//                                                .requiredWidth(width = 101.dp)
//                                                .requiredHeight(height = 17.dp)
//                                        ) {
//                                            Box(
//                                                modifier = Modifier
//                                                    .align(alignment = Alignment.TopStart)
//                                                    .offset(x = 0.dp,
//                                                        y = 7.dp)
//                                                    .requiredSize(size = 4.dp)
//                                                    .clip(shape = CircleShape)
//                                                    .background(color = Color.White.copy(alpha = 0.45f)))
//                                            Box(
//                                                modifier = Modifier
//                                                    .align(alignment = Alignment.TopStart)
//                                                    .offset(x = 8.dp,
//                                                        y = 7.dp)
//                                                    .requiredSize(size = 4.dp)
//                                                    .clip(shape = CircleShape)
//                                                    .background(color = Color.White.copy(alpha = 0.45f)))
//                                            Box(
//                                                modifier = Modifier
//                                                    .align(alignment = Alignment.TopStart)
//                                                    .offset(x = 16.dp,
//                                                        y = 7.dp)
//                                                    .requiredSize(size = 4.dp)
//                                                    .clip(shape = CircleShape)
//                                                    .background(color = Color.White.copy(alpha = 0.45f)))
//                                            Box(
//                                                modifier = Modifier
//                                                    .align(alignment = Alignment.TopStart)
//                                                    .offset(x = 24.dp,
//                                                        y = 7.dp)
//                                                    .requiredSize(size = 4.dp)
//                                                    .clip(shape = CircleShape)
//                                                    .background(color = Color.White.copy(alpha = 0.45f)))
//                                            Box(
//                                                modifier = Modifier
//                                                    .align(alignment = Alignment.TopStart)
//                                                    .offset(x = 32.dp,
//                                                        y = 7.dp)
//                                                    .requiredSize(size = 4.dp)
//                                                    .clip(shape = CircleShape)
//                                                    .background(color = Color.White.copy(alpha = 0.45f)))
//                                            Box(
//                                                modifier = Modifier
//                                                    .align(alignment = Alignment.TopStart)
//                                                    .offset(x = 40.dp,
//                                                        y = 7.dp)
//                                                    .requiredSize(size = 4.dp)
//                                                    .clip(shape = CircleShape)
//                                                    .background(color = Color.White.copy(alpha = 0.45f)))
//                                            Box(
//                                                modifier = Modifier
//                                                    .align(alignment = Alignment.TopStart)
//                                                    .offset(x = 48.dp,
//                                                        y = 7.dp)
//                                                    .requiredSize(size = 4.dp)
//                                                    .clip(shape = CircleShape)
//                                                    .background(color = Color.White.copy(alpha = 0.45f)))
//                                            Box(
//                                                modifier = Modifier
//                                                    .align(alignment = Alignment.TopStart)
//                                                    .offset(x = 56.dp,
//                                                        y = 7.dp)
//                                                    .requiredSize(size = 4.dp)
//                                                    .clip(shape = CircleShape)
//                                                    .background(color = Color.White.copy(alpha = 0.45f)))
//                                        }
//                                    }
//                                }
//                            }
//                            Image(
//                                modifier = Modifier
//                                    .align(alignment = Alignment.TopStart)
//                                    .offset(x = 295.dp,
//                                        y = 20.dp)
//                                    .requiredSize(size = 24.dp))
//                        }
//                    }
//                    Box(
//                        modifier = Modifier
//                            .align(alignment = Alignment.TopStart)
//                            .offset(x = 78.dp,
//                                y = 480.dp)
//                            .requiredWidth(width = 238.dp)
//                            .requiredHeight(height = 21.dp)
//                    ) {
//                        Text(
//                            text = buildAnnotatedString {
//                                withStyle(style = SpanStyle(
//                                    color = Color.White,
//                                    fontSize = 14.sp)
//                                ) {append("Don’t have any account?")}
//                                withStyle(style = SpanStyle(
//                                    color = Color.White,
//                                    fontSize = 14.sp)) {append(" ")}
//                                withStyle(style = SpanStyle(
//                                    color = Color(0xff65d6fc),
//                                    fontSize = 14.sp,
//                                    fontWeight = FontWeight.Bold)) {append("Register")}},
//                            modifier = Modifier
//                                .fillMaxSize()
//                                .wrapContentHeight(align = Alignment.Bottom))
//                    }
//                    Text(
//                        textAlign = TextAlign.Center,
//                        lineHeight = 0.sp,
//                        text = buildAnnotatedString {
//                            withStyle(style = SpanStyle(
//                                color = Color.White,
//                                fontSize = 40.sp,
//                                fontWeight = FontWeight.Bold)) {append("Sign")}
//                            withStyle(style = SpanStyle(
//                                color = Color.White,
//                                fontSize = 40.sp,
//                                fontWeight = FontWeight.Bold)) {append(" I")}
//                            withStyle(style = SpanStyle(
//                                color = Color.White,
//                                fontSize = 40.sp,
//                                fontWeight = FontWeight.Bold)) {append("n")}},
//                        modifier = Modifier
//                            .requiredWidth(width = 374.dp)
//                            .wrapContentHeight(align = Alignment.CenterVertically))
//                }
//            }
//            Image(
//                painter = painterResource(id = R.drawable.homeindicator),
//                contentDescription = "Home Indicator",
//                modifier = Modifier
//                    .align(alignment = Alignment.BottomCenter)
//                    .offset(x = 0.dp,
//                        y = (-466).dp)
//                    .requiredWidth(width = 101.dp)
//                    .requiredHeight(height = 2.dp)
//                    .clip(shape = RoundedCornerShape(100.dp)))
//        }
//        Box(
//            modifier = Modifier
//                .align(alignment = Alignment.TopStart)
//                .offset(x = 19.dp,
//                    y = 427.dp)
//                .requiredWidth(width = 335.dp)
//                .requiredHeight(height = 48.dp)
//        ) {
//            Box(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .clip(shape = RoundedCornerShape(8.dp))
//                    .background(color = Color(0xffa44aff)))
//            Text(
//                text = "SIGN IN",
//                color = Color.White.copy(alpha = 0.82f),
//                textAlign = TextAlign.Center,
//                style = androidx.compose.ui.text.TextStyle(
//                    fontSize = 19.sp,
//                    fontWeight = FontWeight.Bold,
//                    letterSpacing = 2.2.sp
//                ),
//                modifier = Modifier
//                    .fillMaxSize())
//        }
//        Text(
//            text = "Forgot Password?",
//            color = Color.White.copy(alpha = 0.45f),
//            style = androidx.compose.ui.text.TextStyle(
//                fontSize = 14.sp,
//                fontWeight = FontWeight.Bold,
//                letterSpacing = 0.2.sp
//            ),
//            modifier = Modifier
//                .align(alignment = Alignment.TopStart)
//                .offset(x = 228.dp,
//                    y = 372.dp))
//        Image(
//            painter = painterResource(id = R.drawable.group),
//            contentDescription = "Group",
//            alpha = 0.84f,
//            modifier = Modifier
//                .align(alignment = Alignment.TopStart)
//                .offset(x = 0.dp,
//                    y = 652.dp)
//                .requiredWidth(width = 390.dp)
//                .requiredHeight(height = 167.dp))
//        Image(
//            painter = painterResource(id = R.drawable.tasksbar),
//            contentDescription = "tasks bar",
//            colorFilter = ColorFilter.tint(Color.White.copy(alpha = 0.25f)),
//            modifier = Modifier
//                .align(alignment = Alignment.TopStart)
//                .offset(x = 127.dp,
//                    y = 798.dp)
//                .requiredWidth(width = 136.dp)
//                .requiredHeight(height = 9.dp))
//    }
//}
//
//@RequiresApi(Build.VERSION_CODES.O)
//@Preview(widthDp = 375, heightDp = 812)
//@Composable
//private fun SignInPreview() {
//    SignIn(Modifier)
//}

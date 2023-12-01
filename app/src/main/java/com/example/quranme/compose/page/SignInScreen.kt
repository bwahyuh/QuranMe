package com.quranme.compose.page

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.quranme.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF040C23))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = "Sign In",
            style = MaterialTheme.typography.displayMedium,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text("Email") },
            singleLine = true,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = Color.White,
                disabledTextColor = Color.Transparent,
                cursorColor = Color.White,
                focusedBorderColor = Color.White,
                unfocusedBorderColor = Color.White,
                containerColor = Color.Transparent
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text("Password") },
            singleLine = true,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = Color.White,
                disabledTextColor = Color.Transparent,
                cursorColor = Color.White,
                focusedBorderColor = Color.White,
                unfocusedBorderColor = Color.White,
                containerColor = Color.Transparent
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Forgot Password?",
            modifier = Modifier.clickable { /* Handle forgot password */ },
            color = Color(0xFF65D6FC)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { /* Handle sign in */ },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(50),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFA44AFF))
        ) {
            Text(text = "SIGN IN", color = Color.White)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "OR", color = Color.White)
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { /* Handle Google sign in */ },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(50),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1A73E8))
        ) {
            Text(text = "Continue With Google", color = Color.White)
            Spacer(modifier = Modifier.width(8.dp))
            Image(
                painter = painterResource(id = R.drawable.google), // Replace with your google icon resource id
                contentDescription = "Google sign in"
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "Don’t have any account?", color = Color.White)
            Text(
                text = " Register",
                modifier = Modifier.clickable { /* Handle register */ },
                color = Color(0xFF65D6FC)
            )
        }
        Box(
            modifier = modifier
                .requiredWidth(width = 390.dp)
                .requiredHeight(height = 200.dp)
                .align(alignment = Alignment.CenterHorizontally)
        )
        Box(
            modifier = modifier
                .requiredWidth(width = 390.dp)
                .requiredHeight(height = 167.dp)
                .align(alignment = Alignment.CenterHorizontally)
        ) {
            Image(
                painter = painterResource(id = R.drawable.footer),
                contentDescription = "Group",
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 0.dp,
                        y = 1.96044921875.dp)
                    .requiredWidth(width = 314.dp)
                    .requiredHeight(height = 163.dp))
            Image(
                painter = painterResource(id = R.drawable.footer),
                contentDescription = "Vector",
                alpha = 0.3f,
                modifier = Modifier
                    .fillMaxSize())
            Image(
                painter = painterResource(id = R.drawable.footer),
                contentDescription = "Vector",
                modifier = Modifier
                    .fillMaxSize())
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSignInScreen() {
    SignInScreen(Modifier)
}

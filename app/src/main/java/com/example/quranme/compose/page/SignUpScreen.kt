package com.example.quranme.compose.page

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quranme.R
import com.quranme.compose.page.SignInScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF040C23)) // Replace with the actual color from the design
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Sign Up",
            color = Color.White,
            fontSize = 32.sp,
            modifier = Modifier.padding(top = 32.dp)
        )

        // Email TextField
        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text("Email", color = Color.White) },
            singleLine = true,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = Color.White,
                disabledTextColor = Color.Transparent,
                cursorColor = Color.White,
                focusedBorderColor = Color.White,
                unfocusedBorderColor = Color.White,
                containerColor = Color.Transparent
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )

        // Password TextField
        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text("Password", color = Color.White) },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = Color.White,
                disabledTextColor = Color.Transparent,
                cursorColor = Color.White,
                focusedBorderColor = Color.White,
                unfocusedBorderColor = Color.White,
                containerColor = Color.Transparent
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        // Confirm Password TextField
        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text("Confirm Password", color = Color.White) },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = Color.White,
                disabledTextColor = Color.Transparent,
                cursorColor = Color.White,
                focusedBorderColor = Color.White,
                unfocusedBorderColor = Color.White,
                containerColor = Color.Transparent
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        // Checkbox and Text
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = false,
                onCheckedChange = {},
                colors = CheckboxDefaults.colors(checkmarkColor = Color.White)
            )
            Text(
                text = "Save my info?",
                color = Color.White,
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        // Sign Up Button
        Button(
            onClick = { /* Handle sign up */ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6200EE)) // Replace with the actual button color from the design
        ) {
            Text("SIGN UP", color = Color.White)
        }

        // Divider
        Divider(
            color = Color.Gray,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        // Google Sign-In Button
        OutlinedButton(
            onClick = { /* Handle sign in with Google */ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            colors = ButtonDefaults.outlinedButtonColors(containerColor = Color.Transparent)
        ) {
            Image(painter = painterResource(id = R.drawable.google), contentDescription = "Google Logo")
            Text("Continue With Google", color = Color.White, modifier = Modifier.padding(start = 8.dp))
        }
        Box(
            modifier = modifier
                .requiredWidth(width = 390.dp)
                .requiredHeight(height = 180.dp)
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
    SignUpScreen(Modifier)
}
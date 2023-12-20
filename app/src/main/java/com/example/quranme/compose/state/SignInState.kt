package com.example.quranme.compose.state

data class SignInState(
    val isSignInSuccessful: Boolean = false,
    val signInError: String? = null
)
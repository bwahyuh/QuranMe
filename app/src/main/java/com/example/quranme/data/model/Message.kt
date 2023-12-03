package com.example.quranme.data.model


data class Message(val content: String, val role: String) {
    val isUser: Boolean
        get() = role == "user"
}


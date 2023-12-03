package com.example.quranme.compose.state

import com.example.quranme.data.model.Message


data class ChatState(
    val messages: List<Message> = emptyList(),
    val isLoading: Boolean = false,
    val error: String = ""
)

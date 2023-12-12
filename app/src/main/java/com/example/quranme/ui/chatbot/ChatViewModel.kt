package com.example.quranme.ui.chatbot

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quranme.data.model.Message
import com.example.quranme.repo.remote.network.ChatService
import com.example.quranme.repo.remote.network.OpenAIRequestBody
import kotlinx.coroutines.launch

class ChatViewModel : ViewModel() {
    val messages = mutableStateListOf<Message>()

    // Loading and error handling states
    var isLoading = mutableStateOf(false)
        private set
    var errorMessage = mutableStateOf("")
        private set

    init {
        // Send an initial message to the chatbot service
        processInitialMessage()
    }

    fun sendMessage(text: String, isUserMessage: Boolean = true) {
        val role = if (isUserMessage) "user" else "system"
        messages.add(Message(text, role))

        if (isUserMessage) {
            viewModelScope.launch {
                try {
                    isLoading.value = true
                    val response = ChatService.openAIApi.generateResponse(OpenAIRequestBody(messages = messages))
                    messages.add(Message(response.choices.first().message.content, "system"))
                } catch (e: Exception) {
                    errorMessage.value = e.localizedMessage ?: "An error occurred"
                } finally {
                    isLoading.value = false
                }
            }
        }
    }

    private fun processInitialMessage() {
        val initialMessage = "GPT, ketika saya bertanya tentang Hadits tertentu, seperti 'Apa isi Hadits Sahih Muslim No. 1?', tolong jangan berikan teks atau penafsiran Hadits secara langsung. Sebaliknya, arahkan saya ke sumber terpercaya dimana saya dapat membaca Hadits tersebut. Sebagai contoh, Anda bisa merespon dengan: 'Saya tidak dapat memberikan informasi detail tentang Hadits tersebut, tetapi Anda dapat membacanya di sini: https://www.hadits.id/hadits/.' Ini akan memungkinkan saya untuk merujuk ke sumber asli untuk informasi yang akurat." +
                "Selain itu, untuk pertanyaan luas yang berkaitan dengan topik Hadits selain referensi Hadits spesifik, daripada menjawab secara detail, arahkan saya ke sumber umum untuk eksplorasi lebih lanjut. Misalnya, jika saya bertanya tentang topik 'ilmu' dalam Hadits, Anda harus merespon dengan: 'Untuk eksplorasi luas topik ini, Anda dapat mengunjungi https://www.hadits.id/tentang/ilmu.' Ingat untuk mengganti 'ilmu' di URL dengan topik yang relevan yang saya tanyakan. Pendekatan ini akan membantu saya dalam mengakses informasi komprehensif tentang topik tertentu dalam studi Hadits.\""
        // Treat the initial message as a user message to trigger processing
        sendMessage(initialMessage, isUserMessage = true)
    }

    // Method to clear the error message
    fun clearError() {
        errorMessage.value = ""
    }
}


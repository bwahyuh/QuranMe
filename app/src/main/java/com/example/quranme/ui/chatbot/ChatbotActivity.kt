package com.example.quranme.ui.chatbot

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.quranme.compose.page.ChatScreen
import com.example.quranme.data.model.Message

class ChatbotActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: ChatViewModel = viewModel()
            ChatScreen(viewModel)

        }
    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ChatScreen(viewModel = ChatViewModel())
}

package com.example.quranme.compose.page


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.quranme.ui.chatbot.ChatViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(viewModel: ChatViewModel) {
    var inputText by remember { mutableStateOf("") }
    var showErrorDialog by remember { mutableStateOf(false) }

    if (viewModel.errorMessage.value.isNotEmpty()) {
        showErrorDialog = true
    }


    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp),
            reverseLayout = true
        ) {
            items(viewModel.messages.reversed()) { message ->
                MessageBubble(message.content, if (message.isUser) Alignment.End else Alignment.Start)
            }
        }

        // Display loading indicator when waiting for a response
        if (viewModel.isLoading.value) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        }


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            TextField(
                value = inputText,
                onValueChange = { inputText = it },
                modifier = Modifier.weight(1f),
                colors = TextFieldDefaults.textFieldColors(),
                label = { Text("Type a message") },
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Send),
                keyboardActions = KeyboardActions(onSend = {
                    if (inputText.isNotBlank()) {
                        viewModel.sendMessage(inputText)
                        // Clear the input text after sending the message
                        inputText = ""
                    }
                })
            )

            IconButton(
                onClick = {
                    if (inputText.isNotBlank()) {
                        viewModel.sendMessage(inputText)
                        // Clear the input text after sending the message
                        inputText = ""
                    }
                },
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Icon(Icons.Default.Send, contentDescription = "Send message")
            }
        }

        // Menambahkan BottomBar di sini
        com.example.quranme.compose.ui.components.BottomBar(NavController(LocalContext.current))
    }

    if (showErrorDialog) {
        // Use .value to access the String inside the MutableState
        ErrorDialog(errorMessage = viewModel.errorMessage.value, onDismiss = {
            showErrorDialog = false
            viewModel.clearError()
        })
    }


}

@Composable
fun MessageBubble(text: String, alignment: Alignment.Horizontal) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        tonalElevation = 4.dp,
        modifier = Modifier
            .padding(vertical = 4.dp)
            .wrapContentWidth(alignment)
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(16.dp),
            fontSize = 16.sp
        )
    }
}

@Composable
fun ErrorDialog(errorMessage: String, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Error") },
        text = { Text(text = errorMessage) },
        confirmButton = {
            Button(onClick = onDismiss) {
                Text("OK")
            }
        }
    )
}

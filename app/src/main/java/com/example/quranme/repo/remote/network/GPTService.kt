package com.example.quranme.repo.remote.network

import android.util.Log
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*
import io.ktor.http.*
//import com.example.quranme.BuildConfig
import io.ktor.client.call.receive
import io.ktor.client.statement.HttpResponse
import kotlinx.serialization.Serializable

class GPTService {
    private val apiKey = "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"

    private val client = HttpClient(CIO) {
        install(JsonFeature) {
            serializer = GsonSerializer()
        }
        install(HttpTimeout) {
            requestTimeoutMillis = 30_000 // Set request timeout
        }
    }

    suspend fun chatWithGPT(prompt: String): GPTResponse {
        return try {
            // Execute the request and get the response
            val response: HttpResponse = client.post {
                url("https://api.openai.com/v1/chat/completions")
                header("Authorization", "Bearer $apiKey")
                contentType(ContentType.Application.Json)
                body = GPTRequest(
                    prompt = prompt,
                    max_tokens = 150,
                    model = "gpt-4-1106-preview" // Specify the model here
                )
            }

            // Deserialize the response to GPTResponse
            val gptResponse: GPTResponse = response.receive()

            Log.d("GPTService", "Response from GPT: $gptResponse")
            gptResponse
        } catch (e: Exception) {
            Log.e("GPTService", "Error making request: ${e.localizedMessage}")
            GPTResponse(choices = emptyList(), error = e.localizedMessage)
        }
    }
}

@Serializable
data class GPTRequest(val prompt: String, val max_tokens: Int, val model: String)

@Serializable
data class GPTResponse(val choices: List<Choice>, val error: String? = null)

@Serializable
data class Choice(val text: String)

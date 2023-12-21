package com.example.quranme.repo.remote.network

//import com.example.quranme.BuildConfig
import com.example.quranme.data.model.Message
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST


interface OpenAIApi {
    @Headers("Content-Type: application/json", "Authorization: Bearer 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")

    @POST("v1/chat/completions")
    suspend fun generateResponse(@Body requestBody: OpenAIRequestBody): OpenAIResponse
}

data class OpenAIRequestBody(
    val model: String = "gpt-4",
    val messages: List<Message>,
    val max_tokens: Int = 250,
    val n: Int = 1,
    val temperature: Double = 0.8
)

data class OpenAIResponse(
    val choices: List<MessageResponse>
)

data class MessageResponse(
    val message: Message
)



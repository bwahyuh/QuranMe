package com.example.quranme.repo.remote.network

//import com.example.quranme.BuildConfig
import com.example.quranme.data.model.Message
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST


interface OpenAIApi {
<<<<<<< HEAD
    @Headers("Content-Type: application/json", "Authorization: Bearer awokawokoawkowak")
=======
    @Headers("Content-Type: application/json", "Authorization: Bearer sk-")
>>>>>>> 76a18cece58b586c81af467bc8e8aa3fe91c2d6a
    @POST("v1/chat/completions")
    suspend fun generateResponse(@Body requestBody: OpenAIRequestBody): OpenAIResponse
}

data class OpenAIRequestBody(
    val model: String = "gpt-3.5-turbo",
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



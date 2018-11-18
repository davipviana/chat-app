package com.davipviana.chat.service

import com.davipviana.chat.model.Message
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ChatService {

    @POST("polling")
    fun sendMessage(@Body message: Message): Call<Unit>

    @GET("polling")
    fun listenMessages(): Call<Message>
}

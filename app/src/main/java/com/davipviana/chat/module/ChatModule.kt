package com.davipviana.chat.module

import android.util.EventLog
import com.davipviana.chat.service.ChatService
import dagger.Module
import dagger.Provides
import org.greenrobot.eventbus.EventBus
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ChatModule {

    @Provides
    fun getChatService(): ChatService {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.0.106:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(ChatService::class.java)
    }

    @Provides
    fun getEventBus(): EventBus = EventBus.builder().build()
}
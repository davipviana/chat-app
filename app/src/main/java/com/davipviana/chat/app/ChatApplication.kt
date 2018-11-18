package com.davipviana.chat.app

import android.app.Application
import com.davipviana.chat.component.ChatComponent
import com.davipviana.chat.component.DaggerChatComponent

class ChatApplication : Application() {
    val chatComponent: ChatComponent by lazy {
        DaggerChatComponent.builder().build()
    }
}
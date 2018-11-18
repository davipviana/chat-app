package com.davipviana.chat.component

import com.davipviana.chat.activity.MainActivity
import com.davipviana.chat.module.ChatModule
import dagger.Component

@Component(modules = [ChatModule::class])
interface ChatComponent {
    fun inject(mainActivity: MainActivity)
}
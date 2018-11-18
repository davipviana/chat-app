package com.davipviana.chat.callback

import android.content.Context
import android.content.Intent
import android.support.v4.content.LocalBroadcastManager
import com.davipviana.chat.event.MessageEvent
import com.davipviana.chat.model.Message
import org.greenrobot.eventbus.EventBus
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListenMessagesCallback(val context: Context) : Callback<Message> {
    override fun onFailure(call: Call<Message>, t: Throwable) {
    }

    override fun onResponse(call: Call<Message>, response: Response<Message>) {
        if(response.isSuccessful) {
            val message = response.body() as Message

            EventBus.getDefault().post(MessageEvent(message))
        }
    }
}
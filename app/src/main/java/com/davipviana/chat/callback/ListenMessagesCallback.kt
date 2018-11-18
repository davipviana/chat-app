package com.davipviana.chat.callback

import com.davipviana.chat.activity.MainActivity
import com.davipviana.chat.model.Message
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListenMessagesCallback(val activity: MainActivity) : Callback<Message> {
    override fun onFailure(call: Call<Message>, t: Throwable) {
        activity.listenMessages()
    }

    override fun onResponse(call: Call<Message>, response: Response<Message>) {
        if(response.isSuccessful) {
            val message = response.body() as Message

            activity.addMessageToRecyclerView(message)
        }
    }
}
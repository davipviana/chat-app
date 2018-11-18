package com.davipviana.chat.model

import com.google.gson.annotations.SerializedName

class Message(
    @SerializedName("id")
    val clientId: Int,
    val text: String
)

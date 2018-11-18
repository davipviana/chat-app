package com.davipviana.chat.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Message(
    @SerializedName("id")
    val clientId: Int,
    val text: String
) : Serializable

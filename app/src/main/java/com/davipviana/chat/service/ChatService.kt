package com.davipviana.chat.service

import com.davipviana.chat.model.Message
import org.json.JSONStringer
import java.io.PrintStream
import java.lang.RuntimeException
import java.net.HttpURLConnection
import java.net.URL

class ChatService {
    fun sendMessage(message: Message) {
        Thread(Runnable {
            try {
                val httpConnection = URL("http://192.168.0.106:8080/polling").openConnection() as HttpURLConnection
                httpConnection.requestMethod = "POST"
                httpConnection.setRequestProperty("content-type", "application/json")

                val jsonStringer = JSONStringer()
                    .`object`()
                    .key("text")
                    .value(message.text)
                    .key("id")
                    .value(message.clientId)
                    .endObject()

                val outputStream = httpConnection.outputStream
                PrintStream(outputStream).println(jsonStringer.toString())

                httpConnection.connect()
                httpConnection.inputStream
            } catch(e: RuntimeException) {
                e.printStackTrace()
            }
        }).start()


    }
}

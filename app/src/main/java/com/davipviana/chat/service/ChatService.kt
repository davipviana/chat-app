package com.davipviana.chat.service

import com.davipviana.chat.activity.MainActivity
import com.davipviana.chat.model.Message
import org.json.JSONObject
import org.json.JSONStringer
import java.io.PrintStream
import java.lang.RuntimeException
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL
import java.util.*
import kotlin.math.acos

class ChatService(val mainActivity: MainActivity) {
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

    fun listenMessages() {
        Thread(Runnable {
            try {
                val httpConnection = URL("http://192.168.0.106:8080/polling").openConnection() as HttpURLConnection
                httpConnection.requestMethod = "GET"
                httpConnection.setRequestProperty("Accept", "application/json")

                httpConnection.connect()
                val scanner = Scanner(httpConnection.inputStream)

                val stringBuilder = StringBuilder()
                while(scanner.hasNextLine()) {
                    stringBuilder.append(scanner.nextLine())
                }

                val jsonObject = JSONObject(stringBuilder.toString())

                val message = Message(1, jsonObject.getString("text"))

                mainActivity.runOnUiThread {
                    mainActivity.addMessageToRecyclerView(message)
                }

            } catch(e: RuntimeException) {
                e.printStackTrace()
            }
        }).start()
    }
}

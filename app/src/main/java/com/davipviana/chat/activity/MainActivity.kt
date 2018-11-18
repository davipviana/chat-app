package com.davipviana.chat.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.widget.Button
import android.widget.EditText
import com.davipviana.chat.R
import com.davipviana.chat.adapter.MessageAdapter
import com.davipviana.chat.model.Message
import com.davipviana.chat.service.ChatService

class MainActivity : AppCompatActivity() {

    private val clientId: Int = 1

    private lateinit var messageEditText: EditText

    private lateinit var sendButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val messagesRecyclerView = findViewById<RecyclerView>(R.id.main_recycler_view)

        val messages = arrayListOf(Message(1, "olá alunos de android"), Message(2, "Oi"))

        val messageAdapter = MessageAdapter(this, messages, clientId)

        messagesRecyclerView.adapter = messageAdapter

        messageEditText = findViewById<EditText>(R.id.main_text_message)

        sendButton = findViewById<Button>(R.id.main_send_button)

        sendButton.setOnClickListener{
            ChatService().sendMessage(Message(clientId, messageEditText.text.toString()))
        }
    }
}

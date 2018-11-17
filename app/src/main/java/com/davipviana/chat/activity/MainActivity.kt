package com.davipviana.chat.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import com.davipviana.chat.R
import com.davipviana.chat.adapter.MessageAdapter
import com.davipviana.chat.model.Message

class MainActivity : AppCompatActivity() {

    private val clientId: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val messagesRecyclerView = findViewById<RecyclerView>(R.id.main_recycler_view)

        val messages = arrayListOf(Message(1, "olá alunos de android"), Message(2, "Oi"))

        val messageAdapter = MessageAdapter(this, messages, clientId)

        messagesRecyclerView.adapter = messageAdapter
    }
}

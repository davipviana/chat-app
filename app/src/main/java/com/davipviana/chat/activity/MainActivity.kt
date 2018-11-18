package com.davipviana.chat.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.davipviana.chat.app.ChatApplication
import com.davipviana.chat.R
import com.davipviana.chat.adapter.MessageAdapter
import com.davipviana.chat.callback.ListenMessagesCallback
import com.davipviana.chat.callback.SendMessageCallback
import com.davipviana.chat.component.ChatComponent
import com.davipviana.chat.model.Message
import com.davipviana.chat.service.ChatService
import com.squareup.picasso.Picasso
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private val clientId: Int = 1
    private lateinit var messageEditText: EditText
    private lateinit var sendButton: Button
    private lateinit var userAvatarImageView: ImageView
    private lateinit var messageAdapter: MessageAdapter
    private lateinit var chatComponent: ChatComponent

    @Inject
    lateinit var chatService: ChatService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        chatComponent = (application as ChatApplication).chatComponent
        chatComponent.inject(this)

        initializeWidgets()

        listenMessages()
    }

    private fun initializeWidgets() {
        initializeMessagesRecyclerView()
        initializeMessageEditText()
        initializeSendButton()
        initializeUserAvatarImageView()
    }

    private fun initializeMessagesRecyclerView() {
        val messagesRecyclerView = findViewById<RecyclerView>(R.id.main_recycler_view)
        val messages = ArrayList<Message>()
        messageAdapter = MessageAdapter(this, messages, clientId)
        messagesRecyclerView.adapter = messageAdapter
    }

    private fun initializeMessageEditText() {
        messageEditText = findViewById(R.id.main_text_message)
    }

    private fun initializeSendButton() {
        sendButton = findViewById(R.id.main_send_button)
        sendButton.setOnClickListener {
            chatService
                .sendMessage(Message(clientId, messageEditText.text.toString()))
                .enqueue(SendMessageCallback())
        }
    }

    private fun initializeUserAvatarImageView() {
        userAvatarImageView = findViewById(R.id.main_user_avatar)
        Picasso
            .get()
            .load("https://api.adorable.io/avatars/285/$clientId.png")
            .into(userAvatarImageView)
    }

    fun addMessageToRecyclerView(message: Message) {
        messageAdapter.add(message)

        listenMessages()
    }

    fun listenMessages() {
        chatService.listenMessages().enqueue(ListenMessagesCallback(this))
    }
}

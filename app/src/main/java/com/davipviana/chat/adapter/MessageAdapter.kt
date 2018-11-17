package com.davipviana.chat.adapter

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.davipviana.chat.R
import com.davipviana.chat.model.Message

class MessageAdapter(
    private val context: Context,
    private val messages: ArrayList<Message>,
    private val clientId: Int
) : RecyclerView.Adapter<MessageAdapter.MessageViewHolder>() {

    override fun getItemCount(): Int {
        return messages.size
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val message = messages[position]

        holder.bindMessageInfo(message)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.message_item, parent, false)

        return MessageViewHolder(itemView)
    }

    inner class MessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val messageTextView: TextView = itemView.findViewById(R.id.message_item_text)


        fun bindMessageInfo(message: Message) {
            this.messageTextView.text = message.text

            if(clientId != message.clientId) {
                itemView.setBackgroundColor(Color.CYAN)
            }
        }
    }
}
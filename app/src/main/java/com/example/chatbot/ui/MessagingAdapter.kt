package com.example.chatbot.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import com.example.chatbot.R
import com.example.chatbot.data.Message
import com.example.chatbot.databinding.MessageItemBinding
import com.example.chatbot.utils.Constants.RECEIVE_ID
import com.example.chatbot.utils.Constants.SEND_ID

class MessagingAdapter: RecyclerView.Adapter<MessagingAdapter.MessageViewHolder>() {

    var messagesList = mutableListOf<Message>()

    inner class MessageViewHolder(val binding: MessageItemBinding): RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                messagesList.removeAt(adapterPosition)
                notifyItemRemoved(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        return MessageViewHolder(MessageItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false)
        )
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val currentMessage = messagesList[position]

        when(currentMessage.id) {
            SEND_ID -> {
                holder.binding.tvMessage.apply {
                    text = currentMessage.message
                    visibility = View.VISIBLE
                }
                holder.binding.tvBotMessage.visibility = View.GONE
            }
            RECEIVE_ID -> {
                holder.binding.tvBotMessage.apply {
                    text = currentMessage.message
                    visibility = View.VISIBLE
                }
                holder.binding.tvMessage.visibility = View.GONE
            }
        }

    }

    override fun getItemCount() = messagesList.size

    private fun insertMessage(message: Message){
        this.messagesList.add(message)
        notifyItemInserted(messagesList.size)
        //notifyDataSetChanged()
    }

}
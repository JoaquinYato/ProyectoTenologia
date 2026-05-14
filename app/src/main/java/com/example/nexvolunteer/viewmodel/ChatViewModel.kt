package com.example.nexvolunteer.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.nexvolunteer.model.Message
import com.example.nexvolunteer.repository.ChatRepository

class ChatViewModel : ViewModel() {

    private val repository =
        ChatRepository()

    val messages =
        mutableStateListOf<Message>()

    fun sendMessage(

        senderId: String,

        receiverId: String,

        text: String,

        onSuccess: () -> Unit,

        onError: (String) -> Unit
    ) {

        repository.sendMessage(

            senderId,
            receiverId,
            text,

            onSuccess,
            onError
        )
    }

    fun listenMessages(

        senderId: String,

        receiverId: String
    ) {

        repository.listenMessages(

            senderId,
            receiverId

        ) {

            messages.clear()

            messages.addAll(it)
        }
    }
}
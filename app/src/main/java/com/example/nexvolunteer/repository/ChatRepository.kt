package com.example.nexvolunteer.repository

import com.example.nexvolunteer.model.Message
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class ChatRepository {

    private val db =
        FirebaseFirestore.getInstance()

    private fun getChatId(

        user1: String,

        user2: String
    ): String {

        return if (user1 < user2) {

            "${user1}_${user2}"

        } else {

            "${user2}_${user1}"
        }
    }

    fun sendMessage(

        senderId: String,

        receiverId: String,

        text: String,

        onSuccess: () -> Unit,

        onError: (String) -> Unit
    ) {

        val chatId =
            getChatId(senderId, receiverId)

        val document =
            db.collection("chats")
                .document(chatId)
                .collection("messages")
                .document()

        val message = Message(

            id = document.id,

            senderId = senderId,

            receiverId = receiverId,

            text = text
        )

        document.set(message)

            .addOnSuccessListener {

                onSuccess()
            }

            .addOnFailureListener {

                onError(it.message ?: "Error")
            }
    }

    fun listenMessages(

        senderId: String,

        receiverId: String,

        onMessages: (List<Message>) -> Unit
    ) {

        val chatId =
            getChatId(senderId, receiverId)

        db.collection("chats")
            .document(chatId)
            .collection("messages")

            .orderBy(

                "timestamp",

                Query.Direction.ASCENDING
            )

            .addSnapshotListener { value, _ ->

                if (value != null) {

                    val messages =
                        value.toObjects(
                            Message::class.java
                        )

                    onMessages(messages)
                }
            }
    }
}
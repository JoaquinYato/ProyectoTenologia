package com.example.nexvolunteer.screens.chat

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.nexvolunteer.viewmodel.ChatViewModel
import com.google.firebase.auth.FirebaseAuth

@Composable
fun ChatScreen() {

    val viewModel = remember {

        ChatViewModel()
    }

    val senderId =
        FirebaseAuth.getInstance()
            .currentUser?.uid ?: ""

    /*
        TEMPORAL:
        luego esto vendrá desde usuarios reales
    */

    val receiverId = "BNwLYs7sITNOSiF1guvYJR2PTIn1"

    var messageText by remember {

        mutableStateOf("")
    }

    LaunchedEffect(Unit) {

        viewModel.listenMessages(

            senderId,
            receiverId
        )
    }

    Column(

        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)

    ) {

        Text(

            text = "Chat",

            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(

            modifier = Modifier.weight(1f)
        ) {

            items(viewModel.messages) { message ->

                Card(

                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)

                ) {

                    Text(

                        text = message.text,

                        modifier = Modifier
                            .padding(12.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Row {

            OutlinedTextField(

                value = messageText,

                onValueChange = {

                    messageText = it
                },

                label = {

                    Text("Mensaje")
                },

                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(10.dp))

            Button(

                onClick = {

                    if (
                        messageText.isEmpty()
                    ) {

                        return@Button
                    }

                    viewModel.sendMessage(

                        senderId,

                        receiverId,

                        messageText,

                        onSuccess = {

                            messageText = ""
                        },

                        onError = {

                        }
                    )
                }

            ) {

                Text("Enviar")
            }
        }
    }
}
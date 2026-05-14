package com.example.nexvolunteer.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.nexvolunteer.model.User
import com.example.nexvolunteer.repository.UserRepository

class UserViewModel : ViewModel() {

    private val repository = UserRepository()

    val user =
        mutableStateOf(User())

    fun loadUser(uid: String) {

        repository.getUser(

            uid,

            onSuccess = {

                user.value = it
            },

            onError = {

            }
        )
    }

    fun attendEvent(

        uid: String,

        eventId: String,

        onSuccess: () -> Unit,

        onError: (String) -> Unit
    ) {

        repository.attendEvent(

            uid,

            eventId,

            user.value,

            onSuccess,

            onError
        )
    }
}
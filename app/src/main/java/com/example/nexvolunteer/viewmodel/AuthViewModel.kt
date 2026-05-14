package com.example.nexvolunteer.viewmodel

import androidx.lifecycle.ViewModel
import com.example.nexvolunteer.model.Partner
import com.example.nexvolunteer.model.User
import com.example.nexvolunteer.repository.AuthRepository

class AuthViewModel : ViewModel() {

    private val repository = AuthRepository()

    fun registerUser(

        email: String,
        password: String,
        user: User,

        onSuccess: () -> Unit,
        onError: (String) -> Unit

    ) {

        repository.registerUser(
            email,
            password,
            user,
            onSuccess,
            onError
        )
    }

    fun registerPartner(

        email: String,
        password: String,
        partner: Partner,

        onSuccess: () -> Unit,
        onError: (String) -> Unit

    ) {

        repository.registerPartner(
            email,
            password,
            partner,
            onSuccess,
            onError
        )
    }

    fun login(

        email: String,
        password: String,

        onSuccess: () -> Unit,
        onError: (String) -> Unit

    ) {

        repository.login(
            email,
            password,
            onSuccess,
            onError
        )
    }
}
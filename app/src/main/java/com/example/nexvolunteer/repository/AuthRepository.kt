package com.example.nexvolunteer.repository

import com.example.nexvolunteer.model.Partner
import com.example.nexvolunteer.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class AuthRepository {

    private val auth = FirebaseAuth.getInstance()

    private val db = FirebaseFirestore.getInstance()

    fun registerUser(

        email: String,
        password: String,
        user: User,

        onSuccess: () -> Unit,
        onError: (String) -> Unit

    ) {

        auth.createUserWithEmailAndPassword(email, password)

            .addOnSuccessListener {

                val uid = auth.currentUser?.uid ?: ""

                val newUser = user.copy(uid = uid)

                db.collection("users")
                    .document(uid)
                    .set(newUser)

                    .addOnSuccessListener {
                        onSuccess()
                    }

                    .addOnFailureListener {
                        onError(it.message ?: "Error")
                    }
            }

            .addOnFailureListener {
                onError(it.message ?: "Error")
            }
    }

    fun registerPartner(

        email: String,
        password: String,
        partner: Partner,

        onSuccess: () -> Unit,
        onError: (String) -> Unit

    ) {

        auth.createUserWithEmailAndPassword(email, password)

            .addOnSuccessListener {

                val uid = auth.currentUser?.uid ?: ""

                val newPartner = partner.copy(uid = uid)

                db.collection("partners")
                    .document(uid)
                    .set(newPartner)

                    .addOnSuccessListener {
                        onSuccess()
                    }

                    .addOnFailureListener {
                        onError(it.message ?: "Error")
                    }
            }

            .addOnFailureListener {
                onError(it.message ?: "Error")
            }
    }

    fun login(

        email: String,
        password: String,

        onSuccess: () -> Unit,
        onError: (String) -> Unit

    ) {

        auth.signInWithEmailAndPassword(email, password)

            .addOnSuccessListener {
                onSuccess()
            }

            .addOnFailureListener {
                onError(it.message ?: "Error")
            }
    }
}
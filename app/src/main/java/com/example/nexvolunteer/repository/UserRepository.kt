package com.example.nexvolunteer.repository

import com.example.nexvolunteer.model.User
import com.example.nexvolunteer.utils.RankUtils
import com.google.firebase.firestore.FirebaseFirestore

class UserRepository {

    private val db = FirebaseFirestore.getInstance()

    fun getUser(

        uid: String,

        onSuccess: (User) -> Unit,

        onError: (String) -> Unit
    ) {

        db.collection("users")
            .document(uid)

            .get()

            .addOnSuccessListener {

                val user =
                    it.toObject(User::class.java)

                if (user != null) {

                    onSuccess(user)
                }
            }

            .addOnFailureListener {

                onError(it.message ?: "Error")
            }
    }

    fun attendEvent(

        uid: String,

        eventId: String,

        user: User,

        onSuccess: () -> Unit,

        onError: (String) -> Unit
    ) {

        val newCount =
            user.eventosAsistidos + 1

        val newRank =
            RankUtils.getRank(newCount)

        val newHistory =
            user.historialEventos + eventId

        db.collection("users")
            .document(uid)

            .update(

                mapOf(

                    "eventosAsistidos" to newCount,

                    "rango" to newRank,

                    "historialEventos" to newHistory
                )
            )

            .addOnSuccessListener {

                onSuccess()
            }

            .addOnFailureListener {

                onError(it.message ?: "Error")
            }
    }
}
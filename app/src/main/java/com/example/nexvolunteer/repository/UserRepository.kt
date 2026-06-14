package com.example.nexvolunteer.repository

import com.example.nexvolunteer.model.User
import com.example.nexvolunteer.utils.RankUtils
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FieldValue

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

        currentUser: User,

        onSuccess: () -> Unit,

        onError: (String) -> Unit

    ) {

        val updatedEvents =
            currentUser.eventosAsistidos.toMutableList()

        if (updatedEvents.contains(eventId)) {

            onError("Ya participaste en este evento")

            return
        }

        updatedEvents.add(eventId)

        val totalEvents =
            updatedEvents.size

        val newRank =
            RankUtils.getRank(totalEvents)

        val newXp =
            RankUtils.getXp(totalEvents)

        db.collection("users")

            .document(uid)

            .update(

                mapOf(

                    "eventosAsistidos" to updatedEvents,

                    "rango" to newRank,

                    "xp" to newXp

                )

            )

            .addOnSuccessListener {

                db.collection("events")
                    .document(eventId)
                    .update(
                        "participantes",
                        FieldValue.increment(1)
                    )
                    .addOnSuccessListener {
                        onSuccess()
                    }
                    .addOnFailureListener {
                        onError(
                            it.message ?: "Error al actualizar participantes"
                        )
                    }
            }

            .addOnFailureListener {

                onError(
                    it.message ?: "Error al registrar asistencia"
                )
            }
    }
}
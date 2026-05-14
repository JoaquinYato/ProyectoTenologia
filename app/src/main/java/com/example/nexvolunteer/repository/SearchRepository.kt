package com.example.nexvolunteer.repository

import com.example.nexvolunteer.model.Event
import com.google.firebase.firestore.FirebaseFirestore

class SearchRepository {

    private val db =
        FirebaseFirestore.getInstance()

    fun getEvents(

        onSuccess: (List<Event>) -> Unit,

        onError: (String) -> Unit
    ) {

        db.collection("events")

            .get()

            .addOnSuccessListener {

                val events =
                    it.toObjects(Event::class.java)

                onSuccess(events)
            }

            .addOnFailureListener {

                onError(it.message ?: "Error")
            }
    }

    fun addFavorite(

        uid: String,

        eventId: String,

        favorites: List<String>,

        onSuccess: () -> Unit,

        onError: (String) -> Unit
    ) {

        val updatedFavorites =
            favorites + eventId

        db.collection("users")
            .document(uid)

            .update(

                "favoritos",

                updatedFavorites
            )

            .addOnSuccessListener {

                onSuccess()
            }

            .addOnFailureListener {

                onError(it.message ?: "Error")
            }
    }
}
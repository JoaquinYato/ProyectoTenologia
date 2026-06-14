package com.example.nexvolunteer.repository

import com.example.nexvolunteer.model.Event
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class PartnerRepository {

    private val db =
        FirebaseFirestore.getInstance()

    private val currentUid =
        FirebaseAuth.getInstance().currentUser?.uid ?: ""

    fun getAllEvents(

        onSuccess: (List<Event>) -> Unit,

        onError: (String) -> Unit
    ) {

        db.collection("events")

            .whereEqualTo(
                "creadorId",
                currentUid
            )

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

    fun approveEvent(

        eventId: String,

        onSuccess: () -> Unit,

        onError: (String) -> Unit
    ) {

        db.collection("events")
            .document(eventId)

            .update(
                "aprobado",
                true
            )

            .addOnSuccessListener {

                onSuccess()
            }

            .addOnFailureListener {

                onError(it.message ?: "Error")
            }
    }

    fun featureEvent(

        eventId: String,

        onSuccess: () -> Unit,

        onError: (String) -> Unit
    ) {

        db.collection("events")
            .document(eventId)

            .update(
                "destacado",
                true
            )

            .addOnSuccessListener {

                onSuccess()
            }

            .addOnFailureListener {

                onError(it.message ?: "Error")
            }
    }

    fun deleteEvent(

        eventId: String,

        onSuccess: () -> Unit,

        onError: (String) -> Unit
    ) {

        db.collection("events")
            .document(eventId)
            .delete()

            .addOnSuccessListener {

                onSuccess()
            }

            .addOnFailureListener {

                onError(it.message ?: "Error")
            }
    }
}
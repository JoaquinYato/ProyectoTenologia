package com.example.nexvolunteer.repository

import com.example.nexvolunteer.model.Event
import com.google.firebase.firestore.FirebaseFirestore

class EventRepository {

    private val db = FirebaseFirestore.getInstance()

    fun createEvent(

        event: Event,

        onSuccess: () -> Unit,

        onError: (String) -> Unit
    ) {

        val document =
            db.collection("events").document()

        val newEvent =
            event.copy(id = document.id)

        document.set(newEvent)

            .addOnSuccessListener {

                onSuccess()
            }

            .addOnFailureListener {

                onError(it.message ?: "Error")
            }
    }

    fun getEvents(

        onSuccess: (List<Event>) -> Unit,

        onError: (String) -> Unit
    ) {

        db.collection("events")

            .get()

            .addOnSuccessListener { result ->

                val events =
                    result.toObjects(Event::class.java)

                onSuccess(events)
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

    fun approveEvent(
        eventId: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {

        db.collection("events")
            .document(eventId)
            .update("aprobado", true)
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
            .update("destacado", true)
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener {
                onError(it.message ?: "Error")
            }
    }
}
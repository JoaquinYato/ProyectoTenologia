package com.example.nexvolunteer.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.nexvolunteer.model.Event
import com.example.nexvolunteer.repository.PartnerRepository

class PartnerViewModel : ViewModel() {

    private val repository =
        PartnerRepository()

    val events =
        mutableStateListOf<Event>()

    fun loadEvents() {

        repository.getAllEvents(

            onSuccess = {

                events.clear()

                events.addAll(it)
            },

            onError = {

            }
        )
    }

    fun approveEvent(

        eventId: String,

        onSuccess: () -> Unit,

        onError: (String) -> Unit
    ) {

        repository.approveEvent(

            eventId,

            onSuccess,

            onError
        )
    }

    fun featureEvent(

        eventId: String,

        onSuccess: () -> Unit,

        onError: (String) -> Unit
    ) {

        repository.featureEvent(

            eventId,

            onSuccess,

            onError
        )
    }

    fun deleteEvent(
        eventId: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {

        repository.deleteEvent(
            eventId,
            onSuccess,
            onError
        )
    }
}
package com.example.nexvolunteer.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.nexvolunteer.model.Event
import com.example.nexvolunteer.repository.EventRepository

class EventViewModel : ViewModel() {

    private val repository = EventRepository()

    val events = mutableStateListOf<Event>()

    var selectedEvent by mutableStateOf(
        Event()
    )

    fun createEvent(

        event: Event,

        onSuccess: () -> Unit,

        onError: (String) -> Unit

    ) {

        repository.createEvent(

            event,

            onSuccess,

            onError
        )
    }

    fun loadEvents() {

        repository.getEvents(

            onSuccess = {

                events.clear()

                events.addAll(it)
            },

            onError = {

            }
        )
    }

    fun loadEvent(

        eventId: String

    ) {

        repository.getEventById(

            eventId,

            onSuccess = {

                selectedEvent = it
            },

            onError = {

            }
        )
    }
}
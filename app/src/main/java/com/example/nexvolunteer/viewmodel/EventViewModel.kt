package com.example.nexvolunteer.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.nexvolunteer.model.Event
import com.example.nexvolunteer.repository.EventRepository

class EventViewModel : ViewModel() {

    private val repository = EventRepository()

    val events = mutableStateListOf<Event>()

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
}
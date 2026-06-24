package com.example.nexvolunteer.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.nexvolunteer.model.Event
import com.example.nexvolunteer.model.User
import com.example.nexvolunteer.repository.EventRepository
import com.example.nexvolunteer.repository.UserRepository

class UserViewModel : ViewModel() {

    private val repository = UserRepository()

    private val eventRepository =
        EventRepository()

    val user =
        mutableStateOf(User())

    val attendedEvents =
        mutableStateListOf<Event>()

    fun loadUser(uid: String) {

        repository.getUser(

            uid,

            onSuccess = {

                user.value = it

                loadAttendedEvents(
                    it.eventosAsistidos
                )
            },

            onError = {

            }
        )
    }

    private fun loadAttendedEvents(

        eventIds: List<String>

    ) {

        attendedEvents.clear()

        eventRepository.getEvents(

            onSuccess = { events ->

                attendedEvents.addAll(

                    events.filter {

                        eventIds.contains(it.id)
                    }
                )
            },

            onError = {

            }
        )
    }

    fun attendEvent(

        uid: String,

        eventId: String,

        onSuccess: () -> Unit,

        onError: (String) -> Unit

    ) {

        repository.attendEvent(

            uid,

            eventId,

            user.value,

            {

                loadUser(uid)

                onSuccess()
            },

            onError
        )
    }
}
package com.example.nexvolunteer.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.example.nexvolunteer.model.Event
import com.example.nexvolunteer.repository.SearchRepository

class SearchViewModel : ViewModel() {

    private val repository =
        SearchRepository()

    val allEvents =
        mutableStateListOf<Event>()

    val filteredEvents =
        mutableStateListOf<Event>()

    fun loadEvents() {

        repository.getEvents(

            onSuccess = {

                allEvents.clear()
                filteredEvents.clear()

                allEvents.addAll(it)
                filteredEvents.addAll(it)
            },

            onError = {

            }
        )
    }

    fun searchEvents(query: String) {

        val result = allEvents.filter {

            it.titulo.contains(
                query,
                ignoreCase = true
            ) ||

                    it.categoria.contains(
                        query,
                        ignoreCase = true
                    )
        }

        filteredEvents.clear()

        filteredEvents.addAll(result)
    }

    fun filterCategory(category: String) {

        val result = allEvents.filter {

            it.categoria == category
        }

        filteredEvents.clear()

        filteredEvents.addAll(result)
    }

    fun addFavorite(

        uid: String,

        eventId: String,

        favorites: List<String>,

        onSuccess: () -> Unit,

        onError: (String) -> Unit
    ) {

        repository.addFavorite(

            uid,

            eventId,

            favorites,

            onSuccess,

            onError
        )
    }
}
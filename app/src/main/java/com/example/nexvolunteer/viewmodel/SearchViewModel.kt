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

        if (query.isBlank()) {

            filteredEvents.clear()
            filteredEvents.addAll(allEvents)

            return
        }

        val result = allEvents.filter {

            it.titulo.contains(query, true) ||
                    it.categoria.contains(query, true) ||
                    it.ubicacion.contains(query, true) ||
                    it.descripcion.contains(query, true)
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
}
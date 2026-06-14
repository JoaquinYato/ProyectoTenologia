package com.example.nexvolunteer.viewmodel

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.nexvolunteer.repository.DashboardRepository

class DashboardViewModel : ViewModel() {

    private val repository = DashboardRepository()

    val eventos =
        mutableIntStateOf(0)

    val xp =
        mutableIntStateOf(0)

    val rango =
        mutableStateOf("")

    fun loadStats(uid: String) {

        repository.getUserStats(

            uid,

            onSuccess = { e, x, r ->

                eventos.intValue = e

                xp.intValue = x

                rango.value = r
            },

            onError = {

            }
        )
    }
}
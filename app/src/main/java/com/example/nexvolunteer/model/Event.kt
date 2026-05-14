package com.example.nexvolunteer.model

data class Event(

    val id: String = "",

    val titulo: String = "",

    val descripcion: String = "",

    val categoria: String = "",

    val ubicacion: String = "",

    val fecha: String = "",

    val participantes: Int = 0,

    val creadorId: String = "",

    val creadorTipo: String = "",

    val destacado: Boolean = false,

    val aprobado: Boolean = false,

    val imageUrl: String = ""
)
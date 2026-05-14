package com.example.nexvolunteer.model

data class User(

    val uid: String = "",

    val nombre: String = "",

    val apellido: String = "",

    val edad: String = "",

    val dni: String = "",

    val sexo: String = "",

    val correo: String = "",

    val rango: String = "🌱 Semilla",

    val eventosAsistidos: Int = 0,

    val tipo: String = "usuario",

    val historialEventos: List<String> = emptyList(),

    val favoritos: List<String> = emptyList()
)
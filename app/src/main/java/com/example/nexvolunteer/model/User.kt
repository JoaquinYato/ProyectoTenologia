package com.example.nexvolunteer.model

data class User(

    val uid: String = "",

    val nombre: String = "",

    val apellido: String = "",

    val correo: String = "",

    val edad: String = "",

    val dni: String = "",

    val sexo: String = "",

    val intereses: List<String> = emptyList(),

    val tipo: String = "usuario",

    val favoritos: List<String> = emptyList(),

    val eventosAsistidos: List<String> = emptyList(),

    val xp: Int = 0,

    val rango: String = "🌱 EXPLORADOR"
)
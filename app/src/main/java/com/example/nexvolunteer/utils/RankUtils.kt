package com.example.nexvolunteer.utils

object RankUtils {

    fun getRank(events: Int): String {

        return when {

            events >= 50 -> "👑 LÍDER"

            events >= 35 -> "🛡 Mentor"

            events >= 20 -> "🔥 Agente"

            events >= 10 -> "⭐ Impulsor"

            events >= 5 -> "🤝 Colaborador"

            else -> "🌱 Semilla"
        }
    }

    fun canCreateEvents(

        rango: String,

        tipo: String
    ): Boolean {

        return tipo == "partner" ||

                rango == "👑 LÍDER"
    }
}
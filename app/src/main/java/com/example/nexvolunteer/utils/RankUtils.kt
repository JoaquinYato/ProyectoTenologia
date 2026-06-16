package com.example.nexvolunteer.utils

object RankUtils {

    fun getRank(events: Int): String {

        return when {

            events >= 50 -> "👑 LÍDER"

            events >= 35 -> "🛡 COORDINADOR"

            events >= 20 -> "🔥 MENTOR"

            events >= 10 -> "⭐ IMPULSOR"

            events >= 5 -> "🤝 COLABORADOR"

            else -> "🌱 EXPLORADOR"
        }
    }

    fun getProgress(events: Int): Float {

        return when {

            events >= 50 -> 1f

            events >= 35 -> (events - 35) / 15f

            events >= 20 -> (events - 20) / 15f

            events >= 10 -> (events - 10) / 10f

            events >= 5 -> (events - 5) / 5f

            else -> events / 5f
        }
    }

    fun getXp(events: Int): Int {

        return events * 100
    }

    fun canCreateEvents(

        rango: String,

        tipo: String

    ): Boolean {
        return tipo == "partner"
    }
}
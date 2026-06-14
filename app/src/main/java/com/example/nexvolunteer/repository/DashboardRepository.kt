package com.example.nexvolunteer.repository

import com.google.firebase.firestore.FirebaseFirestore

class DashboardRepository {

    private val db = FirebaseFirestore.getInstance()

    fun getUserStats(
        uid: String,
        onSuccess: (Int, Int, String) -> Unit,
        onError: (String) -> Unit
    ) {

        db.collection("users")
            .document(uid)
            .get()
            .addOnSuccessListener {

                val eventos =
                    (it.get("eventosAsistidos") as? List<*>)?.size ?: 0

                val xp =
                    it.getLong("xp")?.toInt() ?: 0

                val rango =
                    it.getString("rango") ?: "🌱 EXPLORADOR"

                onSuccess(
                    eventos,
                    xp,
                    rango
                )
            }
            .addOnFailureListener {

                onError(it.message ?: "Error")
            }
    }
}
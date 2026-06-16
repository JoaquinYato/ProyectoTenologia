package com.example.nexvolunteer.repository

import com.example.nexvolunteer.model.Partner
import com.example.nexvolunteer.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class AuthRepository {

    private val auth = FirebaseAuth.getInstance()

    private val db = FirebaseFirestore.getInstance()

    fun registerUser(

        email: String,
        password: String,
        user: User,

        onSuccess: () -> Unit,
        onError: (String) -> Unit

    ) {

        auth.createUserWithEmailAndPassword(email, password)

            .addOnSuccessListener {

                val uid = auth.currentUser?.uid ?: ""

                val newUser = user.copy(uid = uid)

                db.collection("users")
                    .document(uid)
                    .set(newUser)

                    .addOnSuccessListener {

                        android.util.Log.d(
                            "FIRESTORE_TEST",
                            "USUARIO GUARDADO"
                        )

                        onSuccess()
                    }

                    .addOnFailureListener {

                        android.util.Log.e(
                            "FIRESTORE_TEST",
                            it.message ?: "ERROR"
                        )

                        onError(it.message ?: "ERROR")
                    }

                    .addOnSuccessListener {
                        onSuccess()
                    }

                    .addOnFailureListener {

                        android.util.Log.e(
                            "FIREBASE_ERROR",
                            it.message ?: "Error"
                        )

                        onError(it.message ?: "Error")
                    }
            }

            .addOnFailureListener {

                android.util.Log.e(
                    "FIREBASE_ERROR",
                    it.message ?: "Error"
                )

                onError(it.message ?: "Error")
            }
    }

    fun registerPartner(

        email: String,
        password: String,
        partner: Partner,

        onSuccess: () -> Unit,
        onError: (String) -> Unit

    ) {

        auth.createUserWithEmailAndPassword(email, password)

            .addOnSuccessListener {

                val uid = auth.currentUser?.uid ?: ""

                db.collection("users")
                    .document(uid)
                    .set(
                        mapOf(
                            "uid" to uid,

                            "nombre" to partner.empresa,
                            "apellido" to "",

                            "correo" to partner.correo,

                            "edad" to "",
                            "dni" to partner.ruc,
                            "sexo" to "",

                            "intereses" to emptyList<String>(),
                            "favoritos" to emptyList<String>(),
                            "eventosAsistidos" to emptyList<String>(),

                            "xp" to 0,

                            "tipo" to "partner",

                            "rango" to "🏢 PARTNER"
                        )
                    )

                    .addOnSuccessListener {
                        onSuccess()
                    }

                    .addOnFailureListener {

                        android.util.Log.e(
                            "FIREBASE_ERROR",
                            it.message ?: "Error"
                        )

                        onError(it.message ?: "Error")
                    }
            }

            .addOnFailureListener {

                android.util.Log.e(
                    "FIREBASE_ERROR",
                    it.message ?: "Error"
                )

                onError(it.message ?: "Error")
            }
    }

    fun login(

        email: String,
        password: String,

        onSuccess: () -> Unit,
        onError: (String) -> Unit

    ) {

        auth.signInWithEmailAndPassword(email, password)

            .addOnSuccessListener {
                onSuccess()
            }

            .addOnFailureListener {

                android.util.Log.e(
                    "FIREBASE_ERROR",
                    it.message ?: "Error"
                )

                onError(it.message ?: "Error")
            }
    }
}
package com.example.nexvolunteer.screens.profile

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.nexvolunteer.viewmodel.UserViewModel
import com.google.firebase.auth.FirebaseAuth
import com.example.nexvolunteer.utils.RankUtils

@Composable
fun ProfileScreen() {

    val viewModel = remember {

        UserViewModel()
    }

    val uid =
        FirebaseAuth.getInstance().currentUser?.uid ?: ""

    LaunchedEffect(Unit) {

        viewModel.loadUser(uid)
    }

    val user =
        viewModel.user.value

    Column(

        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)

    ) {

        Text(

            text = "Perfil",

            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(

            text = "👤 ${user.nombre} ${user.apellido}",

            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text("📧 ${user.correo}")

        Spacer(modifier = Modifier.height(20.dp))

        Text(

            text = user.rango,

            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(12.dp))

        LinearProgressIndicator(

            progress = {

                RankUtils.getProgress(
                    user.eventosAsistidos.size
                )
            },

            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text("⭐ XP: ${user.xp}")

        Spacer(modifier = Modifier.height(8.dp))

        Text(

            text = "🎯 Eventos asistidos: ${user.eventosAsistidos.size}"
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(

            text = "Historial de eventos",

            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(10.dp))

        if (user.eventosAsistidos.isEmpty()) {

            Text("Aún no participas en eventos")

        } else {

            viewModel.attendedEvents.forEach { event ->

                Card(

                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)

                ) {

                    Column(

                        modifier = Modifier.padding(16.dp)

                    ) {

                        Text(
                            text = event.titulo,
                            style = MaterialTheme.typography.titleMedium
                        )

                        Text("📍 ${event.ubicacion}")

                        Text("📅 ${event.fecha}")

                        Text("🏷️ ${event.categoria}")
                    }
                }
            }
        }
    }
}
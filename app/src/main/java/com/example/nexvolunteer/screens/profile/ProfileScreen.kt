package com.example.nexvolunteer.screens.profile

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.nexvolunteer.viewmodel.UserViewModel
import com.google.firebase.auth.FirebaseAuth

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

        Text("👤 ${user.nombre} ${user.apellido}")

        Spacer(modifier = Modifier.height(10.dp))

        Text("📧 ${user.correo}")

        Spacer(modifier = Modifier.height(10.dp))

        Text("🏆 ${user.rango}")

        Spacer(modifier = Modifier.height(10.dp))

        Text("🎯 Eventos asistidos: ${user.eventosAsistidos}")

        Spacer(modifier = Modifier.height(20.dp))

        Text(

            text = "Historial",

            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(10.dp))

        user.historialEventos.forEach {

            Text("• Evento ID: $it")
        }
    }
}
package com.example.nexvolunteer.screens.dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.nexvolunteer.viewmodel.DashboardViewModel
import com.google.firebase.auth.FirebaseAuth

@Composable
fun DashboardScreen() {

    val viewModel = remember {
        DashboardViewModel()
    }

    val uid =
        FirebaseAuth.getInstance().currentUser?.uid ?: ""

    LaunchedEffect(Unit) {

        viewModel.loadStats(uid)
    }

    Column(

        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)

    ) {

        Text(

            text = "Dashboard",

            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(20.dp))

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {

            Column(
                modifier = Modifier.padding(16.dp)
            ) {

                Text("🏆 Rango")

                Text(viewModel.rango.value)
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {

            Column(
                modifier = Modifier.padding(16.dp)
            ) {

                Text("⭐ XP")

                Text("${viewModel.xp.intValue}")
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {

            Column(
                modifier = Modifier.padding(16.dp)
            ) {

                Text("🎯 Eventos asistidos")

                Text("${viewModel.eventos.intValue}")
            }
        }
    }
}
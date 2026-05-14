package com.example.nexvolunteer.screens.partner

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.nexvolunteer.navigation.Routes
import com.example.nexvolunteer.viewmodel.PartnerViewModel

@Composable
fun PartnerDashboardScreen(

    navController: NavController
) {

    val viewModel = remember {

        PartnerViewModel()
    }

    LaunchedEffect(Unit) {

        viewModel.loadEvents()
    }

    val totalEvents =
        viewModel.events.size

    val approvedEvents =
        viewModel.events.count {

            it.aprobado
        }

    val featuredEvents =
        viewModel.events.count {

            it.destacado
        }

    Column(

        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)

    ) {

        Text(

            text = "Panel Partner",

            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(20.dp))

        Card(

            modifier = Modifier.fillMaxWidth()
        ) {

            Column(

                modifier = Modifier.padding(16.dp)
            ) {

                Text("📊 Total Eventos: $totalEvents")

                Spacer(modifier = Modifier.height(8.dp))

                Text("✅ Aprobados: $approvedEvents")

                Spacer(modifier = Modifier.height(8.dp))

                Text("⭐ Destacados: $featuredEvents")
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(

            onClick = {

                navController.navigate(
                    Routes.ManageEvents.route
                )
            },

            modifier = Modifier.fillMaxWidth()

        ) {

            Text("Administrar Eventos")
        }
    }
}
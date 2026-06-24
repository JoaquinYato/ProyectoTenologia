package com.example.nexvolunteer.screens.event

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.nexvolunteer.viewmodel.EventViewModel

@Composable
fun EventDetailScreen(

    eventId: String

) {

    val context = LocalContext.current

    val viewModel = remember {

        EventViewModel()
    }

    LaunchedEffect(Unit) {

        viewModel.loadEvent(eventId)
    }

    val event =
        viewModel.selectedEvent

    Column(

        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(
                rememberScrollState()
            )
            .padding(16.dp)

    ) {

        if (event.imageUrl.isNotEmpty()) {

            AsyncImage(

                model = event.imageUrl,

                contentDescription = null,

                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
            )
        }

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        Text(

            text = event.titulo,

            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        Text("📍 ${event.ubicacion}")

        Spacer(
            modifier = Modifier.height(4.dp)
        )

        Text("📅 ${event.fecha}")

        Spacer(
            modifier = Modifier.height(4.dp)
        )

        Text("🏷️ ${event.categoria}")

        Spacer(
            modifier = Modifier.height(4.dp)
        )

        Text("👥 ${event.participantes} participantes")

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        TextButton(

            onClick = {

                val intent = Intent(

                    Intent.ACTION_VIEW,

                    Uri.parse(
                        "https://www.google.com/maps/search/?api=1&query=${event.ubicacion}"
                    )
                )

                context.startActivity(intent)
            }

        ) {

            Text("📍 Ver en Google Maps")
        }

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        Text(

            text = event.descripcion,

            style = MaterialTheme.typography.bodyLarge
        )
    }
}
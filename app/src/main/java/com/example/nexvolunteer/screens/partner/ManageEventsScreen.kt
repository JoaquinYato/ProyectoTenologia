package com.example.nexvolunteer.screens.partner

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.nexvolunteer.viewmodel.PartnerViewModel

@Composable
fun ManageEventsScreen() {

    val context = LocalContext.current

    val viewModel = remember {

        PartnerViewModel()
    }

    LaunchedEffect(Unit) {

        viewModel.loadEvents()
    }

    LazyColumn(

        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)

    ) {

        items(viewModel.events) { event ->

            Card(

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)

            ) {

                Column(

                    modifier = Modifier.padding(16.dp)
                ) {

                    Text(

                        text = event.titulo,

                        style = MaterialTheme.typography.titleLarge
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(event.descripcion)

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "👥 Participantes: ${event.participantes}"
                    )

                    Row {

                        Button(

                            onClick = {

                                viewModel.approveEvent(

                                    event.id,

                                    onSuccess = {

                                        Toast.makeText(
                                            context,
                                            "Evento aprobado",
                                            Toast.LENGTH_LONG
                                        ).show()

                                        viewModel.loadEvents()
                                    },

                                    onError = {

                                    }
                                )
                            }

                        ) {

                            Text("Aprobar")
                        }

                        Spacer(
                            modifier = Modifier.width(10.dp)
                        )

                        Button(

                            onClick = {

                                viewModel.featureEvent(

                                    event.id,

                                    onSuccess = {

                                        Toast.makeText(
                                            context,
                                            "Evento destacado",
                                            Toast.LENGTH_LONG
                                        ).show()

                                        viewModel.loadEvents()
                                    },

                                    onError = {

                                    }
                                )
                            }

                        ) {

                            Text("Destacar")
                        }

                        Spacer(
                            modifier = Modifier.width(10.dp)
                        )

                        Button(

                            onClick = {

                                viewModel.deleteEvent(

                                    event.id,

                                    onSuccess = {

                                        Toast.makeText(
                                            context,
                                            "Evento eliminado",
                                            Toast.LENGTH_LONG
                                        ).show()

                                        viewModel.loadEvents()
                                    },

                                    onError = {

                                        Toast.makeText(
                                            context,
                                            it,
                                            Toast.LENGTH_LONG
                                        ).show()
                                    }
                                )
                            }

                        ) {

                            Text("Eliminar")
                        }
                    }
                }
            }
        }
    }
}
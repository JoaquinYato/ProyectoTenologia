package com.example.nexvolunteer.screens.event

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.nexvolunteer.model.Event
import com.example.nexvolunteer.repository.ImageRepository
import com.example.nexvolunteer.viewmodel.EventViewModel
import com.google.firebase.auth.FirebaseAuth

import com.example.nexvolunteer.ui.theme.customTextFieldColors

@Composable
fun CreateEventScreen(
    navController: NavController
) {

    val context = LocalContext.current

    val viewModel = remember {
        EventViewModel()
    }

    val imageRepository = remember {
        ImageRepository()
    }

    var titulo by remember {
        mutableStateOf("")
    }

    var descripcion by remember {
        mutableStateOf("")
    }

    var categoria by remember {
        mutableStateOf("")
    }

    var ubicacion by remember {
        mutableStateOf("")
    }

    var fecha by remember {
        mutableStateOf("")
    }

    var selectedImageUri by remember {
        mutableStateOf<Uri?>(null)
    }

    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) {
            selectedImageUri = it
        }


    Column(

        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)

    ) {

        Text(

            text = "Crear Evento",

            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(

            value = titulo,

            onValueChange = {
                titulo = it
            },

            label = {
                Text("Título")
            },

            modifier = Modifier.fillMaxWidth()

        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(

            value = descripcion,

            onValueChange = {
                descripcion = it
            },

            label = {
                Text("Descripción")
            },

            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(

            value = categoria,

            onValueChange = {
                categoria = it
            },

            label = {
                Text("Categoría")
            },

            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(

            value = ubicacion,

            onValueChange = {
                ubicacion = it
            },

            label = {
                Text("Ubicación")
            },

            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(

            value = fecha,

            onValueChange = {
                fecha = it
            },

            label = {
                Text("Fecha")
            },

            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(

            onClick = {

                launcher.launch("image/*")
            },

            modifier = Modifier.fillMaxWidth()

        ) {

            Text("Seleccionar Imagen")
        }

        Spacer(modifier = Modifier.height(10.dp))

        selectedImageUri?.let {

            AsyncImage(

                model = it,

                contentDescription = null,

                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(

            onClick = {

                if (

                    titulo.isEmpty() ||
                    descripcion.isEmpty() ||
                    categoria.isEmpty() ||
                    ubicacion.isEmpty() ||
                    fecha.isEmpty()

                ) {

                    Toast.makeText(

                        context,

                        "Complete todos los campos",

                        Toast.LENGTH_LONG

                    ).show()

                    return@Button
                }

                val uid =
                    FirebaseAuth.getInstance()
                        .currentUser?.uid ?: ""

                val baseEvent = Event(

                    titulo = titulo,

                    descripcion = descripcion,

                    categoria = categoria,

                    ubicacion = ubicacion,

                    fecha = fecha,

                    creadorId = uid,

                    creadorTipo = "usuario"
                )

                if (selectedImageUri != null) {

                    imageRepository.uploadImage(

                        context,

                        selectedImageUri!!,

                        onSuccess = { imageUrl ->

                            val event =
                                baseEvent.copy(

                                    imageUrl = imageUrl
                                )

                            viewModel.createEvent(

                                event,

                                onSuccess = {

                                    Toast.makeText(

                                        context,

                                        "Evento creado",

                                        Toast.LENGTH_LONG

                                    ).show()

                                    navController.popBackStack()
                                },

                                onError = {

                                        error ->

                                    android.util.Log.e(
                                        "NEX_EVENT",
                                        error
                                    )

                                    Toast.makeText(
                                        context,
                                        "ERROR: $error",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            )
                        },

                        onError = {

                            Toast.makeText(

                                context,

                                it,

                                Toast.LENGTH_LONG

                            ).show()
                        }
                    )

                } else {

                    viewModel.createEvent(

                        baseEvent,

                        onSuccess = {

                            Toast.makeText(

                                context,

                                "Evento creado",

                                Toast.LENGTH_LONG

                            ).show()

                            navController.popBackStack()
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
            },

            modifier = Modifier.fillMaxWidth()

        ) {

            Text("Publicar Evento")
        }
    }
}
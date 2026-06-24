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
import java.text.SimpleDateFormat
import java.util.*
import androidx.compose.foundation.clickable
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import android.location.Geocoder
import java.util.Locale
import com.google.android.gms.maps.model.CameraPosition
import com.google.maps.android.compose.rememberMarkerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.foundation.text.KeyboardOptions

@OptIn(ExperimentalMaterial3Api::class)
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

    var titulo by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var categoria by remember { mutableStateOf("") }
    var ubicacion by remember { mutableStateOf("") }
    var fecha by remember { mutableStateOf("") }

    var selectedImageUri by remember {
        mutableStateOf<Uri?>(null)
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) {
        selectedImageUri = it
    }

    val datePickerState = rememberDatePickerState()

    var showDatePicker by remember {
        mutableStateOf(false)
    }

    var markerPosition by remember {

        mutableStateOf(
            LatLng(-12.0464, -77.0428)
        )
    }

    val cameraPositionState = rememberCameraPositionState()

    val uiSettings = remember {
        MapUiSettings(
            zoomControlsEnabled = true,
            scrollGesturesEnabled = true,
            zoomGesturesEnabled = true,
            mapToolbarEnabled = true
        )
    }

    if (showDatePicker) {

        DatePickerDialog(

            onDismissRequest = {
                showDatePicker = false
            },

            confirmButton = {

                TextButton(

                    onClick = {

                        datePickerState.selectedDateMillis?.let {

                            val formatter =
                                SimpleDateFormat(
                                    "dd/MM/yyyy",
                                    Locale.getDefault()
                                )

                            fecha = formatter.format(Date(it))
                        }

                        showDatePicker = false
                    }

                ) {
                    Text("Aceptar")
                }
            },

            dismissButton = {

                TextButton(

                    onClick = {
                        showDatePicker = false
                    }

                ) {
                    Text("Cancelar")
                }
            }

        ) {

            DatePicker(
                state = datePickerState
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp)

    ) {

        Text(
            text = "Crear Evento",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = titulo,
            onValueChange = { titulo = it },
            label = { Text("Título") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = descripcion,
            onValueChange = { descripcion = it },
            label = { Text("Descripción") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = categoria,
            onValueChange = { categoria = it },
            label = { Text("Categoría") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(

            value = fecha,

            onValueChange = { value ->

                val numbers =
                    value.filter {
                        it.isDigit()
                    }

                val formatted = buildString {

                    numbers.forEachIndexed { index, c ->

                        append(c)

                        if (
                            (index == 1 || index == 3)
                            && index != numbers.lastIndex
                        ) {
                            append("/")
                        }
                    }
                }

                if (formatted.length <= 10) {
                    fecha = formatted
                }
            },

            label = {
                Text("Fecha")
            },

            placeholder = {
                Text("dd/MM/yyyy")
            },

            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),

            trailingIcon = {

                IconButton(

                    onClick = {
                        showDatePicker = true
                    }

                ) {

                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "Calendario"
                    )
                }
            },

            modifier = Modifier.fillMaxWidth()
        )

        Text(

            text = "Ubicación: $ubicacion",

            style = MaterialTheme.typography.titleMedium

        )

        Spacer(modifier = Modifier.height(8.dp))

        GoogleMap(

            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
            cameraPositionState = cameraPositionState,
            uiSettings = uiSettings,
            onMapClick = { latLng ->
                markerPosition = latLng
                ubicacion = "${latLng.latitude}, ${latLng.longitude}"
            }
        ) {

            Marker(

                state = rememberMarkerState(
                    position = markerPosition
                ),

                title = "Evento"
            )
        }

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        if (ubicacion.isNotEmpty()) {

            Text(

                text = "📍 $ubicacion",

                style = MaterialTheme.typography.titleMedium

            )
        }


        OutlinedTextField(

            value = ubicacion,

            onValueChange = {},

            readOnly = true,

            label = {
                Text("Dirección seleccionada")
            },

            modifier = Modifier.fillMaxWidth()

        )
        Spacer(modifier = Modifier.height(10.dp))


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

                if (!isValidDate(fecha)) {

                    Toast.makeText(

                        context,

                        "Ingrese una fecha válida (dd/MM/yyyy)",

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
                    creadorTipo = "partner",
                    aprobado = true
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

                                onError = { error ->

                                    android.util.Log.e(
                                        "FIREBASE",
                                        error
                                    )

                                    Toast.makeText(
                                        context,
                                        error,
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            )
                        },

                        onError = { error ->

                            Toast.makeText(
                                context,
                                error,
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

                        onError = { error ->

                            Toast.makeText(
                                context,
                                error,
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

private fun isValidDate(
    date: String
): Boolean {

    return try {

        val sdf =
            SimpleDateFormat(
                "dd/MM/yyyy",
                Locale.getDefault()
            )

        sdf.isLenient = false

        sdf.parse(date)

        true

    } catch (e: Exception) {

        false
    }
}
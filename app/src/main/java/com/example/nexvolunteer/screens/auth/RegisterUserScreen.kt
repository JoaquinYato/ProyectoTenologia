package com.example.nexvolunteer.screens.auth

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.nexvolunteer.model.User
import com.example.nexvolunteer.viewmodel.AuthViewModel

@Composable
fun RegisterUserScreen(navController: NavController) {

    val context = LocalContext.current

    val viewModel = AuthViewModel()

    var nombre by remember { mutableStateOf("") }
    var apellido by remember { mutableStateOf("") }
    var edad by remember { mutableStateOf("") }
    var dni by remember { mutableStateOf("") }
    var sexo by remember { mutableStateOf("") }

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(

        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)

    ) {

        Text(
            text = "Registro Usuario",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = apellido,
            onValueChange = { apellido = it },
            label = { Text("Apellido") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = edad,
            onValueChange = { edad = it },
            label = { Text("Edad") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = dni,
            onValueChange = { dni = it },
            label = { Text("DNI") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = sexo,
            onValueChange = { sexo = it },
            label = { Text("Sexo") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Correo") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(25.dp))

        Button(

            onClick = {

                if (
                    nombre.isEmpty() ||
                    apellido.isEmpty() ||
                    edad.isEmpty() ||
                    dni.isEmpty() ||
                    sexo.isEmpty() ||
                    email.isEmpty() ||
                    password.isEmpty()
                ) {

                    Toast.makeText(
                        context,
                        "Complete todos los campos",
                        Toast.LENGTH_LONG
                    ).show()

                    return@Button
                }

                val user = User(

                    nombre = nombre,

                    apellido = apellido,

                    edad = edad,

                    dni = dni,

                    sexo = sexo,

                    correo = email
                )

                viewModel.registerUser(

                    email,
                    password,
                    user,

                    onSuccess = {

                        Toast.makeText(
                            context,
                            "Registro exitoso",
                            Toast.LENGTH_LONG
                        ).show()

                        navController.navigate("login")
                    },

                    onError = {

                        Toast.makeText(
                            context,
                            it,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                )
            },

            modifier = Modifier.fillMaxWidth()

        ) {

            Text("Registrarse")
        }
    }
}
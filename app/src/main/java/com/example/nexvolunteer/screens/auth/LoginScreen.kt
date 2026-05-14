package com.example.nexvolunteer.screens.auth

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.nexvolunteer.viewmodel.AuthViewModel

@Composable
fun LoginScreen(navController: NavController) {

    val context = LocalContext.current

    val viewModel = AuthViewModel()

    var email by remember { mutableStateOf("") }

    var password by remember { mutableStateOf("") }

    Column(

        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),

        verticalArrangement = Arrangement.Center

    ) {

        Text(
            text = "NexVolunteer",
            style = MaterialTheme.typography.headlineLarge
        )

        Spacer(modifier = Modifier.height(25.dp))

        OutlinedTextField(

            value = email,

            onValueChange = {
                email = it
            },

            label = {
                Text("Correo")
            },

            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(

            value = password,

            onValueChange = {
                password = it
            },

            label = {
                Text("Contraseña")
            },

            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(25.dp))

        Button(

            onClick = {

                if (
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

                viewModel.login(

                    email,
                    password,

                    onSuccess = {

                        Toast.makeText(
                            context,
                            "Bienvenido",
                            Toast.LENGTH_LONG
                        ).show()

                        navController.navigate("home")
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

            Text("Iniciar Sesión")
        }

        Spacer(modifier = Modifier.height(10.dp))

        TextButton(

            onClick = {

                navController.navigate("registerUser")
            }

        ) {

            Text("Registrarse como Usuario")
        }

        TextButton(

            onClick = {

                navController.navigate("registerPartner")
            }

        ) {

            Text("Registrarse como Partner")
        }
    }
}
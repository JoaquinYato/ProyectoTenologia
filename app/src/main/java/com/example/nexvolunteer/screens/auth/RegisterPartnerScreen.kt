package com.example.nexvolunteer.screens.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.nexvolunteer.model.Partner
import com.example.nexvolunteer.viewmodel.AuthViewModel

@Composable
fun RegisterPartnerScreen(navController: NavController) {

    val viewModel = AuthViewModel()

    var empresa by remember { mutableStateOf("") }
    var ruc by remember { mutableStateOf("") }
    var rubro by remember { mutableStateOf("") }

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(

        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)

    ) {

        Text(
            text = "Registro Partner",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(empresa,{empresa=it},label={Text("Empresa")})

        OutlinedTextField(ruc,{ruc=it},label={Text("RUC")})

        OutlinedTextField(rubro,{rubro=it},label={Text("Rubro")})

        OutlinedTextField(email,{email=it},label={Text("Correo Empresarial")})

        OutlinedTextField(password,{password=it},label={Text("Contraseña")})

        Spacer(modifier = Modifier.height(20.dp))

        Button(

            onClick = {

                val partner = Partner(

                    empresa = empresa,

                    ruc = ruc,

                    rubro = rubro,

                    correo = email
                )

                viewModel.registerPartner(

                    email,
                    password,
                    partner,

                    onSuccess = {

                        navController.navigate("home")
                    },

                    onError = {

                    }
                )
            },

            modifier = Modifier.fillMaxWidth()

        ) {

            Text("Registrar Partner")
        }
    }
}
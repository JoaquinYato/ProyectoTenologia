package com.example.nexvolunteer.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.nexvolunteer.navigation.BottomBar
import com.example.nexvolunteer.navigation.Routes
import com.example.nexvolunteer.viewmodel.EventViewModel
import com.example.nexvolunteer.utils.RankUtils
import com.example.nexvolunteer.viewmodel.UserViewModel
import com.google.firebase.auth.FirebaseAuth

@Composable
fun HomeScreen(navController: NavController) {

    val viewModel = remember {

        EventViewModel()
    }

    LaunchedEffect(Unit) {

        viewModel.loadEvents()
    }

    val userViewModel = remember {

        UserViewModel()
    }

    val uid =
        FirebaseAuth.getInstance().currentUser?.uid ?: ""

    LaunchedEffect(Unit) {

        userViewModel.loadUser(uid)

        viewModel.loadEvents()
    }

    Scaffold(

        floatingActionButton = {
            if (userViewModel.user.value.tipo == "partner") {
                FloatingActionButton(

                    onClick = {
                        navController.navigate(Routes.CreateEvent.route)
                    }
                )
            {

                Icon(Icons.Default.Add, null)
            }
        }
        },
        bottomBar = {

            BottomBar(navController)
        }

    ) { padding ->

        Column(

            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)

        ) {

            Text(

                text = "Eventos",

                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(

                text = "⭐ Eventos Destacados",

                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(10.dp))

            viewModel.events.filter {

                it.destacado

            }.forEach {

                EventCard(it)
            }

            LazyColumn {

                items(viewModel.events) { event ->

                    EventCard(event)
                }
            }
        }
    }
}
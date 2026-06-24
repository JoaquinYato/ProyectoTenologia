package com.example.nexvolunteer.screens.search

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.nexvolunteer.screens.home.EventCard
import com.example.nexvolunteer.viewmodel.SearchViewModel
import androidx.navigation.NavController

@Composable
fun SearchScreen(navController: NavController) {

    val viewModel = remember {

        SearchViewModel()
    }

    var searchText by remember {

        mutableStateOf("")
    }

    LaunchedEffect(Unit) {

        viewModel.loadEvents()
    }

    Column(

        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)

    ) {

        Text(

            text = "Buscar Eventos",

            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(

            value = searchText,

            onValueChange = {

                searchText = it

                viewModel.searchEvents(it)
            },

            label = {

                Text("Buscar por nombre, categoria o ubicación")
            },

            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(

            modifier = Modifier
                .horizontalScroll(
                    rememberScrollState()
                )
        ) {

            listOf(

                "Ambiente",

                "Tecnología",

                "Sociedad",

                "Educación"

            ).forEach { category ->

                FilterChip(

                    selected = false,

                    onClick = {

                        viewModel.filterCategory(
                            category
                        )
                    },

                    label = {

                        Text(category)
                    },

                    modifier = Modifier
                        .padding(end = 8.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {

            items(viewModel.filteredEvents) {

                EventCard(
                    event = it,
                    navController = navController
                )
            }
        }
    }
}
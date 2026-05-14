package com.example.nexvolunteer.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.nexvolunteer.navigation.Routes

@Composable
fun BottomBar(

    navController: NavController
) {

    val items = listOf(

        Triple(
            Routes.Home.route,
            "Inicio",
            Icons.Default.Home
        ),

        Triple(
            Routes.Search.route,
            "Buscar",
            Icons.Default.Search
        ),

        Triple(
            Routes.Chat.route,
            "Chat",
            Icons.Default.Chat
        ),

        Triple(
            Routes.Profile.route,
            "Perfil",
            Icons.Default.Person
        )
    )

    NavigationBar {

        val navBackStackEntry =
            navController.currentBackStackEntryAsState()

        val currentRoute =
            navBackStackEntry.value
                ?.destination
                ?.route

        items.forEach { item ->

            NavigationBarItem(

                selected =
                    currentRoute == item.first,

                onClick = {

                    navController.navigate(
                        item.first
                    )
                },

                icon = {

                    Icon(
                        item.third,
                        contentDescription = null
                    )
                },

                label = {

                    Text(item.second)
                }
            )
        }
    }
}
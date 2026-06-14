package com.example.nexvolunteer.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.*
import com.example.nexvolunteer.screens.auth.LoginScreen
import com.example.nexvolunteer.screens.auth.RegisterPartnerScreen
import com.example.nexvolunteer.screens.auth.RegisterUserScreen
import com.example.nexvolunteer.screens.chat.ChatScreen
import com.example.nexvolunteer.screens.home.HomeScreen
import com.example.nexvolunteer.screens.profile.ProfileScreen
import com.example.nexvolunteer.screens.search.SearchScreen
import com.example.nexvolunteer.screens.event.CreateEventScreen
import com.example.nexvolunteer.screens.partner.ManageEventsScreen
import com.example.nexvolunteer.screens.partner.PartnerDashboardScreen
import com.example.nexvolunteer.screens.splash.SplashScreen
import com.example.nexvolunteer.screens.dashboard.DashboardScreen
@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(

        navController = navController,

        startDestination = Routes.Splash.route

    ) {

        composable(
            Routes.Splash.route
        ) {

            SplashScreen(navController)
        }

        composable(Routes.Login.route) {

            LoginScreen(navController)
        }

        composable(Routes.RegisterUser.route) {

            RegisterUserScreen(navController)
        }

        composable(Routes.RegisterPartner.route) {

            RegisterPartnerScreen(navController)
        }

        composable(Routes.Home.route) {

            HomeScreen(navController)
        }

        composable(Routes.Search.route) {

            SearchScreen()
        }

        composable(Routes.Chat.route) {

            ChatScreen()
        }

        composable(Routes.Profile.route) {

            ProfileScreen()
        }

        composable(Routes.CreateEvent.route) {

            CreateEventScreen(navController)
        }

        composable(
            Routes.PartnerDashboard.route
        ) {

            PartnerDashboardScreen(navController)
        }

        composable(
            Routes.ManageEvents.route
        ) {

            ManageEventsScreen()
        }

        composable("dashboard") {

            DashboardScreen()
        }
    }
}
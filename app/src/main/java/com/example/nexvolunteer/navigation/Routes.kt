package com.example.nexvolunteer.navigation

sealed class Routes(val route: String) {

    object Home : Routes("home")

    object Search : Routes("search")

    object Chat : Routes("chat")

    object Profile : Routes("profile")

    object Login : Routes("login")

    object RegisterUser : Routes("registerUser")

    object RegisterPartner : Routes("registerPartner")

    object CreateEvent : Routes("createEvent")

    object PartnerDashboard :
        Routes("partnerDashboard")

    object ManageEvents :
        Routes("manageEvents")

    object Splash :
        Routes("splash")

    object Dashboard : Routes("dashboard")
}
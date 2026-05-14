package com.example.nexvolunteer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.nexvolunteer.navigation.AppNavigation
import com.example.nexvolunteer.notifications.NotificationHelper
import com.example.nexvolunteer.ui.theme.NexVolunteerTheme
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestoreSettings

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        /*
            FIRESTORE OFFLINE
         */

        val db = FirebaseFirestore.getInstance()

        val settings = firestoreSettings {
            isPersistenceEnabled = true
        }

        db.firestoreSettings = settings


        /*
            NOTIFICACIONES
         */

        NotificationHelper.createChannel(this)

        /*
            UI
         */

        setContent {
            NexVolunteerTheme {
                AppNavigation()
            }
        }
    }
}
package com.example.nexvolunteer.notifications

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class EventReminderReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val title =
            intent.getStringExtra("title") ?: "Evento"
        NotificationHelper.showNotification(context, "Recordatorio Evento", "Hoy tienes: $title")
    }
}
package com.example.nexvolunteer.notifications

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent

object ReminderHelper {

    fun scheduleReminder(

        context: Context,

        title: String,

        triggerTime: Long
    ) {

        val intent = Intent(

            context,

            EventReminderReceiver::class.java
        )

        intent.putExtra("title", title)

        val pendingIntent =
            PendingIntent.getBroadcast(

                context,

                title.hashCode(),

                intent,

                PendingIntent.FLAG_UPDATE_CURRENT or
                        PendingIntent.FLAG_IMMUTABLE
            )

        val alarmManager =
            context.getSystemService(

                Context.ALARM_SERVICE

            ) as AlarmManager

        alarmManager.setExact(

            AlarmManager.RTC_WAKEUP,

            triggerTime,

            pendingIntent
        )
    }
}
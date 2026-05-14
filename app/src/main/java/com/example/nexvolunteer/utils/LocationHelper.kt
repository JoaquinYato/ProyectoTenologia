package com.example.nexvolunteer.utils

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationManager

object LocationHelper {

    @SuppressLint("MissingPermission")
    fun getLastLocation(

        context: Context
    ): Location? {

        val manager =
            context.getSystemService(

                Context.LOCATION_SERVICE

            ) as LocationManager

        val providers =
            manager.getProviders(true)

        var bestLocation: Location? = null

        for (provider in providers) {

            val location =
                manager.getLastKnownLocation(
                    provider
                )

            if (

                location != null &&
                (
                        bestLocation == null ||
                                location.accuracy <
                                bestLocation.accuracy
                        )

            ) {

                bestLocation = location
            }
        }

        return bestLocation
    }
}
package com.example.nexvolunteer.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable

private val DarkColors = darkColorScheme(

    primary = PrimaryBlue,

    secondary = AccentGreen,

    background = DarkBackground,

    surface = CardDark
)

private val LightColors = lightColorScheme(

    primary = PrimaryBlue,

    secondary = AccentGreen,

    background = LightBackground
)

@Composable
fun NexVolunteerTheme(

    darkTheme: Boolean =
        isSystemInDarkTheme(),

    content: @Composable () -> Unit
) {

    MaterialTheme(

        colorScheme = if (darkTheme)

            DarkColors

        else

            LightColors,

        typography = Typography(),

        content = content
    )
}
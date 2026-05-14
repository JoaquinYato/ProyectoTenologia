package com.example.nexvolunteer.screens.splash

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.nexvolunteer.R
import com.example.nexvolunteer.navigation.Routes
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(

    navController: NavController
) {

    var startAnimation by remember {

        mutableStateOf(false)
    }

    val alphaAnim by animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f, animationSpec = tween(2000), label = ""
    )

    LaunchedEffect(true) {

        startAnimation = true

        delay(2500)

        navController.navigate(Routes.Login.route) {
            popUpTo(0)
        }
    }

    Box(

        modifier = Modifier.fillMaxSize(),

        contentAlignment = Alignment.Center
    ) {

        Column(

            horizontalAlignment = Alignment.CenterHorizontally,

            modifier = Modifier.alpha(alphaAnim)
        ) {

            Image(painter = painterResource(id = R.drawable.logo), contentDescription = null)

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "NexVolunteer", style = MaterialTheme.typography.headlineMedium)
        }
    }
}
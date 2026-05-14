package com.example.nexvolunteer.ui.theme

import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun customTextFieldColors() =

    OutlinedTextFieldDefaults.colors(

        focusedTextColor = Color.Black,

        unfocusedTextColor = Color.Black,

        focusedLabelColor = PrimaryBlue,

        unfocusedLabelColor = Color.Gray,

        focusedBorderColor = PrimaryBlue,

        unfocusedBorderColor = Color.Gray,

        cursorColor = PrimaryBlue,

        focusedContainerColor = Color.White,

        unfocusedContainerColor = Color.White,

        focusedPlaceholderColor = Color.Gray,

        unfocusedPlaceholderColor = Color.Gray
    )
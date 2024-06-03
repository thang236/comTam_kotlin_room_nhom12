package com.example.comtam_kotlin_room.ui.screen.login

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.comtam_kotlin_room.utils.Route

@Composable
fun LoginScreen(navController: NavHostController) {
    Text(text = "LoginScreen")

    Button(onClick = {
        navController.navigate(Route.Home.screen)
    }) {
        Text(text = "Chuyển")

    }
}
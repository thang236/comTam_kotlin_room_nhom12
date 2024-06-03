package com.example.comtam_kotlin_room.ui.screen.welcome

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.comtam_kotlin_room.utils.Route

@Composable
fun WelcomeScreen(navController: NavHostController) {

    Text(text = "Welcome")
    Button(onClick = {
        navController.navigate(Route.MANAGER.screen)
    }) {
        Text(text = "Chuyển")

    }

}
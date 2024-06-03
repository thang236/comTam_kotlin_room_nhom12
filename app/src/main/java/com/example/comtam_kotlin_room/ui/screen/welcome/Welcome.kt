package com.example.comtam_kotlin_room.ui.screen.welcome

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.comtam_kotlin_room.utils.Route

@Composable
fun WelcomeScreen(navController: NavHostController) {
    Column(modifier = Modifier.padding(50.dp)) {
        Text(text = "Welcome")
        Button(onClick = {
            navController.navigate(Route.MANAGER.screen)
        }) {
            Text(text = "Chuyển")

        }
    }



}
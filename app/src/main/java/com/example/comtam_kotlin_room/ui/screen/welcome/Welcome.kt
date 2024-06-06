package com.example.comtam_kotlin_room.ui.screen.welcome

import android.os.Handler
import android.os.Looper
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.comtam_kotlin_room.R
import com.example.comtam_kotlin_room.utils.Route
import kotlinx.coroutines.delay

@Composable
fun WelcomeScreen(navController: NavHostController) {

    LaunchedEffect(key1 = true) {
        delay(3000L)
        navController.navigate(Route.MANAGER.screen) {
            popUpTo(Route.WELCOME.screen) { inclusive = true }
        }
    }
    Column(modifier = Modifier.fillMaxSize().background(Color(0xff282222)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image( modifier = Modifier.fillMaxWidth(1f),
            painter =  painterResource(id = R.drawable.logo),
            contentDescription ="",
            contentScale = ContentScale.FillWidth
        )

    }



}
package com.example.comtam_kotlin_room

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.comtam_kotlin_room.ui.screen.BottomNavigation
import com.example.comtam_kotlin_room.ui.screen.home.HomeScreen
import com.example.comtam_kotlin_room.ui.screen.login.LoginScreen
import com.example.comtam_kotlin_room.ui.screen.welcome.WelcomeScreen
import com.example.comtam_kotlin_room.ui.theme.ComTam_kotlin_roomTheme
import com.example.comtam_kotlin_room.utils.Route

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComTam_kotlin_roomTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Route.WELCOME.screen
                ) {
                    composable(Route.WELCOME.screen) {
                        WelcomeScreen(navController)
                    }
                    composable(Route.Home.screen){
                        BottomNavigation()

                    }
                    composable(Route.MANAGER.screen){
                        LoginScreen(navController)
                    }

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComTam_kotlin_roomTheme {
        Greeting("Android")
    }
}
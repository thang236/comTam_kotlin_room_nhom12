package com.example.comtam_kotlin_room

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.comtam_kotlin_room.data.Database
import com.example.comtam_kotlin_room.ui.screen.BottomNavigation
import com.example.comtam_kotlin_room.ui.screen.category.CategoryViewModel
import com.example.comtam_kotlin_room.ui.screen.home.HomeScreen
import com.example.comtam_kotlin_room.ui.screen.login.LoginScreen
import com.example.comtam_kotlin_room.ui.screen.welcome.WelcomeScreen
import com.example.comtam_kotlin_room.ui.theme.ComTam_kotlin_roomTheme
import com.example.comtam_kotlin_room.utils.Route

class MainActivity : ComponentActivity() {
    val database by lazy {
        Room.databaseBuilder(
            applicationContext,
            Database::class.java,"comtam.db"
        ).build()
    }

    private val viewModelCategory by viewModels<CategoryViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory{
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return CategoryViewModel(database.dao) as T
                }
            }
        }
    )
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
                        BottomNavigation(viewModelCategory)

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
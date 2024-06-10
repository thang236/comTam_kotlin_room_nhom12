package com.example.comtam_kotlin_room

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import com.example.comtam_kotlin_room.ui.screen.BottomNavigationUser
import com.example.comtam_kotlin_room.ui.screen.category.CategoryScreen
import com.example.comtam_kotlin_room.ui.screen.category.CategoryViewModel
import com.example.comtam_kotlin_room.ui.screen.dish.AddDishScreen
import com.example.comtam_kotlin_room.ui.screen.dish.DishViewModel
import com.example.comtam_kotlin_room.ui.screen.dish.ManagerDishScreen


import com.example.comtam_kotlin_room.ui.screen.home.HomeScreen

import com.example.comtam_kotlin_room.ui.screen.home.OderCartViewModel

import com.example.comtam_kotlin_room.ui.screen.login.LoginScreen
import com.example.comtam_kotlin_room.ui.screen.register.Register
import com.example.comtam_kotlin_room.ui.screen.welcome.WelcomeScreen
import com.example.comtam_kotlin_room.ui.screen_user.person_user.ChangeImageUser
import com.example.comtam_kotlin_room.ui.screen_user.person_user.EditPersonUser
import com.example.comtam_kotlin_room.ui.screen_user.person_user.PersonUserScreen
import com.example.comtam_kotlin_room.ui.theme.ComTam_kotlin_roomTheme
import com.example.comtam_kotlin_room.utils.Route


lateinit var DATABASE_INSTANCE : Database

val DB_NAME = "comtam7.db"


class MainActivity : ComponentActivity() {
    val database by lazy {
        Room.databaseBuilder(
            applicationContext,
            Database::class.java, DB_NAME
        ).build()
    }

    private val viewModelCategory by viewModels<CategoryViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory{
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return CategoryViewModel(database.categoryDao) as T
                }
            }
        }
    )

    private val viewModelDish by viewModels<DishViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory{
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return DishViewModel(database.DishDao) as T
                }
            }
        }
    )

    private val oderCartViewModel by viewModels<OderCartViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory{
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return OderCartViewModel(database.oderCartDao) as T
                }
            }
        }
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val state = viewModelCategory.state.collectAsState().value

            val Dishstate = viewModelDish.state.collectAsState().value


            DATABASE_INSTANCE = database



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
                        BottomNavigation(viewModelCategory, navController,oderCartViewModel)

                    }
                    composable(Route.LOGIN.screen){
                        LoginScreen(navController)
                    }

                    composable(Route.ManegerDish.screen){
                        ManagerDishScreen(
                            state = Dishstate,
                            onEvent = viewModelDish::onEvent,
                        navigationController = navController,
                            dishViewModel = viewModelDish
                        )
                    }
                    composable(Route.AddDish.screen){ AddDishScreen(
                        state = Dishstate,
                        onEvent = viewModelDish::onEvent,
                        navigationController = navController,
                        categoryViewModel = viewModelCategory,
                        dishViewModel = viewModelDish) }

                    composable(Route.Register.screen){
                        Register(navController)
                    }
                    composable(Route.ManegerDish.screen){ ManagerDishScreen(
                        state = Dishstate,
                        onEvent = viewModelDish::onEvent,
                        navigationController = navController,
                        dishViewModel = viewModelDish
                    ) }

                    composable(Route.CategoryScreen.screen) {
                        CategoryScreen(
                            state = state,
                            onEvent = viewModelCategory::onEvent,
                            navController = navController
                        )
                    }


                    //user
                    composable(Route.NavigationUser.screen){ BottomNavigationUser(navController) }
                    composable(Route.EditPersonUser.screen){ EditPersonUser(navController)}
                    composable(Route.ChangImageUser.screen){ ChangeImageUser(navController)}



                    composable(Route.PersonUser.screen){ PersonUserScreen(navController)}
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
package com.example.comtam_kotlin_room.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.rounded.EventNote
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.comtam_kotlin_room.R
import com.example.comtam_kotlin_room.ui.screen_user.cart_user.CartUserScreen
import com.example.comtam_kotlin_room.ui.screen_user.history_user.HistoryUserScreen
import com.example.comtam_kotlin_room.ui.screen_user.home_user.HomeUserScreen
import com.example.comtam_kotlin_room.ui.screen_user.person_user.EditPersonUser
import com.example.comtam_kotlin_room.ui.screen_user.person_user.PersonUserScreen
import com.example.comtam_kotlin_room.utils.Route

@Composable
fun BottomNavigationUser( navController: NavHostController){
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        MyBottomAppBarUser(navController)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyBottomAppBarUser( navController: NavHostController) {

    val navigationController = rememberNavController()
    val selected = remember {
        mutableStateOf(Icons.Default.Home)
    }

    Scaffold (
        topBar = {

            Column(Modifier.fillMaxWidth()) {
                TopAppBar(
                    title = {
                        if (selected.value == Icons.Default.Person){
                            Row (modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center) {
                                Text(text = "Hồ sơ")
                            }
                        } else {
                            Row (modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically) {
                                Image(
                                    painter = painterResource(id = R.drawable.logo),
                                    contentDescription ="",
                                    contentScale = ContentScale.Fit,
                                    modifier = Modifier.fillMaxWidth(0.12f)
                                )
                                Text(text = "Cum tứm đim")

                            }
                        }

                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = if (selected.value == Icons.Default.Person) Color(0xFF000000) else Color(0xff252121),
                        titleContentColor = Color.White,
                    ),

                    )
                Divider(thickness = 2.dp, color = Color.Black)
            }

        },


        bottomBar = {
            Column(Modifier.fillMaxWidth()) {
                Divider(thickness = 2.dp, color = Color.Black)

                BottomAppBar(
                    containerColor = Color(0xFF312C2C)
                ) {
                    IconButton(
                        onClick = {
                            selected.value = Icons.Default.Home
                            navigationController.navigate(Route.HomeUser.screen){
                                popUpTo(0)

                            }
                        },
                        modifier = Modifier.weight(1f)
                    )
                    {
                        Column(
                            verticalArrangement =Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally

                        ) {
                            Icon(   Icons.Default.Home  ,
                                contentDescription = "",
                                modifier = Modifier.size(24.dp),
                                tint = if (selected.value == Icons.Default.Home) Color(0xffFFB703) else Color.White
                            )
                            Text(
                                text = "Trang chủ",
                                    color = if (selected.value == Icons.Default.Home) Color(0xffFFB703) else Color.White
                            )
                        }
                        

                    }

                    IconButton(
                        onClick = {
                            selected.value = Icons.Rounded.EventNote
                            navigationController.navigate(Route.HistoryUser.screen){
                                popUpTo(0)
                            }
                        },
                        modifier = Modifier.weight(1f)
                    )
                    {
                        Column(
                            verticalArrangement =Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally

                        ) {
                            Icon(
                                Icons.Rounded.EventNote,
                                contentDescription = "",
                                modifier = Modifier.size(24.dp),
                                tint = if (selected.value == Icons.Rounded.EventNote) Color(
                                    0xffFFB703
                                ) else Color.White

                            )
                            Text(
                                text = "Lịch sử",
                                color = if (selected.value == Icons.Rounded.EventNote) Color(0xffFFB703) else Color.White
                            )
                        }

                    }


                    IconButton(
                        onClick = {
                            selected.value = Icons.Rounded.ShoppingCart
                            navigationController.navigate(Route.CartUser.screen){
                                popUpTo(0)
                            }
                        },
                        modifier = Modifier.weight(1f)
                    )
                    {
                        Column(
                            verticalArrangement =Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally

                        ) {
                        Icon( Icons.Rounded.ShoppingCart,
                            contentDescription = "",
                            modifier = Modifier.size(24.dp),
                            tint = if (selected.value == Icons.Rounded.ShoppingCart) Color(0xffFFB703) else Color.White

                        )
                            Text(
                                text = "Giỏ hàng",
                                color = if (selected.value == Icons.Rounded.ShoppingCart) Color(0xffFFB703) else Color.White
                            )
                        }

                    }



                    IconButton(
                        onClick = {
                            selected.value = Icons.Default.Person
                            navigationController.navigate(Route.PersonUser.screen){
                                popUpTo(0)
                            }
                        },
                        modifier = Modifier.weight(1f)
                    )
                    {
                        Column(
                            verticalArrangement =Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally

                        ) {
                            Icon(
                                Icons.Default.Person,
                                contentDescription = "",
                                modifier = Modifier.size(24.dp),
                                tint = if (selected.value == Icons.Default.Person) Color(0xffFFB703) else Color.White
                            )
                            Text(
                                text = "Hồ sơ",
                                color = if (selected.value == Icons.Default.Person) Color(
                                    0xffFFB703
                                ) else Color.White
                            )
                        }

                    }



                }
            }

        }
    )

    { paddingValues ->




        NavHost(
            navController = navigationController,
            startDestination = Route.HomeUser.screen,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(Route.HomeUser.screen){
                HomeUserScreen()
            }
            composable(Route.CartUser.screen){
                CartUserScreen()
            }
            composable(Route.HistoryUser.screen){
                HistoryUserScreen()
            }
            composable(Route.PersonUser.screen){
                EditPersonUser()
            }



        }
    }

}
package com.example.comtam_kotlin_room.ui.screen

import androidx.annotation.MainThread
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.AutoMode
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Label
import androidx.compose.material.icons.outlined.LeaveBagsAtHome
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material.icons.rounded.AutoMode
import androidx.compose.material.icons.rounded.Bookmark
import androidx.compose.material.icons.rounded.Label
import androidx.compose.material.icons.rounded.LeaveBagsAtHome
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
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.comtam_kotlin_room.R
import com.example.comtam_kotlin_room.ui.screen.home.DetailsCart
import com.example.comtam_kotlin_room.data.Database
import com.example.comtam_kotlin_room.ui.screen.category.CategoryScreen
import com.example.comtam_kotlin_room.ui.screen.category.CategoryViewModel
import com.example.comtam_kotlin_room.ui.screen.home.HomeScreen
import com.example.comtam_kotlin_room.ui.screen.home.OderCartViewModel
import com.example.comtam_kotlin_room.ui.screen.manager.MangerScreen
import com.example.comtam_kotlin_room.ui.screen.support.SupportScreen
import com.example.comtam_kotlin_room.ui.screen.thongke.BieuDo
import com.example.comtam_kotlin_room.ui.screen.thongke.History
import com.example.comtam_kotlin_room.ui.screen.thongke.ThongKe
import com.example.comtam_kotlin_room.utils.Route



@Composable
fun BottomNavigation( viewModelCategory: CategoryViewModel, navController: NavHostController,oderCartViewModel: OderCartViewModel){
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        MyBottomAppBar(viewModelCategory, navController,oderCartViewModel)
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun MyBottomAppBar( viewModelCategory: CategoryViewModel, navController: NavHostController,oderCartViewModel: OderCartViewModel) {

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
                                 horizontalArrangement = Arrangement.Start) {
                                 Image(
                                     painter = painterResource(id = R.drawable.logo),
                                     contentDescription ="",
                                     contentScale = ContentScale.Fit,
                                     modifier = Modifier.fillMaxWidth(0.12f)
                                 )
                                 Text(text = "Cum tứm đim", modifier = Modifier.weight(0.2f))
                                 TextButton(onClick = { navController.navigate(Route.LOGIN.screen){
                                     popUpTo(Route.Home.screen){inclusive = true}
                                 } }) {
                                     Text(text = "Sign Out", color = Color.White)
                                 }

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
                         containerColor = Color(0xff252121),
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
                            navigationController.navigate(Route.Home.screen){
                                popUpTo(0)

                            }
                        },
                        modifier = Modifier.weight(1f)
                    )
                    {
                        Icon( if (selected.value == Icons.Default.Home)  Icons.Default.Home  else  Icons.Outlined.Home,
                            contentDescription = "",
                            modifier = Modifier.size(24.dp),
                            tint = Color.White
                        )

                    }

                    IconButton(
                        onClick = {
                            selected.value = Icons.Rounded.ShoppingCart
                            navigationController.navigate(Route.THONGKE.screen){
                                popUpTo(0)
                            }
                        },
                        modifier = Modifier.weight(1f)
                    )
                    {
                        Icon(if (selected.value == Icons.Rounded.ShoppingCart) Icons.Rounded.ShoppingCart  else  Icons.Outlined.ShoppingCart,
                            contentDescription = "",
                            modifier = Modifier.size(24.dp),
                            tint = Color.White

                        )

                    }


                    IconButton(
                        onClick = {
                            selected.value = Icons.Rounded.LeaveBagsAtHome
                            navigationController.navigate(Route.MANAGER.screen){
                                popUpTo(0)
                            }
                        },
                        modifier = Modifier.weight(1f)
                    )
                    {
                        Icon(if (selected.value == Icons.Rounded.LeaveBagsAtHome) painterResource(id = R.drawable.shoppingmode_fill_24px) else painterResource(id = R.drawable.shoppingmode_24px),
                            contentDescription = "",
                            modifier = Modifier.size(24.dp),
                            tint = Color.White

                        )

                    }



                    IconButton(
                        onClick = {
                            selected.value = Icons.Default.Person
                            navigationController.navigate(Route.SUPPORT.screen){
                                popUpTo(0)
                            }
                        },
                        modifier = Modifier.weight(1f)
                    )
                    {
                        Icon(if (selected.value == Icons.Default.Person) Icons.Default.Person else Icons.Outlined.Person,
                            contentDescription = "",
                            modifier = Modifier.size(24.dp),
                            tint = Color.White

                        )

                    }



                }
            }

        }
    )

    { paddingValues ->




        NavHost(
            navController = navigationController,
            startDestination = Route.Home.screen,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(Route.Home.screen) { HomeScreen(navigationController,oderCartViewModel) }
            composable(Route.DetailCart.screen) { DetailsCart(navigationController,oderCartViewModel) }
//            composable(Route.MANAGER.screen) { MangerScreen(navigationController) }
            composable(Route.MANAGER.screen) { MangerScreen(navController) }
            composable(Route.THONGKE.screen) { ThongKe(navigationController) }
         composable(Route.History.screen){ History()}
            composable(Route.BieuDo.screen){ BieuDo(navigationController)}
            composable(Route.SUPPORT.screen) { SupportScreen(navController) }



        }
    }

}


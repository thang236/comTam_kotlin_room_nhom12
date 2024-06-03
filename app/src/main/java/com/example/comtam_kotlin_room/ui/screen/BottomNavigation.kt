package com.example.comtam_kotlin_room.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material.icons.rounded.AutoMode
import androidx.compose.material.icons.rounded.Bookmark
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material3.BottomAppBar
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.comtam_kotlin_room.R
import com.example.comtam_kotlin_room.ui.screen.home.HomeScreen
import com.example.comtam_kotlin_room.ui.screen.manager.MangerScreen
import com.example.comtam_kotlin_room.ui.screen.support.SupportScreen
import com.example.comtam_kotlin_room.ui.screen.thongke.ThongKe
import com.example.comtam_kotlin_room.utils.Route


@Composable
fun BottomNavigation(){
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        MyBottomAppBar()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyBottomAppBar() {
    val context = LocalContext.current.applicationContext
    val navigationController = rememberNavController()
    val selected = remember {
        mutableStateOf(Icons.Default.Home)
    }

    Scaffold (
        topBar = {
                 TopAppBar(

                     title = {
                         Row {
                             Image(painter = painterResource(id = R.drawable.logo), contentDescription ="" )
                             Text(text = "Cum tứm đim")

                         }
                     },
                     colors = TopAppBarDefaults.topAppBarColors(
                         containerColor = Color(0xff252121),
                         titleContentColor = Color.White,
                     ),

                     )
        },

        bottomBar = {
            BottomAppBar(
                modifier = Modifier.shadow(8.dp, shape = RoundedCornerShape(10.dp)),
                containerColor = Color(0xFFFFFFFF)
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
                    )

                }


                IconButton(
                    onClick = {
                        selected.value = Icons.Rounded.AutoMode
                        navigationController.navigate(Route.MANAGER.screen){
                            popUpTo(0)
                        }
                    },
                    modifier = Modifier.weight(1f)
                )
                {
                    Icon(if (selected.value == Icons.Rounded.AutoMode) Icons.Rounded.AutoMode else Icons.Outlined.AutoMode,
                        contentDescription = "",
                        modifier = Modifier.size(24.dp),
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
                    )

                }







            }
        }
    )
    {paddingValues ->
        NavHost(navController = navigationController,
            startDestination = Route.Home.screen,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(Route.Home.screen){ HomeScreen()}
            composable(Route.MANAGER.screen){ MangerScreen() }
            composable(Route.THONGKE.screen){ ThongKe() }
            composable(Route.SUPPORT.screen){ SupportScreen() }

        }

    }

}
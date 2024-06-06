package com.example.comtam_kotlin_room.ui.screen.dish

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.comtam_kotlin_room.R
import com.example.comtam_kotlin_room.ui.theme.ComTam_kotlin_roomTheme
import com.example.comtam_kotlin_room.utils.Route

data class MenuItem(val id: Int, val name: String, val price: String, val image: Int)

val items = listOf(
    MenuItem(1, "Sườn bì", "28K", R.drawable.comtam),
    MenuItem(2, "Bì chả", "25K", R.drawable.comtam),
    MenuItem(3, "Trứng chả", "25K", R.drawable.comtam),
    MenuItem(4, "Sườn chả", "28K", R.drawable.comtam),
    MenuItem(5, "Sườn bì chả", "35K", R.drawable.comtam),
    MenuItem(6, "Sườn cay", "35K", R.drawable.comtam),
    MenuItem(7, "Sườn trứng", "30K", R.drawable.comtam),
    MenuItem(8, "Sườn trứng", "30K", R.drawable.comtam),
    MenuItem(9, "Sườn trứng", "30K", R.drawable.comtam),
    MenuItem(10, "Sườn trứng", "30K", R.drawable.comtam),
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ManagerDishScreen(navigationController: NavHostController){
    Scaffold(
        topBar = {
            Column(
                Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF242020))
            ) {
                TopAppBar(
                    title = {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color(0xFF242020)),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(Icons.Default.ArrowBackIosNew, contentDescription ="" ,
                                Modifier.clickable { navigationController.popBackStack() })
                            Image(
                                painter = painterResource(id = R.drawable.logo),
                                contentDescription ="",
                                contentScale = ContentScale.Fit,
                                modifier = Modifier.fillMaxWidth(0.12f)
                            )
                            Text(text = "Cum tứm đim", color = Color.White)

                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color(0xFF252121),
                        titleContentColor = Color.White,
                    ),
                )
                Divider(thickness = 2.dp, color = Color.Black)
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navigationController.navigate(Route.AddDish.screen)
            }, contentColor = Color.White, containerColor = Color(0xFFbdf1e9)) {
                Icon(imageVector = Icons.Rounded.Add, contentDescription = "Add", tint = Color.Black)
            }
        },

        ) { inpadding ->
        ManegerDish(items, navigationController,inpadding)
    }
}

@Composable
fun ManegerDish(items: List<MenuItem>, navigationController: NavHostController,inpadding: PaddingValues) {

    LazyColumn(

        modifier = Modifier
            .fillMaxSize().padding(inpadding)
            .background(Color(0xFF242020)),

        ) {
        itemsIndexed(items) { index, item ->
            MenuItemCard(index + 1, item, navigationController = navigationController)
        }
    }
}

@Composable
fun MenuItemCard(order: Int, item: MenuItem, navigationController: NavHostController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF2F2D2D))
    ) {
        Row(modifier = Modifier.padding(8.dp)) {
            Text(
                text = "$order",
                fontSize = 18.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Image(
                painter = painterResource(id = item.image),
                contentDescription = item.name,
                modifier = Modifier
                    .size(64.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
            ) {
                Text(text = item.name, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.White)
                Text(text = item.price, fontSize = 16.sp, color = Color(0xFFFE724C))
            }
            Spacer(modifier = Modifier.width(8.dp))
            IconButton(
                onClick = { navigationController.navigate(Route.UpdateDish.screen) },
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_create_24),
                    contentDescription = "Edit",
                    tint = Color.White
                )
            }
            Spacer(modifier = Modifier.width(5.dp))
            IconButton(
                onClick = { },
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_delete_24),
                    contentDescription = "Delete",
                    tint = Color.White
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComTam_kotlin_roomTheme {
        ManagerDishScreen(navigationController = rememberNavController())
    }
}

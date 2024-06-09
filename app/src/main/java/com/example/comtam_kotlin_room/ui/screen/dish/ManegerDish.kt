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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.comtam_kotlin_room.R
import com.example.comtam_kotlin_room.ui.screen.category.CategoryEvent
import com.example.comtam_kotlin_room.ui.screen.category.DialogDelete
import com.example.comtam_kotlin_room.ui.screen.category.DialogEdit
import com.example.comtam_kotlin_room.utils.Route



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ManagerDishScreen(
    state: DishState,
    navigationController: NavHostController,
    onEvent: (DishEvent) -> Unit){
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

        ) { paddingValues ->
        LazyColumn(
            contentPadding = paddingValues,
            modifier = Modifier
                .background(Color(0xFF252121))
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(state.dishs.size) { index ->
                MenuItemCard(
                    state = state,
                    index = index,
                    onEvent = onEvent,
                    navigationController = navigationController
                )
            }
        }
    }
}
@SuppressLint("DefaultLocale")
@Composable
fun MenuItemCard(
    state: DishState,
    index: Int,
    onEvent: (DishEvent) -> Unit,
    navigationController: NavHostController
) {
    var showDialogEdit by remember { mutableStateOf(false) }
    if (showDialogEdit){
        UpdateDish(
            state = state,
            dish = state.dishs[index],
            onDismiss = { showDialogEdit = false },
            onEvent = onEvent)
    }
    var showDialogDelete by remember { mutableStateOf(false) }

    if (showDialogDelete) {
        DialogDelete(
            onConfirmation = {
                onEvent(DishEvent.DeleteDish(state.dishs[index]))
                showDialogDelete = false
            },
            onDismiss = { showDialogDelete = false }
        )
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF2F2D2D))
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            val imageByteArray = state.dishs[index].image
            if (imageByteArray != null) {
                AsyncImage(
                    model = imageByteArray,
                    contentDescription = state.dishs[index].nameDish, // Description using the dish name
                    modifier = Modifier
                        .size(64.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )
            } else {
                Image(
                    painter = painterResource(id = R.drawable.comtam),
                    contentDescription = state.dishs[index].nameDish, // Description using the dish name
                    modifier = Modifier
                        .size(64.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(
                    text = state.dishs[index].nameDish,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = String.format("%.2f VND", state.dishs[index].price),
                    fontSize = 16.sp,
                    color = Color(0xFFFE724C)
                )
            }
            IconButton(
                onClick = {
                    showDialogEdit = true
                    state.nameDish.value = state.dishs[index].nameDish
                    state.price.value = state.dishs[index].price
                    state.image.value = state.dishs[index].image
                },
                modifier = Modifier
                    .size(24.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_create_24),
                    contentDescription = "Chỉnh sửa món ăn",
                    tint = Color.White
                )
            }
            IconButton(
                onClick = { showDialogDelete = true },
                modifier = Modifier
                    .size(24.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_delete_24),
                    contentDescription = "Xóa món ăn",
                    tint = Color.White
                )
            }
        }
    }
}



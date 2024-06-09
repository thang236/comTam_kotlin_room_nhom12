package com.example.comtam_kotlin_room.ui.screen.dish

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.comtam_kotlin_room.R
import com.example.comtam_kotlin_room.data.DAO.DishDao
import com.example.comtam_kotlin_room.data.entity.Category
import com.example.comtam_kotlin_room.data.entity.Dish
import com.example.comtam_kotlin_room.ui.screen.category.CategoryEvent
import com.example.comtam_kotlin_room.ui.theme.ComTam_kotlin_roomTheme
import com.example.comtam_kotlin_room.utils.Route

//@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun UpdateDishScreen(
//    state: DishState,
//    navigationController: NavHostController,
//    onEvent: (DishEvent) -> Unit
//) {
//
//        Scaffold(
//            topBar = {
//                Column(
//                    Modifier
//                        .fillMaxWidth()
//                        .background(Color(0xFF242020))
//                ) {
//                    TopAppBar(
//                        title = {
//                            Row(
//                                modifier = Modifier
//                                    .fillMaxWidth()
//                                    .background(Color(0xFF242020)),
//                                verticalAlignment = Alignment.CenterVertically
//                            ) {
//                                Icon(Icons.Default.ArrowBackIosNew, contentDescription ="" ,
//                                    Modifier.clickable { navigationController.popBackStack() })
//                                Image(
//                                    painter = painterResource(id = R.drawable.logo),
//                                    contentDescription ="",
//                                    contentScale = ContentScale.Fit,
//                                    modifier = Modifier.fillMaxWidth(0.12f)
//                                )
//                                Text(text = "Cum tứm đim", color = Color.White)
//
//                            }
//                        },
//                        colors = TopAppBarDefaults.topAppBarColors(
//                            containerColor = Color(0xFF252121),
//                            titleContentColor = Color.White,
//                        ),
//                    )
//                    Divider(thickness = 2.dp, color = Color.Black)
//                }
//            },
//
//            ) {
//            UpdateDish(
//                state = state,
//                onEvent = onEvent,
//                navigationController = navigationController
//            )
//        }
//
//}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateDish(
    state: DishState,
    onEvent: (DishEvent) -> Unit,
    onDismiss: () -> Unit,
    dish: Dish,
) {
    var price by remember { mutableStateOf(dish.price) }
    val priceString = price.toString()
    val context = LocalContext.current
    var imageByte by remember { mutableStateOf<ByteArray?>(null) }
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val getContent = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let {
            imageUri = it
            imageByte = uriToByteArray(context.contentResolver, it)
            Log.d("zzzzzzzz", "AddDish: $imageByte")
        }
    }

    state.nameDish.value = dish.nameDish
    state.price.value = dish.price

    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = {
            Text(text = "Chỉnh sửa món ăn", fontWeight = FontWeight.Bold)
        },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                Box(
                    modifier = Modifier
                        .size(200.dp)
                        .background(Color.Gray, shape = RoundedCornerShape(8.dp))
                        .clickable { getContent.launch("image/*") }
                        .align(Alignment.CenterHorizontally),
                    contentAlignment = Alignment.Center
                ) {
                    AsyncImage(
                        model = imageUri,
                        contentDescription = "",
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(8.dp)),
                        contentScale = ContentScale.Crop,
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                TextField(
                    value = priceString,
                    onValueChange = { newValue ->
                        price = newValue.toDoubleOrNull() ?: 0.0
                    },
                    label = { Text("Giá") },
                    modifier = Modifier
                        .background(Color.White)
                        .fillMaxWidth(),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.White
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                TextField(
                    value = state.nameDish.value,
                    onValueChange = { state.nameDish.value = it },
                    label = { Text("Tên món ăn") },
                    modifier = Modifier
                        .background(Color.White)
                        .fillMaxWidth(),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.White
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    dish.nameDish = state.nameDish.value
                    dish.price = price
                    onEvent(DishEvent.EditDish(dish))
                    onDismiss()
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFA000))
            ) {
                Text("Sửa", color = Color.White, fontSize = 18.sp)
            }
        },
        dismissButton = {
            Button(
                onClick = { onDismiss() },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)
            ) {
                Text("Hủy", color = Color.White, fontSize = 18.sp)
            }
        }
    )
}




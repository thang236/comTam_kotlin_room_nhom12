package com.example.comtam_kotlin_room.ui.screen.dish

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.comtam_kotlin_room.R
import com.example.comtam_kotlin_room.ui.theme.ComTam_kotlin_roomTheme

class UpdateDishActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComTam_kotlin_roomTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = Color(0xFF252121)) {
                    UpdateDishScreen(rememberNavController())
                }
            }
        }
    }
}

@Composable
fun UpdateDishScreen(navigationController: NavHostController) {
    ComTam_kotlin_roomTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = Color(0xFF252121)) {
            UpdateDish(navigationController)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateDish(navigationController: NavHostController) {

    var price by remember { mutableStateOf(TextFieldValue("100k")) }
    var foodName by remember { mutableStateOf(TextFieldValue("Sườn")) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        SmallTopAppBar(
            title = { Text("Update", color = Color.White) },
            navigationIcon = {
                IconButton(
                    onClick = { navigationController.popBackStack() }
                ) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                }
            },
            colors = TopAppBarDefaults.smallTopAppBarColors(
                containerColor = Color(0xFF252121)
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .size(200.dp)
                .background(Color.Gray, shape = RoundedCornerShape(8.dp))
                .clickable { /* Add image upload functionality */ },
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.comtam),
                contentDescription = "Sample Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }

        Spacer(modifier = Modifier.height(150.dp))

        TextField(
            value = price,
            onValueChange = { price = it },
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
            value = foodName,
            onValueChange = { foodName = it },
            label = { Text("Tên món ăn") },
            modifier = Modifier
                .background(Color.White)
                .fillMaxWidth(),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { /* Handle save action */ },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFA000))
        ) {
            Text("Sửa", color = Color.White, fontSize = 18.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewUpdateDishScreen() {
    ComTam_kotlin_roomTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = Color(0xFF252121)) {
            UpdateDishScreen(rememberNavController())
        }
    }
}

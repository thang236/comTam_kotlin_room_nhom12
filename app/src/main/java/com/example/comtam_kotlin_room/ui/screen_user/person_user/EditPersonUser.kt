package com.example.comtam_kotlin_room.ui.screen_user.person_user

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.rounded.PhotoCamera
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.comtam_kotlin_room.R
import com.example.comtam_kotlin_room.utils.Route

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditPersonUser(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xff000000))
                .padding(horizontal = 10.dp)
                .padding(top = 30.dp)
        ) {
            Row(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Icon(
                    Icons.Default.ArrowBackIosNew,
                    contentDescription = "",
                    modifier = Modifier.size(20.dp)
                        .clickable { navController.navigate(Route.NavigationUser.screen) },
                    tint = Color.White
                )
            }
            Text(
                text = "Sửa hồ sơ",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.align(Alignment.Center)
            )
        }
        Box(
            modifier = Modifier
                .background(Color(0xFF000000))
        ) {
            Card(
                modifier = Modifier
                    .padding(top = 50.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF252121)),
                shape = RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp),
            ) {
                Column(modifier = Modifier
                    .padding(top = 80.dp)
                    .padding(10.dp)
                    .verticalScroll(rememberScrollState())) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                    ) {
                        Text(text = "Họ và Tên", fontSize = 17.sp, color = Color.White)
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(16.dp),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                containerColor = Color(0xFFD9D9D9)
                            ),
                            value = "Nhóm 12",
                            onValueChange = {  },
                            label = { Text("") }
                        )
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 20.dp)
                            .padding(horizontal = 20.dp)
                    ) {
                        Text(text = "Số điện thoại", fontSize = 17.sp, color = Color.White)
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(16.dp),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                containerColor = Color(0xFFD9D9D9)
                            ),
                            value = "0123456789",
                            onValueChange = {  },
                            label = { Text("") }
                        )
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 20.dp)
                            .padding(horizontal = 20.dp)
                    ) {
                        Text(text = "Phường", fontSize = 17.sp, color = Color.White)
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(16.dp),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                containerColor = Color(0xFFD9D9D9)
                            ),
                            value = "Xuân Phương",
                            onValueChange = {  },
                            label = { Text("") }
                        )
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 20.dp)
                            .padding(horizontal = 20.dp)
                    ) {
                        Text(text = "Đường", fontSize = 17.sp, color = Color.White)
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(16.dp),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                containerColor = Color(0xFFD9D9D9)
                            ),
                            value = "Trịnh Văn Bô",
                            onValueChange = {  },
                            label = { Text("") }
                        )
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 20.dp)
                            .padding(horizontal = 20.dp)
                    ) {
                        Text(text = "Số nhà", fontSize = 17.sp, color = Color.White)
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(16.dp),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                containerColor = Color(0xFFD9D9D9)
                            ),
                            value = "Tòa nhà FPT Polytechnic",
                            onValueChange = {  },
                            label = { Text("") }
                        )
                    }
                }
            }
            Box(
                modifier = Modifier.align(Alignment.TopCenter)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "",
                    modifier = Modifier // clip to the circle shape
                        .border(11.dp, Color.White, CircleShape)
                        .size(120.dp)
                )
                Box(modifier = Modifier.align(Alignment.BottomEnd)) {
                    IconButton(
                        onClick = { navController.navigate(Route.ChangImageUser.screen) },
                        colors = IconButtonDefaults.iconButtonColors(
                            containerColor = Color.White
                        ),
                        modifier = Modifier
                            .padding(8.dp)
                            .size(27.dp)
                    ) {
                        Icon(imageVector = Icons.Rounded.PhotoCamera, contentDescription = "", modifier = Modifier.size(15.dp))
                    }
                }
            }
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF252121))
        ){
            Divider(thickness = 2.dp, color = Color.Black)
            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFE724C)
                ),
                shape = RoundedCornerShape(40.dp),
                modifier = Modifier
                    .padding(top = 10.dp)
                    .fillMaxWidth(0.5f)
                    .align(Alignment.Center)
            ) {
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = "Lưu",
                    fontSize = 20.sp
                )
            }
        }
    }
}

package com.example.comtam_kotlin_room.ui.screen_user.person_user

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import com.example.comtam_kotlin_room.R
import com.example.comtam_kotlin_room.utils.Route

@Composable
fun PersonUserScreen(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF000000))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Edit",
                    color = Color.White,
                    fontSize = 20.sp,
                    modifier = Modifier.clickable {navController.navigate(Route.EditPersonUser.screen)})
                Text(
                    text = "SignOut",
                    color = Color.White,
                    fontSize = 20.sp,
                    modifier = Modifier.clickable { navController.navigate(Route.LOGIN.screen){
                        popUpTo(Route.NavigationUser.screen){inclusive = true}
                    } })
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 50.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF252121)),
                    shape = RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp),
                ) {
                    Column(modifier = Modifier
                        .padding(top = 140.dp).verticalScroll(rememberScrollState())) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp)
                        ) {
                            Text(text = "Số điện thoại", fontSize = 17.sp, color = Color.White)
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                text = "0123456789",
                                color = Color.Black,
                                fontSize = 17.sp,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(60.dp)
                                    .clip(shape = RoundedCornerShape(16.dp))
                                    .background(color = Color(0xFFD9D9D9))
                                    .padding(20.dp)
                            )
                        }
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 30.dp)
                                .padding(horizontal = 20.dp)
                        ) {
                            Text(text = "Phường", fontSize = 17.sp, color = Color.White)
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                text = "Xuân Phương",
                                color = Color.Black,
                                fontSize = 17.sp,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(60.dp)
                                    .clip(shape = RoundedCornerShape(16.dp))
                                    .background(color = Color(0xFFD9D9D9))
                                    .padding(20.dp)
                            )
                        }
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 30.dp)
                                .padding(horizontal = 20.dp)
                        ) {
                            Text(text = "Đường", fontSize = 17.sp, color = Color.White)
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                text = "Trịnh Văn Bô",
                                color = Color.Black,
                                fontSize = 17.sp,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(60.dp)
                                    .clip(shape = RoundedCornerShape(16.dp))
                                    .background(color = Color(0xFFD9D9D9))
                                    .padding(20.dp)
                            )
                        }
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 30.dp)
                                .padding(horizontal = 20.dp)
                        ) {
                            Text(text = "Số nhà", fontSize = 17.sp, color = Color.White)
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                text = "Tòa nhà FPT Polytechnic",
                                color = Color.Black,
                                fontSize = 17.sp,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(60.dp)
                                    .clip(shape = RoundedCornerShape(16.dp))
                                    .background(color = Color(0xFFD9D9D9))
                                    .padding(20.dp)
                            )
                        }
                    }
                }
                Box(
                    modifier = Modifier.align(Alignment.TopCenter)
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Image(
                            painter = painterResource(id = R.drawable.logo),
                            contentDescription = "",
                            modifier = Modifier // clip to the circle shape
                                .border(11.dp, Color.White, CircleShape)
                                .size(120.dp)
                        )
                        Text(
                            text = "Nhóm 12",
                            color = Color.White,
                            fontSize = 22.sp,
                            modifier = Modifier.padding(top = 5.dp)
                        )
                    }
                }
            }

        }
    }
}
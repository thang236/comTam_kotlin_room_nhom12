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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.comtam_kotlin_room.R

@Preview(showBackground = true)
@Composable
fun ChangeImageUser() {
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
                .padding(top = 10.dp)
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
                    modifier = Modifier
                        .size(20.dp)
                        .clickable { },
                    tint = Color.White
                )
            }
            Text(
                text = "Chỉnh sửa ảnh",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.align(Alignment.Center)
            )

        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF000000))
        ) {
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 150.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF252121)),
                shape = RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp),
            ) {
                Column(
                    modifier = Modifier
                        .padding(top = 180.dp)
                ) {
                    Button(
                        onClick = { },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFD9D9D9)
                        ),
                        shape = RoundedCornerShape(40.dp),
                        modifier = Modifier
                            .padding(top = 10.dp)
                            .fillMaxWidth()
                            .padding(horizontal = 100.dp)
                    ) {
                        Text(
                            modifier = Modifier.padding(8.dp),
                            text = "Chọn ảnh từ thư viện",
                            fontSize = 13.sp,
                            color = Color.Black
                        )
                    }
                    Button(
                        onClick = { },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFD9D9D9)
                        ),
                        shape = RoundedCornerShape(40.dp),
                        modifier = Modifier
                            .padding(top = 10.dp)
                            .fillMaxWidth()
                            .padding(horizontal = 100.dp)
                    ) {
                        Text(
                            modifier = Modifier.padding(8.dp),
                            text = "Chụp ảnh mới",
                            fontSize = 13.sp,
                            color = Color.Black
                        )
                    }
                    Button(
                        onClick = { },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFFE724C)
                        ),
                        shape = RoundedCornerShape(40.dp),
                        modifier = Modifier
                            .padding(top = 50.dp)
                            .fillMaxWidth()
                            .padding(horizontal = 60.dp)
                    ) {
                        Text(
                            modifier = Modifier.padding(8.dp),
                            text = "Lưu",
                            fontSize = 20.sp
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
                            .border(27.dp, Color.White, CircleShape)
                            .size(270.dp)
                    )
                }
            }
        }

    }
}
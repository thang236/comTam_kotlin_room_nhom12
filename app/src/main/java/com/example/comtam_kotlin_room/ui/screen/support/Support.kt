package com.example.comtam_kotlin_room.ui.screen.support

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.comtam_kotlin_room.R

@Preview(showBackground = true)
@Composable
fun SupportScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xff252121))
            .padding(horizontal = 20.dp)
            .padding(top = 20.dp)
        ,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier.clickable {},
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Image(
                painter = painterResource(id = R.drawable.zalo),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth(0.2f)
                    .size(50.dp)
            )
            Text(
                text = "0123456789",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                modifier = Modifier.padding(start = 20.dp).fillMaxWidth()
            )
        }
        Spacer(modifier = Modifier.height(50.dp))
        Row(
            modifier = Modifier.clickable {},
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.gmail),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth(0.2f)
                    .size(40.dp)
            )
            Text(
                text = "Nhom12@fpt.edu.vn",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                modifier = Modifier.padding(start = 20.dp).fillMaxWidth()
            )
        }
        Spacer(modifier = Modifier.height(50.dp))
        Row(
            modifier = Modifier.clickable {},
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.phone),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth(0.2f)
                    .size(50.dp)
            )
            Text(
                text = "0123456789",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                modifier = Modifier.padding(start = 20.dp).fillMaxWidth()
            )
        }
    }

}